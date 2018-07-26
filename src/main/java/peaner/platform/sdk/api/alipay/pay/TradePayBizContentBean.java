package peaner.platform.sdk.api.alipay.pay;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import peaner.platform.sdk.common.requestBean.extend.ExtendParams;
import peaner.platform.sdk.common.requestBean.extend.GoodsDetail;
import peaner.platform.sdk.common.requestBean.extend.SettleInfo;

/**
 * @Author: lilongzhou
 * @Description:
 * @Date: Created in 18-7-5 下午4:36
 **/
@Setter
@Getter
public class TradePayBizContentBean {

    /**
     * 商户订单号
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     * 支付场景
     * 条码支付，取值：bar_code
     * 声波支付，取值：wave_code
     */
    private String scene;

    /**
     * 支付授权码
     */
    @SerializedName("auth_code")
    private String authCode;

    /**
     * 销售产品码
     */
    @SerializedName("product_code")
    private String productCode;

    /**
     * 订单标题
     */
    private String subject;

    /**
     * 买家的支付宝用户id，如果为空，会从传入了码值信息中获取买家ID
     */
    @SerializedName("buyer_id")
    private String buyerId;

    /**
     * 如果该值为空，则默认为商户签约账号对应的支付宝用户ID
     */
    @SerializedName("seller_id")
    private String sellerId;

    /**
     * 订单总金额，单位为元，
     */
    @SerializedName("total_amount")
    private Double totalAmount;

    /**
     * 标价币种, total_amount 对应的币种单位
     */
    @SerializedName("trans_currency")
    private String transCurrency;

    /**
     * 禁用渠道，用户不可用指定渠道支付当有多个渠道时用“,”分隔
     */
    @SerializedName("disable_pay_channels")
    private String disablePayChannels;

    @SerializedName("enable_pay_channels")
    private String enablePayChannels;

    @SerializedName("business_params")
    private String businessParams;

    @SerializedName("qr_code_timeout_express")
    private String qrCodeTimeoutExpress;

    /**
     * 商户指定的结算币种
     */
    @SerializedName("settle_currency")
    private String settleCurrency;

    /**
     * 参与优惠计算的金额，单位为元
     */
    @SerializedName("discountable_amount")
    private Double discountableAmount;

    @SerializedName("settle_info")
    private SettleInfo settleInfo;

    /**
     * 订单描述
     */
    private String body;

    /**
     * 订单包含的商品列表信息
     */
    @SerializedName("goods_detail")
    private GoodsDetail[] goodsDetail;


    /**
     * 商户操作员编号
     */
    @SerializedName("operator_id")
    private String operatorId;

    /**
     * 商户门店编号
     */
    @SerializedName("store_id")
    private String storeId;

    /**
     * 商户机具终端编号
     */
    @SerializedName("terminal_id")
    private String terminalId;

    /**
     * 业务扩展参数
     */
    @SerializedName("extend_params")
    private ExtendParams extendParams;

    /**
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。
     */
    @SerializedName("timeout_express")
    private String timeoutExpress;

    /**
     * 预授权确认模式，授权转交易请求中传入
     */
    @SerializedName("auth_confirm_mode")
    private String authConfirmMode;

    /**
     * 商户传入终端设备相关信息
     */
    @SerializedName("terminal_params")
    private String terminalParams;


}
