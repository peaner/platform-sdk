package peaner.platform.sdk.common.requestBean.extend;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description:
 * @Date: Created in 18-7-3 下午5:20
 **/
@Setter
@Getter
public class ExtendParams {

    /**
     * 系统商编号
     */
    @SerializedName("sys_service_provider_id")
    private String sysServiceProviderId;

    /**
     * 使用花呗分期要进行的分期数
     */
    @SerializedName("hb_fq_num")
    private String hbFqNum;

    /**
     * 使用花呗分期需要卖家承担的手续费比例的百分值，传入100代表100%
     */
    @SerializedName("hb_fq_seller_percent")
    private String hbFqSellerPercent;

    /**
     * 行业数据回流信息
     */
    @SerializedName("industry_reflux_info")
    private String industryRefluxInfo;

    /**
     * 卡类型
     */
    @SerializedName("card_type")
    private String cardType;

}
