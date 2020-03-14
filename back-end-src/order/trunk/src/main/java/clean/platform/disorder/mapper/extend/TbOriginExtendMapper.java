/**
 * 版权信息: © 中金财金服
 */
package clean.platform.disorder.mapper.extend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import clean.platform.disorder.model.entity.vo.TbOriginVo;
import org.springframework.stereotype.Repository;

/*****************************************************************************
 *创建时间 : 2019年03月30日
 *实现功能 : TODO
 *作者 : codeGenerator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2019年03月30日 v0.0.1 codeGenerator 创建
 *****************************************************************************/
@Repository
public interface TbOriginExtendMapper {

	/**
	 * ====================================================================
	 *功 能： 根据Vo查询
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2019年03月30日 v0.0.1 codeGenerator 创建
	====================================================================
	 */
	List<TbOriginVo> selectByVo(TbOriginVo record);

	/**
	 * ====================================================================
	 *功 能： 根据Pk查询
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2019年03月30日 v0.0.1 codeGenerator 创建
	====================================================================
	 */
	TbOriginVo selectByPK(@Param("id") Long id);

	/**
	 *
	 * @return
	 */
	List<TbOriginVo> selectOriginVoList();

}
