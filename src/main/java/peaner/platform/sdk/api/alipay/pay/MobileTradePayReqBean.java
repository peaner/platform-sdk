package peaner.platform.sdk.api.alipay.pay;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import peaner.platform.sdk.common.requestBean.AliCommonReqBean;
import peaner.platform.sdk.common.requestBean.extend.ExtUserInfo;
import peaner.platform.sdk.common.requestBean.extend.ExtendParams;
import peaner.platform.sdk.common.requestBean.extend.InvoiceInfo;
import peaner.platform.sdk.common.requestBean.extend.RoyaltyInfo;
import peaner.platform.sdk.common.requestBean.extend.SettleInfo;
import peaner.platform.sdk.common.requestBean.extend.SubMerchant;

/**
 * @Author: lilongzhou
 * @Description: 手机网站支付请求参数
 * @Date: Created in 18-7-3 下午4:51
 **/
@Setter
@Getter
public class MobileTradePayReqBean extends AliCommonReqBean {

    /**
     * 订单描述
     */
    private String body;

    /**
     * 订单标题
     */
    private String subject;

    /**
     * 商户订单号
     */
    @SerializedName("out_trade_no")
    private String outTradeNo;

    /**
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。
     */
    @SerializedName("timeout_express")
    private String timeoutExpress;

    /**
     * 绝对超时时间
     */
    @SerializedName("time_expire")
    private String timeExpire;

    /**
     * 订单总金额，单位为元，
     */
    @SerializedName("total_amount")
    private Double totalAmount;

    /**
     * 如果该值为空，则默认为商户签约账号对应的支付宝用户ID
     */
    @SerializedName("seller_id")
    private String sellerId;

    /**
     * 针对用户授权接口，获取用户相关数据时，用于标识用户授权关系
     */
    @SerializedName("auth_token")
    private String authToken;

    /**
     * 商品主类型 :0-虚拟类商品,1-实物类商品
     */
    @SerializedName("goods_type")
    private String goodsType;

    /**
     * 公用回传参数
     */
    @SerializedName("passback_params")
    private String passbackParams;

    /**
     * 用户付款中途退出返回商户网站的地址
     */
    @SerializedName("quit_url")
    private String quitUrl;

    /**
     * 销售产品码
     */
    @SerializedName("product_code")
    private String productCode;

    /**
     * 优惠参数
     * 注：仅与支付宝协商后可用
     */
    @SerializedName("promo_params")
    private String promoParams;

    /**
     * 描述分账信息
     */
    @SerializedName("royalty_info")
    private RoyaltyInfo royaltyInfo;

    /**
     * 业务扩展参数
     */
    @SerializedName("extend_params")
    private ExtendParams extendParams;

    /**
     * 间连受理商户信息体
     */
    @SerializedName("sub_merchant")
    private SubMerchant subMerchant;

    /**
     * 可用渠道，用户只能在指定渠道范围内支付
     */
    @SerializedName("enable_pay_channels")
    private String enablePayChannels;

    /**
     * 禁用渠道，用户不可用指定渠道支付
     */
    @SerializedName("disable_pay_channels")
    private String disablePayChannels;

    /**
     * 商户门店编号
     */
    @SerializedName("store_id")
    private String storeId;

    /**
     * 描述结算信息
     */
    @SerializedName("settle_info")
    private SettleInfo settleInfo;

    /**
     * 开票信息
     */
    @SerializedName("invoice_info")
    private InvoiceInfo invoiceInfo;

    /**
     * 指定渠道，目前仅支持传入pcredit
     */
    @SerializedName("specified_channel")
    private String specifiedChannel;

    /**
     * 商户传入业务信息
     */
    @SerializedName("business_params")
    private String businessParams;

    /**
     * 外部指定买家
     */
    @SerializedName("ext_user_info")
    private ExtUserInfo extUserInfo;


}
