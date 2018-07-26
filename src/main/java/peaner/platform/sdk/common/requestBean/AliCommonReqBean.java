package peaner.platform.sdk.common.requestBean;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: lilongzhou
 * @Description: 阿里公共请求参数
 * @Date: Created in 18-7-2 下午2:17
 **/
@Setter
@Getter
@Slf4j
public class AliCommonReqBean {

    /**
     * 必填,支付宝分配给开发者的应用ID
     */
    @SerializedName("app_id")
    private String appId;

    /**
     * 必填,接口名称
     */
    private String method;

    /**
     * 仅支持JSON
     */
    private String format;

    /**
     * HTTP/HTTPS开头字符串
     */
    @SerializedName("return_url")
    private String returnURL;

    /**
     * 必填,请求使用的编码格式，如utf-8,gbk,gb2312等
     */
    private String charset;

    /**
     * 必填,商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     */
    @SerializedName("sign_type")
    private String signType;

    /**
     * 必填,商户请求参数的签名串
     */
    private String sign;

    /**
     * 必填,发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
     */
    private String timestamp;

    /**
     * 必填,调用的接口版本，固定为：1.0
     */
    private String version;

    /**
     * 支付宝服务器主动通知商户服务器里指定的页面http/https路径。建议商户使用https
     * 对于第三方开放平台,此参数是必填
     */
    @SerializedName("notify_url")
    private String notifyUrl;

    /**
     * 授权所需参数
     * 对于第三方开放平台,此参数是必填
     */
    @SerializedName("app_auth_token")
    private String appAuthToken;

    /**
     * 必填,业务请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递
     */
    @SerializedName("biz_content")
    private String bizContent;

}
