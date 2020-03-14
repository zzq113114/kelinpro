/**
 * 版权信息: © 可蔺
 */
package clean.platform.disorder.mapper.extend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import clean.platform.disorder.model.entity.vo.TbRoleMenuVo;

/*****************************************************************************
 *创建时间 : 2020年03月05日
 *实现功能 : TODO
 *作者 : codeGenerator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2020年03月05日 v0.0.1 codeGenerator 创建
 *****************************************************************************/
public interface TbRoleMenuExtendMapper {


	List<TbRoleMenuVo> selectByVo(TbRoleMenuVo record);


	TbRoleMenuVo selectByPK(@Param("id") Long id);

}
