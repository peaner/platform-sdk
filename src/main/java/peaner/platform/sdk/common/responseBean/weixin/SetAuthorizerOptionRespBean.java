package peaner.platform.sdk.common.responseBean.weixin;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description: 设置授权方的选项信息返回参数
 * @Date: Created in 下午4:07 2018/11/11
 */
@Setter
@Getter
public class SetAuthorizerOptionRespBean {

    /**
     * 错误码
     */
    @SerializedName("errcode")
    private String errCode;

    /**
     * 错误信息
     */
    @SerializedName("errmsg")
    private String errMsg;

}
