package peaner.platform.sdk.common.responseBean.weixin;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description: 获取预授权码pre_auth_code返回参数
 * @Date: Created in 下午3:16 2018/11/11
 */
@Setter
@Getter
public class PreAuthCodeRespBean {

    /**
     * 预授权码
     */
    @SerializedName("pre_auth_code")
    private String preAuthCode;

    /**
     * 有效期，为10分钟
     */
    @SerializedName("expires_in")
    private String expiresIn;

}
