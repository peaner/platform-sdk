package peaner.platform.sdk.component.alipayComponent;

import com.google.gson.GsonBuilder;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import peaner.platform.sdk.api.alipay.pay.MobileTradeBizContentBean;
import peaner.platform.sdk.api.alipay.pay.MobileTradePayReqBean;
import peaner.platform.sdk.api.alipay.pay.MobileTradePayRespBean;
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
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lilongzhou
 * @Description: 手机网站支付接口实现
 * @Date: Created in 18-7-3 下午6:26
 **/
public class GetMobileTradePayComponent {

    private AlibabaRequest mobileTradePayRequestService;

    public GetMobileTradePayComponent() {
        Retrofit retrofit = RetrofitUtils.getInstance().baseUrl("https://openapi.alipay.com/gateway.do/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addConverterFactory(new FileRequestBodyConverterFactory()).build();
        mobileTradePayRequestService = RetrofitUtils.createService(retrofit, AlibabaRequest.class);
    }

    /**
     * 支付宝手机网站支付接口
     * @param mobileTradePayReqBean 手机网站支付参数
     * @param privateKey 私钥
     * @param aliPublicKey 公钥
     * @param inputCharset 编码
     * @return 返回参数
     * @throws PlatformException 异常信息
     */
    public Map<String, Object> mobileTradePayByAlibaba(MobileTradePayReqBean mobileTradePayReqBean, String privateKey,
                                                       String aliPublicKey, String inputCharset) throws PlatformException {

        AliCommonReqBean aliCommonReqBean =
                BeanCopyUtils.copy(mobileTradePayReqBean, AliCommonReqBean.class);
        MobileTradePayReqBean mobileTradePayRequestForm =
                BeanCopyUtils.copy(aliCommonReqBean, MobileTradePayReqBean.class);
        mobileTradePayRequestForm.setMethod("alipay.trade.wap.pay");

        MobileTradeBizContentBean mobileTradeBizContentBean =
                BeanCopyUtils.copy(mobileTradePayReqBean, MobileTradeBizContentBean.class);
        String bizContent = GsonUtils.toJson(mobileTradeBizContentBean);
        mobileTradePayRequestForm.setBizContent(bizContent);

        // 签名处理
        String jsonBean = GsonUtils.toJson(mobileTradePayRequestForm);
        String preSign = AlibabaUtils.createLinkString(Bean2MapUtils.buildStringMap(GsonUtils.jsonToMap(jsonBean)));
        mobileTradePayRequestForm.setSign(
                AlibabaUtils.getSign(preSign, privateKey, inputCharset, mobileTradePayReqBean.getSignType()));

        Call<MobileTradePayRespBean> mobileTradePayResponseBeanCall =
                mobileTradePayRequestService.getAlibabaMobileTradePay(
                        GsonUtils.jsonToMap(GsonUtils.toJson(mobileTradePayRequestForm)));
        MobileTradePayRespBean mobileTradePayRespBean =
                RetrofitUtils.info(mobileTradePayResponseBeanCall);

        // 签名验证
        String MobileTradePayJsonBean = GsonUtils.toJson(mobileTradePayRespBean.getAlipayTradeWapPayResponse());
        String MobileTradePayPreSign = AlibabaUtils.createLinkString(Bean2MapUtils.buildStringMap(GsonUtils.jsonToMap(MobileTradePayJsonBean)));
        if (!AlibabaUtils.signVerify(MobileTradePayPreSign,
                AlibabaUtils.getSign(MobileTradePayPreSign, privateKey, inputCharset, mobileTradePayReqBean.getSignType()), aliPublicKey, inputCharset, mobileTradePayReqBean.getSignType())) {
            throw new PlatformException(ResponseAPIStatus.CHECK_SIGN_ERROR);
        }
        // 响应参数的包装
        Map<String, Object> map = new HashMap<>();
        map.put("sign", mobileTradePayRespBean.getSign());
        map.put("alipay_trade_precreate_response", mobileTradePayRespBean.getAlipayTradeWapPayResponse());
        return map;
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
