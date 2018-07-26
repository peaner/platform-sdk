package peaner.platform.sdk.component;

import peaner.platform.sdk.api.alipay.auth.AuthRespBean;
import peaner.platform.sdk.api.alipay.pay.MobileTradePayRespBean;
import peaner.platform.sdk.api.alipay.pay.TradePayRespBean;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

import java.util.Map;

/**
 * @Author: lilongzhou
 * @Description: 阿里第三方相关API
 * @Date: Created in 18-7-2 下午5:36
 **/
public interface AlibabaRequest {

    // 授权api,刷新
    @POST("https://openapi.alipay.com/gateway.do")
    Call<AuthRespBean> getAlibabaAuth(@QueryMap Map<String, Object> params);

    // 授权api,不刷新
    @POST("https://openapi.alipay.com/gateway.do")
    Call<AuthRespBean> getAlibabaAuthByNotRefresh(@QueryMap Map<String, Object> params);

    // 解绑授权通知
    /*@POST("https://openapi.alipay.com/gateway.do")
    Call<UnAuthResponseBean> getAlibabaUnAuth(@QueryMap Map<String, Object> params);*/

    // 当面付api
    // 统一收单交易支付接口
    @POST("https://openapi.alipay.com/gateway.do")
    Call<TradePayRespBean> getAlibabaTradePay(@QueryMap Map<String, Object> params);


    // 手机网站支付
    // 手机网站支付接口2.0
    @POST("https://openapi.alipay.com/gateway.do")
    Call<MobileTradePayRespBean> getAlibabaMobileTradePay(@QueryMap Map<String, Object> params);


    // 电脑网站支付
    // 统一收单下单并支付页面接口
    @POST("https://openapi.alipay.com/gateway.do")
    Call<String> getAlibabaPcTradePay(@QueryMap Map<String, Object> params);


}
