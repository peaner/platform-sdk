package peaner.platform.sdk.api.alipay.pay;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description: 手机网站支付响应参数
 * @Date: Created in 18-7-3 下午4:53
 **/
@Setter
@Getter
public class MobileTradePayRespBean {

    @SerializedName("alipay_trade_wap_pay_response")
    private MobileTradePayBean alipayTradeWapPayResponse;

    /**
     * 签名
     */
    private String sign;

}
