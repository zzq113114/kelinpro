/**
 * 版权信息: © 中金财金服
 */
package clean.platform.disorder.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/*****************************************************************************
 *创建时间 : 2019年04月02日
 *实现功能 : TODO
 *作者 : codeGenerator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2019年04月02日 v0.0.1 codeGenerator 创建
 *****************************************************************************/
public class TbStaffOrder implements Serializable {

	// 员工编号
	@JsonSerialize(using = ToStringSerializer.class)
	private Long staffId;

	// 订单编号
	@JsonSerialize(using = ToStringSerializer.class)
	private Long orderId;

	private static final long serialVersionUID = 1L;

	public Long getStaffId() {
		return this.staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
}
