package peaner.platform.sdk.api.alipay.pay.advice;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: lilongzhou
 * @Description:
 * @Date: Created in 18-7-6 下午4:10
 **/
@Setter
@Getter
public class TradePayMsgBean {

    /**
     * 通知的发送时间。格式为yyyy-MM-dd HH:mm:ss
     */
    @SerializedName("notify_time")
    private Date notifyTime;

    /**
     * 通知的类型
     */
    @SerializedName("notify_type")
    private String notifyType;

    /**
     * 通知校验ID
     */
    @SerializedName("notify_id")
    private String notifyId;

    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     */
    @SerializedName("sign_type")
    private String signType;

    /**
     * 签名
     */
    private String sign;

    /**
     * 支付宝交易号
     */
    @SerializedName("trade_no")
    private String tradeNo;

    /**
     * 开发者的app_id
     */
    @SerializedName("app_id")
    private String appId;

    /**
     * 原支付请求的商户订单号
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     * 商户业务ID，主要是退款通知中返回退款申请的流水号
     */
    @SerializedName("out_biz_no")
    private String outBizNo;

    /**
     * 买家支付宝账号对应的支付宝唯一用户号
     */
    @SerializedName("buyer_id")
    private String buyerId;

    /**
     * 买家支付宝账号
     */
    @SerializedName("buyer_logon_id")
    private String buyerLogonId;

    /**
     * 卖家支付宝用户号
     */
    @SerializedName("seller_id")
    private String sellerId;

    /**
     * 卖家支付宝账号
     */
    @SerializedName("seller_email")
    private String sellerEmail;

    /**
     * 交易状态
     */
    @SerializedName("trade_status")
    private String tradeStatus;

    /**
     * 本次交易支付的订单金额，单位为人民币（元）
     */
    @SerializedName("total_amount")
    private Long totalAmount;

    /**
     * 商家在交易中实际收到的款项，单位为元
     */
    @SerializedName("receipt_amount")
    private Long receiptAmount;

    /**
     * 用户在交易中支付的可开发票的金额
     */
    @SerializedName("invoice_amount")
    private Long invoiceAmount;

    /**
     * 用户在交易中支付的金额
     */
    @SerializedName("buyer_pay_amount")
    private Long buyerPayAmount;

    /**
     * 使用集分宝支付的金额
     */
    @SerializedName("point_amount")
    private Long pointAmount;

    /**
     * 总退款金额
     */
    @SerializedName("refund_fee")
    private Long refundFee;

    /**
     * 实际退款金额
     */
    @SerializedName("send_back_fee")
    private Long sendBackFee;

    /**
     * 订单标题
     */
    private String subject;

    /**
     * 商品描述
     */
    private String body;

    /**
     * 交易创建时间
     */
    @SerializedName("gmt_create")
    private Date gmtCreate;

    /**
     * 交易付款时间
     */
    @SerializedName("gmt_payment")
    private Date gmtPayment;

    /**
     * 交易退款时间
     */
    @SerializedName("gmt_refund")
    private Date gmtRefund;

    /**
     * 交易结束时间
     */
    @SerializedName("gmt_close")
    private Date gmtClose;

    /**
     * 支付金额信息
     */
    @SerializedName("fund_bill_list")
    private String fundBillList;

}
