/**
 * 版权信息: © 可蔺
 */
package clean.platform.disorder.model.entity;

import java.io.Serializable;

/*****************************************************************************
 *创建时间 : 2019年05月05日
 *实现功能 : TODO
 *作者 : codeGenerator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2019年05月05日 v0.0.1 codeGenerator 创建
 *****************************************************************************/
public class TbRoleMenu implements Serializable {

	// 
	private Integer roleId;

	// 
	private Integer menuId;

	private static final long serialVersionUID = 1L;

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
}
