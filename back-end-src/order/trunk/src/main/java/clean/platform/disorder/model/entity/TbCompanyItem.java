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
public class TbCompanyItem implements Serializable {

	// 公司编号
	@JsonSerialize(using = ToStringSerializer.class)
	private Long companyId;

	// 服务项目编号
	@JsonSerialize(using = ToStringSerializer.class)
	private Long itemId;

	// 服务配置项编码
	private Integer configId;

	private static final long serialVersionUID = 1L;

	public Long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getItemId() {
		return this.itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}
}
