package peaner.platform.sdk.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: lilongzhou
 * @Description: 返回码
 * @Date: Created in 18-7-2 下午3:34
 **/
@Getter
@AllArgsConstructor
public enum ResponseAPIStatus {

    // 生成签名错误
    GENERATE_SIGN_ERROR(1000),

    // 检查签名有误
    CHECK_SIGN_ERROR(1001),

    // 系统错误
    SYSTEM_INTERNAL_ERROR(500);

    private int code;

    public boolean isSuccess() {
        return this.code == 0;
    }


}
