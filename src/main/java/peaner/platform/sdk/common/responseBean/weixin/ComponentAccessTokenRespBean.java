package peaner.platform.sdk.common.responseBean.weixin;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description: 获取第三方平台component_access_token返回参数
 * @Date: Created in 上午11:40 2018/11/11
 */
@Setter
@Getter
public class ComponentAccessTokenRespBean {

    /**
     * 第三方平台access_token
     */
    @SerializedName("component_access_token")
    private String componentAccessToken;

    /**
     * 有效期
     */
    @SerializedName("expires_in")
    private String expiresIn;

}
