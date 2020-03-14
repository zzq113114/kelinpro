/**
 * 版权信息: © 鑫为科技
 */
package clean.platform.disorder.mapper;

import java.util.List;

import clean.platform.disorder.model.entity.TbDisOrder;

/*****************************************************************************
 *创建时间 : 2020年02月06日
 *实现功能 : TODO
 *作者 : code
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2020年02月06日 v0.0.1 AUTO CREATE 创建
 *****************************************************************************/
public interface TbDisOrderMapper {

    int insert(TbDisOrder record);


    int insertSelective(TbDisOrder record);


	int insertBatch(List<? extends TbDisOrder> list);


    int deleteByPrimaryKey(Long id);


    int updateByPrimaryKey(TbDisOrder record);


    int updateByPrimaryKeySelective(TbDisOrder record);

    TbDisOrder selectByPrimaryKey(Long id);
}
