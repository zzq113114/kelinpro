/**
 * 版权信息: © 鑫为科技
 */
package clean.platform.disorder.mapper.extend;

import java.util.List;

import clean.platform.disorder.model.entity.TbFinishedOrder;
import org.apache.ibatis.annotations.Param;

import clean.platform.disorder.model.entity.vo.TbFinishedOrderVo;

/*****************************************************************************
 *创建时间 : 2020年03月06日
 *实现功能 : TODO
 *作者 : code
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2020年03月06日 v0.0.1 AUTO CREATE 创建
 *****************************************************************************/
public interface TbFinishedOrderExtendMapper {


	List<TbFinishedOrderVo> selectByVo(TbFinishedOrderVo record);


	TbFinishedOrderVo selectByPK(@Param("id") Long id);



}
