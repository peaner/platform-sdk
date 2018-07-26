package peaner.platform.sdk.testController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import peaner.platform.sdk.api.alipay.auth.AuthBean;
import peaner.platform.sdk.api.alipay.auth.AuthReqBean;
import peaner.platform.sdk.api.alipay.auth.AuthRespBean;
import peaner.platform.sdk.api.alipay.auth.UrlReqBean;
import peaner.platform.sdk.api.alipay.pay.TradePayBean;
import peaner.platform.sdk.api.alipay.pay.TradePayReqBean;
import peaner.platform.sdk.api.alipay.pay.TradePayRespBean;
import peaner.platform.sdk.api.alipay.pay.advice.AdviceMsgBean;
import peaner.platform.sdk.component.alipayComponent.GetAuthComponent;
import peaner.platform.sdk.component.alipayComponent.GetSynAdviceComponent;
import peaner.platform.sdk.component.alipayComponent.GetTradePayComponent;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lilongzhou
 * @Description:
 * @Date: Created in 22:32 2018/7/25
 */
@Controller
@Slf4j
public class TestController {

    private final static String APPID = "XXXX(开发者appid)";

    private final static String URL = "https://openauth.alipay.com/oauth2/appToAppAuth.htm";

    private final static String REDIRECTURL = "http://XXXX(此处为自己的公网ip/域名)/connect/alipay/auth/callback";

    private final static String PUBLICKEY = "公钥";

    private final static String CALLBACKPUBLICKEY = "支付回调公钥";

    private final static String PRIVATEKEY = "私钥";

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/connect/alipay/auth")
    public String gotoAuth(Model model) {
        UrlReqBean urlReqBean = new UrlReqBean();
        urlReqBean.setAppId(APPID);
        urlReqBean.setRedirectURL(REDIRECTURL);
        String newUrl = new GetAuthComponent().authUrlAssemble(URL,"UTF-8" , urlReqBean);
        model.addAttribute("redirectUrl", newUrl);
        return "auth";
    }

    @GetMapping("/connect/alipay/auth/callback")
    public String alipayCallBack(HttpServletRequest request, Model model) {
        try {
            log.info("进入回调函数");
            // 获取到回调地址后面所带的参数 开发者appid 用户授权码 app_auth_code
            String appId = request.getParameter("app_id");
            String appAuthCode = request.getParameter("app_auth_code");
            AuthReqBean authReqBean = new AuthReqBean();
            authReqBean.setCharset("UTF-8");
            authReqBean.setAppId(appId);
            authReqBean.setGrantType("authorization_code");
            authReqBean.setCode(appAuthCode);
            authReqBean.setSignType("RSA2");
            authReqBean.setVersion("1.0");
            authReqBean.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            AuthRespBean authRespBean = new GetAuthComponent().authByAlibaba(authReqBean, PRIVATEKEY, PUBLICKEY, "UTF-8");
            AuthBean authBean = authRespBean.getAlipayOpenAuthTokenAppResponse();
            log.info("app_auth_token:", authBean.getAppAuthToken());
            model.addAttribute("callBack", authBean);
            return "afterAuth";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    @GetMapping("/connect/alipay/trade-pay")
    public String tradePayController (Model model) {
        try {

            TradePayReqBean tradePayReqBean = new TradePayReqBean();
            tradePayReqBean.setAppId(APPID);
            tradePayReqBean.setCharset("UTF-8");
            tradePayReqBean.setSignType("RSA2");
            tradePayReqBean.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            tradePayReqBean.setAppAuthToken("获取到的appAuthToken");
            tradePayReqBean.setVersion("1.0");
            // 支付后通知地址
            tradePayReqBean.setNotifyUrl("http://XXXX(此处为自己的公网ip/域名)/connect/alipay/tradePay/callback");
            tradePayReqBean.setOutTradeNo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            tradePayReqBean.setSellerId("卖家id");
            tradePayReqBean.setTotalAmount(0.01);
            tradePayReqBean.setSubject("IphoneX 128G");
            tradePayReqBean.setBody("apple iphones");

            TradePayRespBean tradePayRespBean = new GetTradePayComponent().tradePayByAlibaba(tradePayReqBean, PRIVATEKEY, PUBLICKEY, "UTF-8");
            TradePayBean tradePayBean = tradePayRespBean.getAlipayTradePrecreateResponse();
            model.addAttribute("tradePay", tradePayBean);
            return "tradePay";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @PostMapping("/connect/alipay/tradePay/callback")
    public String alipayReturnCallBack(HttpServletRequest request) {

        try {
            // request 转 map
            Map<String, String> params = request2Map(request);
            // 操作
            AdviceMsgBean msgBean = new GetSynAdviceComponent().synCallback(params, CALLBACKPUBLICKEY, "UTF-8");
            log.info("进行数据操作");
            // 数据操作
            return "success";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "fail";
    }

    private static Map<String, String> request2Map(HttpServletRequest request) {
        //获得参数的一个列举
        Enumeration en = request.getParameterNames();
        Map<String, String> map = new HashMap<>();
        //遍历列举来获取所有的参数
        while (en.hasMoreElements()) {
            String name = (String) en.nextElement();
            String value = request.getParameter(name);
            map.put(name, value);
        }
        return map;
    }

}
