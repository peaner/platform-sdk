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
public class RoyaltyInfo {

    /**
     * 分账类型
     */
    @SerializedName("royalty_type")
    private String royaltyType;

    /**
     * 分账明细的信息
     */
    @SerializedName("royalty_detail_infos")
    private RoyaltyDetailInfos[] royaltyDetailInfos;

}
