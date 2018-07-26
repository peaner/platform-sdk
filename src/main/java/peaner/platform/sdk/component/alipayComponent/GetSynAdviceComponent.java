package peaner.platform.sdk.component.alipayComponent;

import com.google.gson.Gson;
import peaner.platform.sdk.api.alipay.pay.advice.AdviceMsgBean;
import peaner.platform.sdk.common.ResponseAPIStatus;
import peaner.platform.sdk.exception.PlatformException;
import peaner.platform.sdk.utils.GsonUtils;
import peaner.platform.sdk.utils.sign.RSAUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lilongzhou
 * @Description:
 * @Date: Created in 18-7-6 下午6:35
 **/
public class GetSynAdviceComponent {

    /**
     * 同步/异步数据回调都可以使用
     * @param params 参数列表
     * @param aliPublicKey 支付宝功能公钥
     * @param inputCharset 编码
     * @return 返回数据
     * @throws PlatformException 异常处理
     */
    public AdviceMsgBean synCallback(Map<String, String> params,
                                     String aliPublicKey, String inputCharset) throws PlatformException {
        // 获取参数
        Map<String, String> bean = new HashMap<>(params);
        // 签名校验
        if (!RSAUtils.rsaCheckV1(params, aliPublicKey, inputCharset, "RSA2")) {
            throw new PlatformException(ResponseAPIStatus.CHECK_SIGN_ERROR);
        }
        // 将request参数列表转换成json
        String msgJson = GsonUtils.mapToJson(bean);
        // 将json转换成bean
        Gson gson = new Gson();
        // 返回实体信息
        return gson.fromJson(msgJson, AdviceMsgBean.class);
    }


}
