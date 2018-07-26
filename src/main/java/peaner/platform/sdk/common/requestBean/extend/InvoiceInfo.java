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
public class InvoiceInfo {

    /**
     * 开票关键信息
     */
    @SerializedName("key_info")
    private InvoiceKeyInfo keyInfo;

    /**
     * 开票内容
     */
    private String details;

}
