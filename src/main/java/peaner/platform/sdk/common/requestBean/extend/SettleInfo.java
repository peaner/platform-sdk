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
public class SettleInfo {

    /**
     * 结算详细信息
     */
    @SerializedName("settle_detail_infos")
    private SettleDetailInfo[] settle_detail_infos;

}
