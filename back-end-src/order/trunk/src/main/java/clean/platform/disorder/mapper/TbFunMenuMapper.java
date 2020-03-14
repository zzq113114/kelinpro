/**
 * 版权信息: © 可蔺
 */
package clean.platform.disorder.mapper;

import java.util.List;

import clean.platform.disorder.model.entity.TbFunMenu;

/*****************************************************************************
 *创建时间 : 2020年02月05日
 *实现功能 : TODO
 *作者 : codeGenerator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2020年02月05日 v0.0.1 codeGenerator 创建
 *****************************************************************************/
public interface TbFunMenuMapper {


    int insert(TbFunMenu record);


    int insertSelective(TbFunMenu record);


	int insertBatch(List<? extends TbFunMenu> list);


    int deleteByPrimaryKey(Long id);


    int updateByPrimaryKey(TbFunMenu record);


    int updateByPrimaryKeySelective(TbFunMenu record);


    TbFunMenu selectByPrimaryKey(Long id);
}
