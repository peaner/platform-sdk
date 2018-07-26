package peaner.platform.sdk.component.alipayComponent;

import com.google.gson.GsonBuilder;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import peaner.platform.sdk.api.alipay.pay.PcTradePayBizContentBean;
import peaner.platform.sdk.api.alipay.pay.PcTradePayReqBean;
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
 * @Description: 电脑网站支付接口实现
 * @Date: Created in 18-7-3 下午6:26
 **/
public class GetPcTradePayComponent {

    private AlibabaRequest pcTradePayRequestService;

    public GetPcTradePayComponent() {
        Retrofit retrofit = RetrofitUtils.getInstance().baseUrl("https://openapi.alipay.com/gateway.do/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addConverterFactory(new FileRequestBodyConverterFactory()).build();
        pcTradePayRequestService = RetrofitUtils.createService(retrofit, AlibabaRequest.class);
    }

    /**
     * 支付宝pc支付
     * @param pcTradePayReqBean pc支付实体
     * @param privateKey  私钥
     * @param aliPublicKey 公钥
     * @param inputCharset 编码
     * @return 返回页面信息
     * @throws PlatformException 异常信息
     */
    public String pcTradePayByAlibaba(PcTradePayReqBean pcTradePayReqBean, String privateKey,
                                      String aliPublicKey, String inputCharset) throws PlatformException {

        AliCommonReqBean aliCommonReqBean =
                BeanCopyUtils.copy(pcTradePayReqBean, AliCommonReqBean.class);

        PcTradePayReqBean pcTradePayRequestForm =
                BeanCopyUtils.copy(aliCommonReqBean, PcTradePayReqBean.class);

        pcTradePayRequestForm.setMethod("alipay.trade.page.pay");

        PcTradePayBizContentBean pcTradePayBizContentBean =
                BeanCopyUtils.copy(pcTradePayReqBean, PcTradePayBizContentBean.class);

        String bizContent = GsonUtils.toJson(pcTradePayBizContentBean);
        pcTradePayRequestForm.setBizContent(bizContent);

        // 签名处理
        String jsonBean = GsonUtils.toJson(pcTradePayRequestForm);
        String preSign = AlibabaUtils.createLinkString(Bean2MapUtils.buildStringMap(GsonUtils.jsonToMap(jsonBean)));
        pcTradePayRequestForm.setSign(
                AlibabaUtils.getSign(preSign, privateKey, inputCharset, pcTradePayReqBean.getSignType()));

        Call<String> pcTradePayResponseBeanCall = pcTradePayRequestService.getAlibabaPcTradePay(
                GsonUtils.jsonToMap(GsonUtils.toJson(pcTradePayRequestForm)));
        String pcTradePayResponseString = RetrofitUtils.info(pcTradePayResponseBeanCall);

        return pcTradePayResponseString;
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
