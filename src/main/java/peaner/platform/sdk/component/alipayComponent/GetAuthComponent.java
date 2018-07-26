package peaner.platform.sdk.component.alipayComponent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import peaner.platform.sdk.api.alipay.auth.AuthReqBean;
import peaner.platform.sdk.api.alipay.auth.AuthRespBean;
import peaner.platform.sdk.api.alipay.auth.BizContentBean;
import peaner.platform.sdk.api.alipay.auth.UrlReqBean;
import peaner.platform.sdk.common.Constants;
import peaner.platform.sdk.common.ResponseAPIStatus;
import peaner.platform.sdk.common.requestBean.AliCommonReqBean;
import peaner.platform.sdk.component.AlibabaRequest;
import peaner.platform.sdk.exception.PlatformException;
import peaner.platform.sdk.utils.AlibabaUtils;
import peaner.platform.sdk.utils.Bean2MapUtils;
import peaner.platform.sdk.utils.BeanCopyUtils;
import peaner.platform.sdk.utils.GsonUtils;
import peaner.platform.sdk.utils.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @Author: lilongzhou
 * @Description: 授权实现
 * @Date: Created in 18-7-2 下午10:08
 **/
@Slf4j
public class GetAuthComponent {

    private AlibabaRequest authRequestService;

    public GetAuthComponent() {
        Retrofit retrofit = RetrofitUtils.getInstance().baseUrl("https://openapi.alipay.com/gateway.do/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addConverterFactory(new FileRequestBodyConverterFactory()).build();
        authRequestService = RetrofitUtils.createService(retrofit, AlibabaRequest.class);
    }

    /**
     * 支付宝URL拼装
     *
     * @param url          请求地址
     * @param inputCharset 编码
     * @param UrlReqBean   请求参数
     * @return 返回处理后url
     */
    public String authUrlAssemble(String url, String inputCharset, UrlReqBean UrlReqBean) {
        // 并且对回调路径进行加密
        String redirectUrl = AlibabaUtils.urlEncodeString(UrlReqBean.getRedirectURL(), inputCharset);
        UrlReqBean.setRedirectURL(redirectUrl);
        Gson gson = new GsonBuilder().create();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        // 拼接url
        return url + "?" + AlibabaUtils.createLinkString(gson.fromJson(gson.toJson(UrlReqBean), type));
    }

    /**
     * 支付宝请求授权接口
     *
     * @param authReqBean  授权请求参数
     * @param privateKey   商家私钥
     * @param aliPublicKey 支付宝公钥
     * @param inputCharset 编码
     * @return 响应实体信息
     * @throws PlatformException 异常信息
     */
    public AuthRespBean authByAlibaba(AuthReqBean authReqBean, String privateKey,
                                      String aliPublicKey, String inputCharset) throws PlatformException {
        // 参数
        AliCommonReqBean aliCommonReqBean =
                BeanCopyUtils.copy(authReqBean, AliCommonReqBean.class);

        AuthReqBean authRequestForm =
                BeanCopyUtils.copy(aliCommonReqBean, AuthReqBean.class);

        authRequestForm.setMethod("alipay.open.auth.token.app");
        BizContentBean bizContentBean = BeanCopyUtils.copy(authReqBean, BizContentBean.class);
        String bizContent = GsonUtils.toJson(bizContentBean);
        authRequestForm.setBizContent(bizContent);

        // 签名处理
        String jsonBean = GsonUtils.toJson(authRequestForm);
        String preSign = AlibabaUtils.createLinkString(
                Bean2MapUtils.buildStringMap(GsonUtils.jsonToMap(jsonBean)));
        authRequestForm.setSign(AlibabaUtils.getSign(preSign, privateKey, inputCharset, authReqBean.getSignType()));

        AuthRespBean authRespBean = new AuthRespBean();
        if (Constants.REFRESH_TOKE.equals(authReqBean.getGrantType())) {
            Call<AuthRespBean> call =
                    authRequestService.getAlibabaAuth(GsonUtils.jsonToMap(GsonUtils.toJson(authRequestForm)));
            authRespBean = RetrofitUtils.info(call);
            // 签名验证
            String authJsonBean = GsonUtils.toJson(authRespBean.getAlipayOpenAuthTokenAppResponse());
            String authPreSign =
                    AlibabaUtils.createLinkString(Bean2MapUtils.buildStringMap(GsonUtils.jsonToMap(authJsonBean)));
            if (!AlibabaUtils.signVerify(authPreSign, AlibabaUtils.getSign(
                    authPreSign, privateKey, inputCharset, authReqBean.getSignType()),
                    aliPublicKey, inputCharset, authReqBean.getSignType())) {
                throw new PlatformException(ResponseAPIStatus.CHECK_SIGN_ERROR);
            }
        }

        if (Constants.AUTHORIZATION_CODE.equals(authReqBean.getGrantType())) {
            Call<AuthRespBean> call =
                    authRequestService.getAlibabaAuthByNotRefresh(GsonUtils.jsonToMap(GsonUtils.toJson(authRequestForm)));
            authRespBean = RetrofitUtils.info(call);
            // 签名验证
            String authJsonBean = GsonUtils.toJson(authRespBean.getAlipayOpenAuthTokenAppResponse());
            String authPreSign =
                    AlibabaUtils.createLinkString(Bean2MapUtils.buildStringMap(GsonUtils.jsonToMap(authJsonBean)));
            if (!AlibabaUtils.signVerify(authPreSign, AlibabaUtils.getSign(
                    authPreSign, privateKey, inputCharset, authReqBean.getSignType()),
                    aliPublicKey, inputCharset, authReqBean.getSignType())) {
                throw new PlatformException(ResponseAPIStatus.CHECK_SIGN_ERROR);
            }
        }
        return authRespBean;
    }


    static class FileRequestBodyConverterFactory extends Converter.Factory {
        @Override
        public Converter<File, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            return new FileRequestBodyConverter();
        }
    }

    static class FileRequestBodyConverter implements Converter<File, RequestBody> {
        @Override
        public RequestBody convert(File file) throws IOException {
            return RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        }
    }

}
