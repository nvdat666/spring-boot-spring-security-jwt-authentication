package vn.datnv.springjwt.dto.response;

import lombok.Getter;
import lombok.Setter;
import vn.datnv.springjwt.constant.CommonConstants;

@Getter
@Setter
public class TokenRefreshResponse {
  private String accessToken;
  private String refreshToken;
  private String tokenType = CommonConstants.TOKEN_TYPE;

  public TokenRefreshResponse(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }
}
