package peaner.platform.sdk.utils.sign;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import peaner.platform.sdk.common.AlibabaConstnts;
import peaner.platform.sdk.common.ResponseAPIStatus;
import peaner.platform.sdk.exception.PlatformException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Author: lilongzhou
 * @Description:
 * @Date: Created in 18-7-5 上午10:48
 **/
@Slf4j
public class RSAUtils {

    /**
     * rsa内容签名
     * @param content 内容
     * @param privateKey 私钥
     * @param charset 编码
     * @param signType 签名类型
     * @return 返回值
     * @throws PlatformException 异常信息
     */
    public static String rsaSign(String content, String privateKey, String charset,
                                 String signType) throws PlatformException {
        if (AlibabaConstnts.SIGN_TYPE_RSA.equals(signType)) {

            return rsaSign(content, privateKey, charset);
        } else if (AlibabaConstnts.SIGN_TYPE_RSA2.equals(signType)) {

            return rsa256Sign(content, privateKey, charset);
        } else {
            throw new PlatformException(ResponseAPIStatus.GENERATE_SIGN_ERROR);
        }
    }

    /**
     * sha256WithRsa 加签
     * @param content 内容
     * @param privateKey 私钥
     * @param charset 编码
     * @return 返回值
     * @throws PlatformException 异常信息
     */
    public static String rsa256Sign(String content, String privateKey,
                                    String charset) throws PlatformException {

        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8(AlibabaConstnts.SIGN_TYPE_RSA,
                    new ByteArrayInputStream(privateKey.getBytes()));

            java.security.Signature signature = java.security.Signature
                    .getInstance(AlibabaConstnts.SIGN_SHA256RSA_ALGORITHMS);

            signature.initSign(priKey);

            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }

            byte[] signed = signature.sign();

            return new String(Base64.encodeBase64(signed));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new PlatformException(ResponseAPIStatus.GENERATE_SIGN_ERROR);
        }

    }

    /**
     * sha1WithRsa 加签
     * @param content 内容
     * @param privateKey 私钥
     * @param charset 编码
     * @return 返回值
     * @throws PlatformException 异常信息
     */
    public static String rsaSign(String content, String privateKey,
                                 String charset) throws PlatformException {
        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8(AlibabaConstnts.SIGN_TYPE_RSA,
                    new ByteArrayInputStream(privateKey.getBytes()));

            java.security.Signature signature = java.security.Signature
                    .getInstance(AlibabaConstnts.SIGN_ALGORITHMS);

            signature.initSign(priKey);

            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }

            byte[] signed = signature.sign();

            return new String(Base64.encodeBase64(signed));
        } catch (InvalidKeySpecException ie) {
            throw new PlatformException(ResponseAPIStatus.GENERATE_SIGN_ERROR);
        } catch (Exception e) {
            throw new PlatformException(ResponseAPIStatus.GENERATE_SIGN_ERROR);
        }
    }

    public static PrivateKey getPrivateKeyFromPKCS8(String algorithm,
                                                    InputStream ins) throws Exception {
        if (ins == null || StringUtils.isEmpty(algorithm)) {
            return null;
        }

        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        byte[] encodedKey = StreamUtil.readText(ins).getBytes();

        encodedKey = Base64.decodeBase64(encodedKey);

        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
    }

    public static PublicKey getPublicKeyFromX509(String algorithm,
                                                 InputStream ins) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);

        StringWriter writer = new StringWriter();
        StreamUtil.io(new InputStreamReader(ins), writer);

        byte[] encodedKey = writer.toString().getBytes();

        encodedKey = Base64.decodeBase64(encodedKey);

        return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
    }


    /**
     * rsa内容验签
     * @param content   待验内容
     * @param sign      签名
     * @param publicKey 公钥
     * @param charset   编码
     * @param signType  签名类型
     * @return 验证是否成功
     * @throws PlatformException 异常信息
     */
    public static boolean rsaCheck(String content, String sign, String publicKey, String charset,
                                   String signType) throws PlatformException {

        if (AlibabaConstnts.SIGN_TYPE_RSA.equals(signType)) {

            return rsaCheckContent(content, sign, publicKey, charset);

        } else if (AlibabaConstnts.SIGN_TYPE_RSA2.equals(signType)) {

            return rsa256CheckContent(content, sign, publicKey, charset);

        } else {

            throw new PlatformException(ResponseAPIStatus.CHECK_SIGN_ERROR);
        }

    }

    /**
     * rsa内容验签
     *
     * @param content   待验内容
     * @param sign      签名
     * @param publicKey 公钥
     * @param charset   编码
     * @return 是否验证成功
     * @throws PlatformException 异常信息
     */
    public static boolean rsaCheckContent(String content, String sign, String publicKey,
                                          String charset) throws PlatformException {
        try {
            PublicKey pubKey = getPublicKeyFromX509("RSA",
                    new ByteArrayInputStream(publicKey.getBytes()));

            java.security.Signature signature = java.security.Signature
                    .getInstance(AlibabaConstnts.SIGN_ALGORITHMS);

            signature.initVerify(pubKey);

            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }

            return signature.verify(Base64.decodeBase64(sign.getBytes()));
        } catch (Exception e) {
            throw new PlatformException(ResponseAPIStatus.CHECK_SIGN_ERROR);
        }
    }


    /**
     * rsa2内容验签
     *
     * @param content   待验内容
     * @param sign      签名
     * @param publicKey 公钥
     * @param charset   编码
     * @return 是否验证成功
     * @throws PlatformException 异常信息
     */
    public static boolean rsa256CheckContent(String content, String sign, String publicKey,
                                             String charset) throws PlatformException {
        try {
            PublicKey pubKey = getPublicKeyFromX509("RSA",
                    new ByteArrayInputStream(publicKey.getBytes()));

            java.security.Signature signature = java.security.Signature
                    .getInstance(AlibabaConstnts.SIGN_SHA256RSA_ALGORITHMS);

            signature.initVerify(pubKey);

            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            } else {
                signature.update(content.getBytes(charset));
            }

            return signature.verify(Base64.decodeBase64(sign.getBytes()));
        } catch (Exception e) {
            throw new PlatformException(ResponseAPIStatus.CHECK_SIGN_ERROR);
        }
    }


    /**
     * 获得待验签的参数
     * @param params 参数列表
     * @return 返回值
     */
    public static String getSignCheckContentV1(Map<String, String> params) {
        if (params == null) {
            return null;
        }

        params.remove("sign");
        params.remove("sign_type");

        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            content.append((i == 0 ? "" : "&") + key + "=" + value);
        }

        return content.toString();
    }


    /**
     * 异步消息校验
     * @param params 参数列表
     * @param publicKey 公钥
     * @param charset 编码
     * @param signType 签名类型
     * @return 返回值
     * @throws PlatformException 异常信息
     */
    public static boolean rsaCheckV1(Map<String, String> params, String publicKey,
                                     String charset, String signType) throws PlatformException {
        String sign = params.get("sign");
        String content = getSignCheckContentV1(params);

        return rsaCheck(content, sign, publicKey, charset, signType);
    }


    public static String getSignCheckContentV2(Map<String, String> params) {
        if (params == null) {
            return null;
        }

        params.remove("sign");

        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            content.append((i == 0 ? "" : "&") + key + "=" + value);
        }

        return content.toString();
    }

    public static boolean rsaCheckV2(Map<String, String> params, String publicKey,
                                     String charset) throws PlatformException {
        String sign = params.get("sign");
        String content = getSignCheckContentV2(params);

        return rsaCheckContent(content, sign, publicKey, charset);
    }

    public static boolean rsaCheckV2(Map<String, String> params, String publicKey,
                                     String charset, String signType) throws PlatformException {
        String sign = params.get("sign");
        String content = getSignCheckContentV2(params);

        return rsaCheck(content, sign, publicKey, charset, signType);
    }


}
