package peaner.platform.sdk.api.alipay.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description:
 * @Date: Created in 18-7-4 下午7:59
 **/
@Setter
@Getter
public class BizContentBean {

    /**
     * 授权类型
     */
    @SerializedName("grant_type")
    private String grantType;

    /**
     * 授权码
     * 当grant_type= authorization_code时，必须录入
     */
    private String code;

    /**
     * 刷新令牌
     * grant_type=refresh_toke时必录
     */
    @SerializedName("refresh_token")
    private String refreshToken;

}
