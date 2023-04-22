package vn.datnv.srv.dto.response;

import lombok.Getter;
import lombok.Setter;
import vn.datnv.srv.constant.CommonConstants;

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
