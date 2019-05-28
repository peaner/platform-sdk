package peaner.platform.sdk.component;

import peaner.platform.sdk.common.requestBean.weixin.AuthorizerInfoReqBean;
import peaner.platform.sdk.common.requestBean.weixin.AuthorizerOptionReqBean;
import peaner.platform.sdk.common.requestBean.weixin.AuthorizerTokenReqBean;
import peaner.platform.sdk.common.requestBean.weixin.QueryAuthInfoReqBean;
import peaner.platform.sdk.common.requestBean.weixin.ComponentAccessTokenReqBean;
import peaner.platform.sdk.common.requestBean.weixin.PreAuthCodeReqBean;
import peaner.platform.sdk.common.requestBean.weixin.SetAuthorizerOptionReqBean;
import peaner.platform.sdk.common.responseBean.weixin.AuthorizerInfoRespBean;
import peaner.platform.sdk.common.responseBean.weixin.AuthorizerOptionRespBean;
import peaner.platform.sdk.common.responseBean.weixin.AuthorizerTokenRespBean;
import peaner.platform.sdk.common.responseBean.weixin.QueryAuthInfoRespBean;
import peaner.platform.sdk.common.responseBean.weixin.ComponentAccessTokenRespBean;
import peaner.platform.sdk.common.responseBean.weixin.PreAuthCodeRespBean;
import peaner.platform.sdk.common.responseBean.weixin.SetAuthorizerOptionRespBean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @Author: lilongzhou
 * @Description: 微信第三方相关API
 * @Date: Created in 上午11:22 2018/11/11
 */
public interface WeiXinRequest {

    final static String BASE_URL = "https://api.weixin.qq.com/cgi-bin/component/";

    // 授权

    // 1. 推送component_verify_ticket协议

    // 2. 获取第三方平台component_access_token
    @POST("api_component_token")
    Call<ComponentAccessTokenRespBean> getComponentAccessToken(@Body ComponentAccessTokenReqBean componentAccessTokenReqBean);

    // 3. 获取预授权码pre_auth_code
    @POST("api_create_preauthcode")
    Call<PreAuthCodeRespBean> getPreAuthCode(@Body PreAuthCodeReqBean preAuthCodeReqBean, @Query("component_access_token") String componentAccessToken);

    // 4. 使用授权码换取公众号或小程序的接口调用凭据和授权信息
    @POST("api_query_auth")
    Call<QueryAuthInfoRespBean> getAuthInfo(@Body QueryAuthInfoReqBean queryAuthInfoReqBean, @Query("component_access_token") String componentAccessToken);

    // 5. 获取（刷新）授权公众号或小程序的接口调用凭据（令牌）
    @POST("api_authorizer_token")
    Call<AuthorizerTokenRespBean> getAuthorizerToken(@Body AuthorizerTokenReqBean authorizerTokenReqBean, @Query("component_access_token") String componentAccessToken);

    // 6. 获取授权方的帐号基本信息
    @POST("api_get_authorizer_info")
    Call<AuthorizerInfoRespBean> getAuthorizerInfo(@Body AuthorizerInfoReqBean authorizerInfoReqBean, @Query("component_access_token") String componentAccessToken);

    // 7. 获取授权方的选项设置信息
    @POST("api_get_authorizer_option")
    Call<AuthorizerOptionRespBean> getAuthorizerOption(@Body AuthorizerOptionReqBean authorizerOptionReqBean, @Query("component_access_token") String componentAccessToken);

    // 8. 设置授权方的选项信息
    @POST("api_set_authorizer_option")
    Call<SetAuthorizerOptionRespBean> setAuthorizerOption(@Body SetAuthorizerOptionReqBean setAuthorizerOptionReqBean, @Query("component_access_token") String componentAccessToken);

    // 9. 推送授权相关通知
}
