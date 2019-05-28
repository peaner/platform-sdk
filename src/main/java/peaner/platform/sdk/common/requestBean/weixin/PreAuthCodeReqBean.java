package peaner.platform.sdk.common.requestBean.weixin;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description: 获取预授权码pre_auth_code请求参数
 * @Date: Created in 下午3:17 2018/11/11
 */
@Setter
@Getter
public class PreAuthCodeReqBean {

    /**
     * 第三方平台方appid
     */
    @SerializedName("component_appid")
    private String componentAppId;

}
