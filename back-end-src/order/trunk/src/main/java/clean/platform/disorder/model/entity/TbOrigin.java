/**
 * 版权信息: © 中金财金服
 */
package clean.platform.disorder.model.entity;

import java.io.Serializable;

/*****************************************************************************
 *创建时间 : 2019年03月30日
 *实现功能 : TODO
 *作者 : codeGenerator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2019年03月30日 v0.0.1 codeGenerator 创建
 *****************************************************************************/
public class TbOrigin implements Serializable {

	// 
	private Integer id;

	// 订单信息来源网站
	private String originFrom;

	// 是否伤处
	private Short isDelete;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOriginFrom() {
		return this.originFrom;
	}

	public void setOriginFrom(String originFrom) {
		this.originFrom = originFrom;
	}

	public Short getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}
}
