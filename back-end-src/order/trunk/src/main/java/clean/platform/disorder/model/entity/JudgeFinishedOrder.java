package clean.platform.disorder.model.entity;

import clean.platform.disorder.model.query.BaseQuery;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class JudgeFinishedOrder extends BaseQuery implements Serializable {
    // 主键
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    // 订单编号
    private Long orderId;

    // 0:失败；1：成功
    private Short status;

    // 订单成功后。实际的收入金额
    private BigDecimal incomeAmount;

    // 原因描述
    private String reason;

    // 用户评价
    private String evaluations;

    // 用户评级级别
    private Short evalLevel;

    // 创建日期
    private Date createDate;

    // 创建者
    private String createUser;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Short getStatus() {
        return this.status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getEvaluations() {
        return this.evaluations;
    }

    public void setEvaluations(String evaluations) {
        this.evaluations = evaluations;
    }

    public Short getEvalLevel() {
        return this.evalLevel;
    }

    public void setEvalLevel(Short evalLevel) {
        this.evalLevel = evalLevel;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public BigDecimal getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
    }
}
