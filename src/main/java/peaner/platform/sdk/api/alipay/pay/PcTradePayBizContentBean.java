package peaner.platform.sdk.api.alipay.pay;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import peaner.platform.sdk.common.requestBean.extend.ExtendParams;
import peaner.platform.sdk.common.requestBean.extend.GoodsDetail;

/**
 * @Author: lilongzhou
 * @Description: 电脑网站支付请求参数bizContent
 * @Date: Created in 18-7-3 下午4:51
 **/
@Setter
@Getter
public class PcTradePayBizContentBean {

    /**
     * 商户订单号
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     * 销售产品码
     */
    @SerializedName("product_code")
    private String productCode;

    /**
     * 订单总金额，单位为元，
     */
    @SerializedName("total_amount")
    private Double totalAmount;

    /**
     * 订单标题
     */
    private String subject;

    /**
     * 订单描述
     */
    private String body;


    /**
     * 订单包含的商品列表信息
     */
    @SerializedName("goods_detail")
    private GoodsDetail[] goodsDetail;

    @SerializedName("passback_params")
    private String passbackParams;

    /**
     * 业务扩展参数
     */
    @SerializedName("extend_params")
    private ExtendParams extendParams;

    @SerializedName("goodsType")
    private String goodsType;

    /**
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。
     */
    @SerializedName("timeout_express")
    private String timeoutExpress;

    @SerializedName("enable_pay_channels")
    private String enablePayChannels;


    @SerializedName("disable_pay_channels")
    private String disablePayChannels;

    @SerializedName("auth_token")
    private String authToken;

    @SerializedName("qr_pay_mode")
    private String qrPayMode;

    @SerializedName("qrcode_width")
    private String qrcodeWidth;


}
