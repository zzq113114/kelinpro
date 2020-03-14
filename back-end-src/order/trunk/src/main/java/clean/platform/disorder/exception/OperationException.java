package clean.platform.disorder.exception;

import clean.platform.disorder.model.ResultEnum;

import java.io.IOException;

/**
 * 家政公司信息维护异常
 *
 * @author ZX
 * @create 2020-03-09 21:43
 **/

public class OperationException extends IOException {

    private ResultEnum resultEnum;

    public OperationException() {
        super();
    }

    public OperationException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.resultEnum = resultEnum;
    }

    public OperationException(String message) {
        super(message);
    }

    public OperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationException(ResultEnum resultEnum, Throwable cause) {
        super(cause);
        this.resultEnum = resultEnum;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }
}
