package peaner.platform.sdk.exception;

import lombok.Getter;
import lombok.Setter;
import peaner.platform.sdk.common.ResponseAPIStatus;

/**
 * @Author: lilongzhou
 * @Description:
 * @Date: Created in 18-7-2 下午3:36
 **/
@Setter
@Getter
public class PlatformException extends Exception {

    protected ResponseAPIStatus responseAPIStatus;

    public PlatformException() {
        super();
    }

    public PlatformException(ResponseAPIStatus responseAPIStatus) {
        super(responseAPIStatus.toString());
        this.responseAPIStatus = responseAPIStatus;
    }

    public PlatformException(String message) {
        super(message);
        this.responseAPIStatus = ResponseAPIStatus.SYSTEM_INTERNAL_ERROR;
    }

    public PlatformException(ResponseAPIStatus responseAPIStatus, String message) {
        super(message);
        this.responseAPIStatus = responseAPIStatus;
    }

}
