package clean.platform.disorder.model.query;

import java.util.Date;

/**
 * 订单查询类
 */
public class QueryOrder extends BaseQuery {

    private String customerName;

    private Date[] startDate;

    private Short orderStatus;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date[] getStartDate() {
        return startDate;
    }

    public void setStartDate(Date[] startDate) {
        this.startDate = startDate;
    }

    public Short getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Short orderStatus) {
        this.orderStatus = orderStatus;
    }
}
