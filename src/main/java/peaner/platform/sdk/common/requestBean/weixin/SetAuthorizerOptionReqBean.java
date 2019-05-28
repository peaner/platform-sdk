package peaner.platform.sdk.common.requestBean.weixin;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description: 设置授权方的选项信息请求参数
 * @Date: Created in 下午4:08 2018/11/11
 */
@Setter
@Getter
public class SetAuthorizerOptionReqBean {

    /**
     * 第三方平台appid
     */
    @SerializedName("component_appid")
    private String componentAppId;

    /**
     * 授权公众号或小程序的appid
     */
    @SerializedName("authorizer_appid")
    private String authorizerAppId;

    /**
     * 选项名称
     */
    @SerializedName("option_name")
    private String optionName;

    /**
     * 设置的选项值
     */
    @SerializedName("option_value")
    private String optionValue;


}
