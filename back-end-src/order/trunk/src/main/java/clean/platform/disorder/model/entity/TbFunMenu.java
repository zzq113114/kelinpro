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
public class TbFunMenu implements Serializable {

	// 
	private Integer id;

	// 
	private String menuCode;

	// 
	private String menuName;

	private String path;

	private Integer parentId;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
