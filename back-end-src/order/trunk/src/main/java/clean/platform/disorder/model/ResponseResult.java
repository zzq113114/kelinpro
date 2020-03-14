package clean.platform.disorder.model;

/**
 * 响应返回类
 *
 * @author ZX
 * @create 2020-03-06 22:53
 **/

public class ResponseResult<T>  {

    private ResultEnum resultEnum;

    private Integer code;

    private String msg;

    private T data;

    public ResponseResult() {
        this.resultEnum = ResultEnum.RESULT_SUCCESS;
        this.code = ResultEnum.RESULT_SUCCESS.getCode();
        this.msg = ResultEnum.RESULT_SUCCESS.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }

    public void setResultEnum(ResultEnum resultEnum) {
        this.resultEnum = resultEnum;
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }
}
