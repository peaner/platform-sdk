package peaner.platform.sdk.api.alipay.pay;

import lombok.Getter;
import lombok.Setter;
import peaner.platform.sdk.common.responseBean.AliCommonRespBean;
import peaner.platform.sdk.common.responseBean.TradeFundBill;
import peaner.platform.sdk.common.responseBean.VoucherDetail;

import java.util.Date;

/**
 * @Author: lilongzhou
 * @Description: 电脑网站支付响应参数
 * @Date: Created in 18-7-3 下午4:53
 **/
@Setter
@Getter
public class PcTradePayRespBean extends AliCommonRespBean {

    private String tradeNo;

    private String out_trade_no;

    private String buyer_logon_id;

    private Double total_amount;

    private String trans_currency;

    private String settle_currency;

    private String settle_amount;

    private String pay_currency;

    private String pay_amount;

    private String settle_trans_rate;

    private String trans_pay_rate;

    private String receipt_amount;

    private Double buyer_pay_amount;

    private Double point_amount;

    private Double invoice_amount;

    private Date gmt_payment;

    private TradeFundBill fund_bill_list;

    private Double card_balance;

    private String store_name;

    private String buyer_user_id;

    private String discount_goods_detail;

    private VoucherDetail voucher_detail_list;

    private String auth_trade_pay_mode;

    private String business_params;

    private String buyer_user_type;

    private String mdiscount_amount;

    private String discount_amount;

}
