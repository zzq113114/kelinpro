/**
 * 版权信息: © 鑫为科技
 */
package clean.platform.disorder.mapper;

import java.util.List;

import clean.platform.disorder.model.entity.TbServiceItem;

/*****************************************************************************
 *创建时间 : 2020年03月01日
 *实现功能 : TODO
 *作者 : code
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2010年03月01日 v0.0.1 AUTO CREATE 创建
 *****************************************************************************/
public interface TbServiceItemMapper {


    int insert(TbServiceItem record);

    int insertSelective(TbServiceItem record);


	int insertBatch(List<? extends TbServiceItem> list);


    int deleteByPrimaryKey(Long id);


    int updateByPrimaryKey(TbServiceItem record);


    int updateByPrimaryKeySelective(TbServiceItem record);


    TbServiceItem selectByPrimaryKey(Long id);
}
