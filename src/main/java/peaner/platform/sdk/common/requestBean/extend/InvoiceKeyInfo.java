package peaner.platform.sdk.common.requestBean.extend;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description:
 * @Date: Created in 18-7-3 下午8:59
 **/
@Setter
@Getter
public class InvoiceKeyInfo {

    /**
     * 该交易是否支持开票
     */
    @SerializedName("is_support_invoice")
    private Boolean isSupportInvoice;

    /**
     * 开票商户名称
     */
    @SerializedName("invoice_merchant_name")
    private String invoiceMerchantName;

    /**
     * 税号
     */
    @SerializedName("tax_num")
    private String taxNum;

}
