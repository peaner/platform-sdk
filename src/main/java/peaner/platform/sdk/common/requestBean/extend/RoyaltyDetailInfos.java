package peaner.platform.sdk.common.requestBean.extend;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description:
 * @Date: Created in 18-7-3 下午6:52
 **/
@Setter
@Getter
public class RoyaltyDetailInfos {

    /**
     * 分账序列号
     */
    @SerializedName("serial_no")
    private Integer serialNo;

    /**
     * 接受分账金额的账户类型
     */
    @SerializedName("trans_in_type")
    private String transInType;

    /**
     * 分账批次号
     */
    @SerializedName("batch_no")
    private String batchNo;

    /**
     * 商户分账的外部关联号，用于关联到每一笔分账信息，商户需保证其唯一性
     */
    @SerializedName("out_relation_id")
    private String outRelationId;

    /**
     * 要分账的账户类型
     */
    @SerializedName("trans_out_type")
    private String transOutType;

    /**
     * 如果转出账号类型为userId，本参数为要分账的支付宝账号对应的支付宝唯一用户号
     */
    @SerializedName("trans_out")
    private String transOut;

    /**
     * 如果转入账号类型为userId，本参数为接受分账金额的支付宝账号对应的支付宝唯一用户号
     */
    @SerializedName("trans_in")
    private String transIn;

    /**
     * 分账的金额，单位为元
     */
    private Integer amount;

    /**
     * 分账描述信息
     */
    private String desc;

    /**
     * 分账的比例，值为20代表按20%的比例分账
     */
    @SerializedName("amount_percentage")
    private String amountPercentage;

}
