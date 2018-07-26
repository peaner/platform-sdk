package peaner.platform.sdk.api.alipay.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import peaner.platform.sdk.common.requestBean.AliCommonReqBean;

/**
 * @Author: lilongzhou
 * @Description:
 * @Date: Created in 18-7-3 上午11:37
 **/
@Setter
@Getter
public class UrlReqBean extends AliCommonReqBean {


    /**
     * 商户自定义参数，用户授权后，重定向到redirect_uri时会原样回传给商户.
     */
    private String state;

    /**
     * 回调页面
     * 参数需要UrlEncode
     */
    @SerializedName("redirect_uri")
    private String redirectURL;

    /**
     * 应用类型
     * MOBILEAPP,WEBAPP,PUBLICAPP,TINYAPP,ARAPP
     */
    @SerializedName("application_type")
    private String applicationType;


}
