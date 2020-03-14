/**
 * 版权信息: © 中金财金服
 */
package clean.platform.disorder.mapper;

import java.util.List;

import clean.platform.disorder.model.entity.TbOrderOrigin;

/*****************************************************************************
 *创建时间 : 2020年03月30日
 *实现功能 : TODO
 *作者 : codeGenerator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2020年03月30日 v0.0.1 codeGenerator 创建
 *****************************************************************************/
public interface TbOrderOriginMapper {


    int insert(TbOrderOrigin record);


    int insertSelective(TbOrderOrigin record);


	int insertBatch(List<? extends TbOrderOrigin> list);


    int deleteByPrimaryKey(Long id);


    int updateByPrimaryKey(TbOrderOrigin record);


    int updateByPrimaryKeySelective(TbOrderOrigin record);


    TbOrderOrigin selectByPrimaryKey(Long id);
}
