package peaner.platform.sdk.common.requestBean.extend;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description:
 * @Date: Created in 18-7-3 下午6:48
 **/
@Setter
@Getter
public class ExtUserInfo {

    private String name;

    private String mobile;

    /**
     * 证件类型
     */
    @SerializedName("cert_type")
    private String certType;

    /**
     * 证件号
     */
    @SerializedName("cert_no")
    private String certNo;

    /**
     * 允许的最小买家年龄，买家年龄必须大于等于所传数值
     */
    @SerializedName("min_age")
    private String minAge;

    /**
     * 是否强制校验付款人身份信息
     */
    @SerializedName("fix_buyer")
    private String fixBuyer;

    /**
     * 是否强制校验身份信息
     */
    @SerializedName("need_check_info")
    private String needCheckInfo;


}
