/**
 * 版权信息: © 鑫为科技
 */
package clean.platform.disorder.model.entity;

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
public class TbServiceItem implements Serializable {

	// 主键
	private Integer id;

	// 项目编码
	private String itemCode;

	// 项目名称
	private String itemName;

	// 状态位，0:false无效;1:true有效
	private Short status;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
}
