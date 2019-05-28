package peaner.platform.sdk.common.requestBean.weixin;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description: 获取授权方的帐号基本信息请求参数
 * @Date: Created in 下午3:48 2018/11/11
 */
@Setter
@Getter
public class AuthorizerInfoReqBean {

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

}
