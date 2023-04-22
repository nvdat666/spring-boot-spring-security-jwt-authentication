package vn.datnv.springjwt.dto.response;

import lombok.Getter;
import lombok.Setter;
import vn.datnv.springjwt.constant.CommonConstants;

@Getter
@Setter
public class JwtResponse {
	private String accessToken;
	private String type = CommonConstants.TOKEN_TYPE;
	private String refreshToken;
	private Long id;
	private String username;
	private String email;
	private String role;

	public JwtResponse(String accessToken, String refreshToken, Long id, String username, String email, String role) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.role = role;
	}

	
}
