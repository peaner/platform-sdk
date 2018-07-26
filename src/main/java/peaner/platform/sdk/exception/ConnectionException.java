package peaner.platform.sdk.exception;

import peaner.platform.sdk.common.ResponseAPIStatus;

/**
 * @Author: lilongzhou
 * @Description: 连接异常
 * @Date: Created in 18-7-2 下午3:33
 **/
public class ConnectionException extends PlatformException {

    private static String conMessage = "连接失败:";

    public ConnectionException() {
        super();
    }

    public ConnectionException(ResponseAPIStatus responseAPIStatus) {
        super(responseAPIStatus);
    }

    public ConnectionException(String message) {
        super(conMessage + message);
    }

    public ConnectionException(ResponseAPIStatus responseAPIStatus, String message) {
        super(responseAPIStatus, conMessage + message);
    }

}
