package peaner.platform.sdk.api.alipay.pay;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description:
 * @Date: Created in 18-7-5 下午4:41
 **/
@Setter
@Getter
public class TradePayBean {

    /**
     * 商户订单号
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     * 当前预下单请求生成的二维码码串，可以用二维码生成工具根据该码串值生成对应的二维码
     */
    @SerializedName("qr_code")
    private String qrCode;

    /**
     * 网关返回码
     */
    private String code;

    /**
     * 网关返回码描述
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

}
