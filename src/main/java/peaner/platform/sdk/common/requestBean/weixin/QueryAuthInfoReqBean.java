package peaner.platform.sdk.common.requestBean.weixin;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description: 使用授权码换取公众号或小程序的接口调用凭据和授权信息请求参数
 * @Date: Created in 下午3:24 2018/11/11
 */
@Setter
@Getter
public class QueryAuthInfoReqBean {

    /**
     * 第三方平台appid
     */
    @SerializedName("component_appid")
    private String componentAppId;

    /**
     * 授权code,会在授权成功时返回给第三方平台，详见第三方平台授权流程说明
     */
    @SerializedName("authorization_code")
    private String authorizationCode;

}
