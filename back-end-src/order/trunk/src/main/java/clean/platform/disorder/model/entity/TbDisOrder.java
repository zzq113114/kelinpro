/**
 * 版权信息: © 鑫为科技
 */
package clean.platform.disorder.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;

/*****************************************************************************
 *创建时间 : 2019年03月06日
 *实现功能 : TODO
 *作者 : code
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2019年03月06日 v0.0.1 AUTO CREATE 创建
 *****************************************************************************/
public class TbDisOrder implements Serializable {

	// 主键
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	// 订单编号
	private Long orderId;

	// 公司编号
	private Long companyId;

	// 是否接收，0：不接受；1：接收
	private Short isAccept;

	// 备注
	private String remark;

	// 创建日期
	private Date createDate;

	// 创建者
	private byte[] createUser;

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

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Short getIsAccept() {
		return this.isAccept;
	}

	public void setIsAccept(Short isAccept) {
		this.isAccept = isAccept;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public byte[] getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(byte[] createUser) {
		this.createUser = createUser;
	}
}
