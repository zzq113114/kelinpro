/**
 * 版权信息: © 中金财金服
 */
package clean.platform.disorder.mapper;

import java.util.List;

import clean.platform.disorder.model.entity.TbServiceItemConfig;
import org.springframework.stereotype.Repository;

/*****************************************************************************
 *创建时间 : 2020年01月08日
 *实现功能 : TODO
 *作者 : codeGenerator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2020年01月08日 v0.0.1 codeGenerator 创建
 *****************************************************************************/
@Repository
public interface TbServiceItemConfigMapper {


    int insert(TbServiceItemConfig record);


    int insertSelective(TbServiceItemConfig record);


	int insertBatch(List<? extends TbServiceItemConfig> list);



    int deleteByPrimaryKey(Long id);


    int updateByPrimaryKey(TbServiceItemConfig record);


    int updateByPrimaryKeySelective(TbServiceItemConfig record);


    TbServiceItemConfig selectByPrimaryKey(Long id);
}
