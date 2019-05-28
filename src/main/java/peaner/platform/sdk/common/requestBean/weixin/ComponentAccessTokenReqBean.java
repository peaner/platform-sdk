package peaner.platform.sdk.common.requestBean.weixin;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description: 获取第三方平台component_access_token请求参数
 * @Date: Created in 上午11:36 2018/11/11
 */
@Setter
@Getter
public class ComponentAccessTokenReqBean {

    /**
     * 第三方平台appid
     */
    @SerializedName("component_appid")
    private String componentAppId;

    /**
     * 第三方平台appsecret
     */
    @SerializedName("component_appsecret")
    private String componentAppSecret;

    /**
     * 微信后台推送的ticket，此ticket会定时推送
     */
    @SerializedName("component_verify_ticket")
    private String componentVerifyTicket;

}
