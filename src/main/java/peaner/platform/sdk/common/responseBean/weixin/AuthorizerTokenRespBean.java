package peaner.platform.sdk.common.responseBean.weixin;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description: 获取（刷新）授权公众号或小程序的接口调用凭据（令牌）返回参数
 * @Date: Created in 下午3:38 2018/11/11
 */
@Setter
@Getter
public class AuthorizerTokenRespBean {

    /**
     * 授权方令牌
     */
    @SerializedName("authorizer_access_token")
    private String authorizerAccessToken;

    /**
     * 有效期，为2小时
     */
    @SerializedName("expires_in")
    private String expiresIn;

    /**
     * 刷新令牌
     */
    @SerializedName("authorizer_refresh_token")
    private String authorizerRefreshToken;


}
