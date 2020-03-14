/**
 * 版权信息: © 可蔺
 */
package clean.platform.disorder.mapper.extend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import clean.platform.disorder.model.entity.vo.TbFunMenuVo;
import org.springframework.stereotype.Repository;

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
@Repository
public interface TbFunMenuExtendMapper {


	List<TbFunMenuVo> selectByVo(TbFunMenuVo record);


	TbFunMenuVo selectByPK(@Param("id") Long id);

}
