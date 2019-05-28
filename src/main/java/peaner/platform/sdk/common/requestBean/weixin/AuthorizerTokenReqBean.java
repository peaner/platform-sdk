package peaner.platform.sdk.common.requestBean.weixin;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description: 获取（刷新）授权公众号或小程序的接口调用凭据（令牌）请求参数
 * @Date: Created in 下午3:38 2018/11/11
 */
@Setter
@Getter
public class AuthorizerTokenReqBean {

    /**
     * 第三方平台appid
     */
    @SerializedName("component_appid")
    private String componentAppId;

    /**
     * 授权方appid
     */
    @SerializedName("authorizer_appid")
    private String authorizerAppId;

    /**
     * 授权方的刷新令牌，刷新令牌主要用于第三方平台获取和刷新已授权用户的access_token，
     * 只会在授权时刻提供，请妥善保存。一旦丢失，只能让用户重新授权，才能再次拿到新的刷新令牌
     */
    @SerializedName("authorizer_refresh_token")
    private String authorizerRefreshToken;


}
