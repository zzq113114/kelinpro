/**
 * 版权信息: © 鑫为科技
 */
package clean.platform.disorder.mapper.extend;

import java.util.List;

import clean.platform.disorder.model.dto.LoginUser;
import org.apache.ibatis.annotations.Param;

import clean.platform.disorder.model.entity.vo.TbUserVo;
import org.springframework.stereotype.Repository;

/*****************************************************************************
 *创建时间 : 2019年03月06日
 *实现功能 : TODO
 *作者 : code
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2019年03月06日 v0.0.1 AUTO CREATE 创建
 *****************************************************************************/
@Repository
public interface TbUserExtendMapper {


	List<TbUserVo> selectByVo(TbUserVo record);


	TbUserVo selectByPK(@Param("id") Long id);

	/**
	 * 用户登录
	 * @param tbUserVo
	 * @return
	 */
	LoginUser selectLoginUser(TbUserVo tbUserVo);

}
