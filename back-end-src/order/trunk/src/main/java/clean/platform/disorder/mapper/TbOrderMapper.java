/**
 * 版权信息: © 中金财金服
 */
package clean.platform.disorder.mapper;

import java.util.List;

import clean.platform.disorder.model.entity.TbOrder;
import org.springframework.stereotype.Repository;

/*****************************************************************************
 *创建时间 : 2020年03月27日
 *实现功能 : TODO
 *作者 : codeGenerator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2020年03月27日 v0.0.1 codeGenerator 创建
 *****************************************************************************/
@Repository
public interface TbOrderMapper {


    int insert(TbOrder record);


    int insertSelective(TbOrder record);


	int insertBatch(List<? extends TbOrder> list);


    int deleteByPrimaryKey(Long id);


    int updateByPrimaryKey(TbOrder record);


    int updateByPrimaryKeySelective(TbOrder record);


    TbOrder selectByPrimaryKey(Long id);
}
