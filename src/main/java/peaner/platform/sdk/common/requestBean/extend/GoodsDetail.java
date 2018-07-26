package peaner.platform.sdk.common.requestBean.extend;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: lilongzhou
 * @Description:
 * @Date: Created in 18-7-3 下午5:19
 **/
@Setter
@Getter
public class GoodsDetail {

    /**
     * 商品的编号
     */
    @SerializedName("goods_id")
    private String goodsId;

    /**
     * 商品名称
     */
    @SerializedName("goods_name")
    private String goodsName;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 商品单价，单位为元
     */
    private Double price;

    /**
     * 商品类目
     */
    @SerializedName("goods_category")
    private String goodsCategory;

    /**
     * 商品描述信息
     */
    private String body;

    /**
     * 商品的展示地址
     */
    @SerializedName("show_url")
    private String showUrl;

}
