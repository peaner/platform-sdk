package peaner.platform.sdk.api.alipay.pay;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description:
 * @Date: Created in 18-7-5 下午6:32
 **/
@Setter
@Getter
public class MobileTradePayBean {

    /**
     * 该交易在支付宝系统中的交易流水号
     */
    @SerializedName("trade_no")
    private String tradeNo;

    /**
     * 商户网站唯一订单号
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     * 该笔订单的资金总额，单位为RMB-Yuan
     */
    @SerializedName("total_amount")
    private Double totalAmount;

    /**
     * 收款支付宝账号对应的支付宝唯一用户号
     */
    @SerializedName("seller_id")
    private String sellerId;

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
