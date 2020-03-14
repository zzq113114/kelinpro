/**
 * 版权信息: © 鑫为科技
 */
package clean.platform.disorder.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

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
public class TbRole implements Serializable {

	// 主键
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	// 角色名称
	private String roleName;

	// 角色描述
	private String roleDesc;

	// 是否有效0:无效；1：有效
	private Short isActive;

	// 是否删除:0:未删除；1：已删除
	private Short isDelete;

	private static final long serialVersionUID = 1L;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Short getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Short isActive) {
		this.isActive = isActive;
	}

	public Short getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}
}
