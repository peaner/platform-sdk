package peaner.platform.sdk.api.alipay.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description: 授权响应参数
 * @Date: Created in 18-7-2 下午9:59
 **/
@Setter
@Getter
public class AuthRespBean {

    @SerializedName("alipay_open_auth_token_app_response")
    private AuthBean alipayOpenAuthTokenAppResponse;

    /**
     * 必填,签名
     */
    private String sign;


}
