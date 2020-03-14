package clean.platform.disorder.model.entity.vo;

import clean.platform.disorder.model.ConstDefiner;
import clean.platform.disorder.model.entity.TbFinishedOrder;
import clean.platform.disorder.model.ConstDefiner.ORDER_STATUS;

import java.util.Date;


public class JudgeFinishedOrderVo extends TbFinishedOrder {

    private String customerAddress;

    private String statusName;

    private String originFrom;

    private Date queryStartDate;

    private Date queryEndDate;

    private Long[] staffIds;//分派订单时指定的员工编号

    private String staffsName; //如果是已经分派的订单，则显示所有接收该订单的员工名字

    private String orderNo;

    private  String detailInfo;

    private  String customerName;

    private  String  mobileNo;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDetailInfo() {
        return detailInfo;
    }

    public void setDetailInfo(String detailInfo) {
        this.detailInfo = detailInfo;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatusName() {
        Short st = getStatus();
        if (st != null && st != 0) {
            switch (ConstDefiner.matchOpCode(st)) {
                case IS_NEW:
                    this.statusName = ORDER_STATUS.IS_NEW.getName();
                    break;
                case IS_DISPATCH:
                    this.statusName = ORDER_STATUS.IS_DISPATCH.getName();
                    break;
                case IS_DISPATCH_SUCCESS:
                    this.statusName = ORDER_STATUS.IS_DISPATCH_SUCCESS.getName();
                    break;
                case IS_DISPATCH_FAILURE:
                    this.statusName = ORDER_STATUS.IS_DISPATCH_FAILURE.getName();
                    break;
                case IS_FINISHED:
                    this.statusName = ORDER_STATUS.IS_FINISHED.getName();
                    break;
                case IS_CANCEL:
                    this.statusName = ORDER_STATUS.IS_CANCEL.getName();
                    break;
                case IS_AGAIN:
                    this.statusName = ORDER_STATUS.IS_AGAIN.getName();
                    break;
                default:
                    this.statusName = ORDER_STATUS.IS_NULL.getName();
                    break;
            }
        }
        return statusName;
    }


    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getOriginFrom() {
        return originFrom;
    }

    public void setOriginFrom(String originFrom) {
        this.originFrom = originFrom;
    }

    public Date getQueryStartDate() {
        return queryStartDate;
    }

    public void setQueryStartDate(Date queryStartDate) {
        this.queryStartDate = queryStartDate;
    }

    public Date getQueryEndDate() {
        return queryEndDate;
    }

    public void setQueryEndDate(Date queryEndDate) {
        this.queryEndDate = queryEndDate;
    }

    public Long[] getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(Long[] staffIds) {
        this.staffIds = staffIds;
    }

    public String getStaffsName() {
        return staffsName;
    }

    public void setStaffsName(String staffsName) {
        this.staffsName = staffsName;
    }
}
