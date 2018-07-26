package peaner.platform.sdk.common.requestBean.extend;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description:
 * @Date: Created in 18-7-3 下午8:55
 **/
@Setter
@Getter
public class SettleDetailInfo {

    /**
     * 结算收款方的账户类型
     */
    @SerializedName("trans_in_type")
    private String transInType;

    /**
     * 结算收款方
     */
    @SerializedName("trans_in")
    private String transIn;

    /**
     * 结算汇总维度，按照这个维度汇总成批次结算，由商户指定
     */
    @SerializedName("summary_dimension")
    private String summaryDimension;

    /**
     * 结算的金额，单位为元
     */
    private Double amount;

}
