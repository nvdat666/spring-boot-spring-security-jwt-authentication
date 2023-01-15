package vn.datnv.springjwt.dto.request;

import lombok.Getter;
import lombok.Setter;
import vn.datnv.springjwt.enums.Gender;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Getter
@Setter
public class SignupRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String email;

    private String fullName;

    private Instant dob;

    private Gender gender;

    private String phone;

    private String address;

}
