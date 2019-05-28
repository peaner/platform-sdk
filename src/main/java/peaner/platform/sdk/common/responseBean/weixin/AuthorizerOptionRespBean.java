package peaner.platform.sdk.common.responseBean.weixin;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description: 获取授权方的选项设置信息返回参数
 * @Date: Created in 下午4:01 2018/11/11
 */
@Setter
@Getter
public class AuthorizerOptionRespBean {

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
     * 选项值
     */
    @SerializedName("option_value")
    private String optionValue;

}
