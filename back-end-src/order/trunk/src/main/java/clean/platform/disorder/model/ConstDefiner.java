package clean.platform.disorder.model;

/**
 * 常量类
 *
 * @author ZX
 * @create 2020-03-10 1:10
 **/

public interface ConstDefiner {

    /**
     * 删除常量
     */
    final Short IS_DELETE = 1;

    final Short NOT_DELETE = 0;

    final Short STATUS_NEW =  ORDER_STATUS.IS_NEW.getStatus();

    final Short STATUS_DISPATCH = ORDER_STATUS.IS_DISPATCH.getStatus();

    final Short STATUS_DISPATCH_SUCCESS = ORDER_STATUS.IS_DISPATCH_SUCCESS.getStatus();

    final Short STATUS_DISPATCH_FAILURE = ORDER_STATUS.IS_DISPATCH_FAILURE.getStatus();

    final Short STATUS_FINISHED = ORDER_STATUS.IS_FINISHED.getStatus();

    final Short STATUS_CANCEL = ORDER_STATUS.IS_CANCEL.getStatus();

    final Short STATUS_AGAIN = ORDER_STATUS.IS_AGAIN.getStatus();

//    final Short STATUS_OVER = ORDER_STATUS.IS_OVER.getStatus();

    enum ORDER_STATUS {

        IS_NEW(1,"新建"),
        IS_DISPATCH(2,"已分派"),
        IS_DISPATCH_SUCCESS(3,"分派成功"),
        IS_DISPATCH_FAILURE(4,"分派失败"),
        IS_CANCEL(5,"订单作废"),
        IS_AGAIN(6,"重新派单"),
        IS_FINISHED(7,"订单成功"),
        IS_NULL(10,""),
        IS_OVER(8,"待审核");
        Short status;

        String name;

        ORDER_STATUS(Integer status, String name) {
            this.status = Short.valueOf(status.toString());
            this.name = name;
        }

        public Short getStatus() {
            return status;
        }

        public String getName() {
            return name;
        }
    }

    public static ORDER_STATUS matchOpCode(Short code) {
        for (ORDER_STATUS _STATUS : ORDER_STATUS.values()) {
            if (_STATUS.getStatus() == code) {
                return _STATUS;
            }
        }
        return ORDER_STATUS.IS_NULL;
    }

}
