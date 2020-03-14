/**
 * 版权信息: © 鑫为科技
 */
package clean.platform.disorder.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/*****************************************************************************
 *创建时间 : 2019年04月03日
 *实现功能 : TODO
 *作者 : code
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2019年04月03日 v0.0.1 AUTO CREATE 创建
 *****************************************************************************/
public class TbUserRole implements Serializable {

	//
	@JsonSerialize(using = ToStringSerializer.class)
	private Long userId;

	//
	@JsonSerialize(using = ToStringSerializer.class)
	private Long roleId;

	private static final long serialVersionUID = 1L;

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
