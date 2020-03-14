/**
 * 版权信息: © 易往信息技术
 */
package clean.platform.disorder.datasource;

/*****************************************************************************
 *创建时间 : 2019年2月29日
 *实现功能 : TODO
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2019年2月29日 v0.0.1 创建
 *****************************************************************************/
public enum DBNameEnum {
    /*管理员*/
	master("master");

	private String name;

	private DBNameEnum(String name) {
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

}
