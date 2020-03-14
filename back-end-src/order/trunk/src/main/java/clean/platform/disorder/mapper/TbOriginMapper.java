/**
 * 版权信息: © 中金财金服
 */
package clean.platform.disorder.mapper;

import clean.platform.disorder.model.entity.TbOrigin;

import java.util.List;



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
public interface TbOriginMapper {


    int insert(TbOrigin record);


    int insertSelective(TbOrigin record);


	int insertBatch(List<? extends TbOrigin> list);


    int deleteByPrimaryKey(Long id);


    int updateByPrimaryKey(TbOrigin record);


    int updateByPrimaryKeySelective(TbOrigin record);

    TbOrigin selectByPrimaryKey(Long id);
}
