/**
 * 版权信息: © 中金财金服
 */
package clean.platform.disorder.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/*****************************************************************************
 *创建时间 : 2019年03月27日
 *实现功能 : TODO
 *作者 : codeGenerator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2019年03月27日 v0.0.1 codeGenerator 创建
 *****************************************************************************/
public class TbOrder implements Serializable {

	// 主键
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	// 订单流水号
	private String orderNo;

	// 源派单编号
	private Long originOrderId;

	// 订单状态:1:新建，待下发；2：已下发；3：下发成功；4：下发失败
	private Short status;

	// 订单详情，在网上获得的订单信息通过该字段说明详细信息
	private String detailInfo;

	// 订单来源:1：移动端；2：web端
	private Integer origin;

	// 来源网站
	private String fromWeb;

	// 客户名称
	private String customerName;

	// 工作人数
	private Integer staffCount;

	// 工作时长
	private Integer workHours;

	// 客户地址
	private String customerAddress;

	// 金额
	private BigDecimal amount;

	//预约电话
	private String mobileNo;

	//回款状态
	private Integer payment;

	// 预定服务开始日期
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date bookStartDate;

	// 预定服务结束日期
	private Date bookEndDate;

	// 创建日期
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createDate;

	// 创建者
	private String createUser;

	// 备注：订单的备注信息，比如，下派失败时注明失败原因
	private String remark;

	private static final long serialVersionUID = 1L;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getDetailInfo() {
		return this.detailInfo;
	}

	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
	}

	public Integer getOrigin() {
		return this.origin;
	}

	public void setOrigin(Integer origin) {
		this.origin = origin;
	}

	public String getFromWeb() {
		return this.fromWeb;
	}

	public void setFromWeb(String fromWeb) {
		this.fromWeb = fromWeb;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return this.customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getBookStartDate() {
		return this.bookStartDate;
	}

	public void setBookStartDate(Date bookStartDate) {
		this.bookStartDate = bookStartDate;
	}

	public Date getBookEndDate() {
		return this.bookEndDate;
	}

	public void setBookEndDate(Date bookEndDate) {
		this.bookEndDate = bookEndDate;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public Integer getStaffCount() {
		return staffCount;
	}

	public void setStaffCount(Integer staffCount) {
		this.staffCount = staffCount;
	}

	public Integer getWorkHours() {
		return workHours;
	}

	public void setWorkHours(Integer workHours) {
		this.workHours = workHours;
	}

	public Long getOriginOrderId() {
		return originOrderId;
	}

	public void setOriginOrderId(Long originOrderId) {
		this.originOrderId = originOrderId;
	}
}
