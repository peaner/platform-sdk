package peaner.platform.sdk.common.requestBean.extend;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description:
 * @Date: Created in 18-7-3 下午6:49
 **/
@Setter
@Getter
public class SubMerchant {

    /**
     * 间连受理商户的支付宝商户编号，通过间连商户入驻后得到
     */
    @SerializedName("merchant_id")
    private String merchantId;

    /**
     * 商户id类型
     */
    @SerializedName("merchant_type")
    private String merchantType;


}
