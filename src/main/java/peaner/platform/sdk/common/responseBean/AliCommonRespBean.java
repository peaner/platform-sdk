package peaner.platform.sdk.common.responseBean;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description: 阿里公共响应参数
 * @Date: Created in 18-7-2 下午5:09
 **/
@Setter
@Getter
public class AliCommonRespBean {

    /**
     * 必填,网关返回码
     */
    private String code;

    /**
     * 必填,网关返回码描述
     */
    private String msg;

    /**
     * 业务返回码
     */
    @SerializedName("sub_code")
    private String subCode;

    /**
     * 业务返回码描述
     */
    @SerializedName("sub_msg")
    private String subMsg;

    /**
     * 必填,签名
     */
    private String sign;


}
