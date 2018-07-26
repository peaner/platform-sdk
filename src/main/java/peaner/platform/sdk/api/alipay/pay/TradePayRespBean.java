package peaner.platform.sdk.api.alipay.pay;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description: 当面付响应参数
 * @Date: Created in 18-7-3 下午4:53
 **/
@Setter
@Getter
public class TradePayRespBean {

    @SerializedName("alipay_trade_precreate_response")
    private TradePayBean alipayTradePrecreateResponse;

    /**
     * 签名
     */
    private String sign;

}
