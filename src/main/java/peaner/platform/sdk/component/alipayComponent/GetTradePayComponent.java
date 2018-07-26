package peaner.platform.sdk.component.alipayComponent;

import com.google.gson.GsonBuilder;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import peaner.platform.sdk.api.alipay.pay.TradePayBizContentBean;
import peaner.platform.sdk.api.alipay.pay.TradePayReqBean;
import peaner.platform.sdk.api.alipay.pay.TradePayRespBean;
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

/**
 * @Author: lilongzhou
 * @Description: 当面付接口实现
 * @Date: Created in 18-7-3 下午4:57
 **/
public class GetTradePayComponent {

    private AlibabaRequest tradePayRequestService;

    public GetTradePayComponent() {
        Retrofit retrofit = RetrofitUtils.getInstance().baseUrl("https://openapi.alipay.com/gateway.do/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addConverterFactory(new FileRequestBodyConverterFactory()).build();
        tradePayRequestService = RetrofitUtils.createService(retrofit, AlibabaRequest.class);
    }


    /**
     * 支付宝当面付接口
     * @param tradePayReqBean 当面付请求参数
     * @param privateKey 私钥
     * @param aliPublicKey 公钥
     * @param inputCharset 编码
     * @return 返回值
     * @throws PlatformException 异常信息
     */
    public TradePayRespBean tradePayByAlibaba(TradePayReqBean tradePayReqBean, String privateKey,
                                              String aliPublicKey, String inputCharset) throws PlatformException {

        // 请求参数
        AliCommonReqBean aliCommonReqBean =
                BeanCopyUtils.copy(tradePayReqBean, AliCommonReqBean.class);
        TradePayReqBean tradePayRequestForm =
                BeanCopyUtils.copy(aliCommonReqBean, TradePayReqBean.class);
        tradePayRequestForm.setMethod("alipay.trade.precreate");

        TradePayBizContentBean tradePayBizContentBean =
                BeanCopyUtils.copy(tradePayReqBean, TradePayBizContentBean.class);
        String bizContent = GsonUtils.toJson(tradePayBizContentBean);
        tradePayRequestForm.setBizContent(bizContent);

        // 签名处理
        String jsonBean = GsonUtils.toJson(tradePayRequestForm);
        String preSign = AlibabaUtils.createLinkString(Bean2MapUtils.buildStringMap(GsonUtils.jsonToMap(jsonBean)));
        tradePayRequestForm.setSign(AlibabaUtils.getSign(preSign, privateKey, inputCharset, tradePayReqBean.getSignType()));
        Call<TradePayRespBean> tradePayResponseBeanCall =
                tradePayRequestService.getAlibabaTradePay(GsonUtils.jsonToMap(GsonUtils.toJson(tradePayRequestForm)));
        TradePayRespBean tradePayRespBean = RetrofitUtils.info(tradePayResponseBeanCall);
        // 签名验证
        String tradeJsonBean = GsonUtils.toJson(tradePayRespBean.getAlipayTradePrecreateResponse());
        String tradePreSign = AlibabaUtils.createLinkString(Bean2MapUtils.buildStringMap(GsonUtils.jsonToMap(tradeJsonBean)));

        if (!AlibabaUtils.signVerify(tradePreSign, AlibabaUtils.getSign(
                tradePreSign, privateKey, inputCharset, tradePayReqBean.getSignType()),
                aliPublicKey, inputCharset, tradePayReqBean.getSignType())) {
            throw new PlatformException(ResponseAPIStatus.CHECK_SIGN_ERROR);
        }
        return tradePayRespBean;
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
