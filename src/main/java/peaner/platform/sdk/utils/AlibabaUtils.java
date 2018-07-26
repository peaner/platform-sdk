package peaner.platform.sdk.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import peaner.platform.sdk.exception.PlatformException;
import peaner.platform.sdk.utils.sign.RSAUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lilongzhou
 * @Description: 阿里API相关工具类
 * @Date: Created in 18-7-3 上午10:40
 **/
@Slf4j
public class AlibabaUtils {

    /**
     * 校验签名
     * @param preSign      待签名数据
     * @param sign         签名值
     * @param aliPublicKey 支付宝公钥
     * @param inputCharset 编码格式
     * @param signType 签名方式
     * @return 布尔值
     * @throws PlatformException 异常信息
     */
    public static boolean signVerify(String preSign, String sign,
                                     String aliPublicKey, String inputCharset, String signType)
            throws PlatformException {
        return RSAUtils.rsaCheck(preSign, sign, aliPublicKey, inputCharset, signType);
    }


    /**
     * 获取签名
     * @param preSign      待签名数据
     * @param privateKey   商户私钥
     * @param inputCharset 编码格式
     * @return 签名值
     * @param signType     签名类型
     * @throws PlatformException 异常信息
     */
    public static String getSign(String preSign, String privateKey, String inputCharset, String signType) throws PlatformException {
        return RSAUtils.rsaSign(preSign, privateKey, inputCharset, signType);
    }


    /**
     * 处理URL路径
     *
     * @param url          需处理的url
     * @param inputCharset 编码格式
     * @return 处理后的url
     */
    public static String urlEncodeString(String url, String inputCharset) {
        try {
            return URLEncoder.encode(url, inputCharset);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 处理URL路径
     * @param url 路径
     * @param inputCharset 编码
     * @return 返回值
     */
    public static String urlDecodeString(String url, String inputCharset) {
        try {
            return URLDecoder.decode(url, inputCharset);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    /**
     * 拼接参数
     * @param params map参数
     * @return 拼接后的字符串
     */
    public static String createLinkString(Map<String, String> params) {
        params = paramsFilter(params);
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);

        String resultStr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {
                resultStr = resultStr + key + "=" + value;
            } else {
                resultStr = resultStr + key + "=" + value + "&";
            }
        }
        return resultStr;
    }

    /**
     * 去除参数列表中的空值和签名参数
     *
     * @param arrays 签名参数列表
     * @return 去掉空值与签名参数后的新签名参数列表
     */
    private static Map<String, String> paramsFilter(Map<String, String> arrays) {
        Map<String, String> resultMap = new HashMap<String, String>();

        if (arrays == null || arrays.size() <= 0) {
            return resultMap;
        }

        for (String key : arrays.keySet()) {
            String value = arrays.get(key);
            if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)
                    || "sign".equalsIgnoreCase(key)) {
                continue;
            }
            resultMap.put(key, value);
        }
        return resultMap;
    }


    /*public static Map<String, String> request2Map(HttpServletRequest request) {
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
    }*/


}
