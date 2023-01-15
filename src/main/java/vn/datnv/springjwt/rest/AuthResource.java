package vn.datnv.springjwt.rest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import vn.datnv.springjwt.dto.request.LoginRequest;
import vn.datnv.springjwt.dto.request.SignupRequest;
import vn.datnv.springjwt.dto.request.TokenRefreshRequest;
import vn.datnv.springjwt.dto.response.JwtResponse;
import vn.datnv.springjwt.dto.response.MessageResponse;
import vn.datnv.springjwt.dto.response.TokenRefreshResponse;
import vn.datnv.springjwt.entity.RefreshToken;
import vn.datnv.springjwt.entity.User;
import vn.datnv.springjwt.enums.Role;
import vn.datnv.springjwt.exception.TokenRefreshException;
import vn.datnv.springjwt.repository.UserRepository;
import vn.datnv.springjwt.security.SecurityUtils;
import vn.datnv.springjwt.security.jwt.JwtUtils;
import vn.datnv.springjwt.security.service.RefreshTokenService;
import vn.datnv.springjwt.security.service.UserDetailsImpl;
import vn.datnv.springjwt.service.UserService;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthResource extends BaseResource{
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;
  @Autowired
  UserService userService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  RefreshTokenService refreshTokenService;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    String jwt = jwtUtils.generateJwtToken(userDetails);

    Role role = SecurityUtils.getCurrentUserRole();

    RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

    return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
        userDetails.getUsername(), userDetails.getEmail(), role.getDisplayValue()));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsernameAndDeletedFalse(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (StringUtils.hasText(signUpRequest.getEmail()) && userRepository.existsByEmailAndDeletedFalse(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }
    
    // Create new user's account
    User user = new User();
    BeanUtils.copyProperties(signUpRequest, user, "password", "userRole");
    user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    user.setUserRole(Role.ROLE_USER);

    userService.createNew(user);
    return ResponseEntity.ok(new MessageResponse("User (" + user.getUsername() + ") registered successfully!"));
  }

  @PostMapping("/refreshtoken")
  public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
    String requestRefreshToken = request.getRefreshToken();

    return refreshTokenService.findByToken(requestRefreshToken)
        .map(refreshTokenService::verifyExpiration)
        .map(RefreshToken::getUserId)
        .map(userId -> {
          String token = jwtUtils.generateTokenFromUsername(userRepository.findByIdAndDeletedFalse(userId).get().getUsername());
          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
        })
        .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
            "Refresh token is not in database!"));
  }
  
  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    refreshTokenService.deleteByUserId(userDetails.getId());
    return ResponseEntity.ok(new MessageResponse("Log out successful!"));
  }

}
