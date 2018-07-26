package peaner.platform.sdk.api.alipay.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description: 授权实体
 * @Date: Created in 18-7-5 下午3:13
 **/
@Setter
@Getter
public class AuthBean {

    /**
     * 必填,令牌信息
     */
    @SerializedName("app_auth_token")
    private String appAuthToken;

    /**
     * 必填,刷新令牌
     */
    @SerializedName("app_refresh_token")
    private String appRefreshToken;

    /**
     * 必填,授权方应用id
     */
    @SerializedName("auth_app_id")
    private String authAppId;

    /**
     * 必填,令牌有效期
     */
    @SerializedName("expires_in")
    private String expiresIn;

    /**
     * 必填,刷新令牌有效时间
     */
    @SerializedName("re_expires_in")
    private String reExpiresIn;

    /**
     * 必填,支付宝用户标识
     */
    @SerializedName("userid")
    private String userId;

    /**
     * 必填,网关返回码
     */
    private String code;

    /**
     * 必填,网关返回码描述
     */
    private String msg;

}
