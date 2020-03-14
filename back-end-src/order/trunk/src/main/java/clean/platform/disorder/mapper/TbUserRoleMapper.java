/**
 * 版权信息: © 鑫为科技
 */
package clean.platform.disorder.mapper;

import java.util.List;

import clean.platform.disorder.model.entity.TbUserRole;
import org.springframework.stereotype.Repository;

/*****************************************************************************
 *创建时间 : 2020年02月03日
 *实现功能 : TODO
 *作者 : code
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2020年02月03日 v0.0.1 AUTO CREATE 创建
 *****************************************************************************/
@Repository
public interface TbUserRoleMapper {


    int insert(TbUserRole record);


    int insertSelective(TbUserRole record);


	int insertBatch(List<? extends TbUserRole> list);


    int deleteByPrimaryKey(Long id);


    int updateByPrimaryKey(TbUserRole record);


    int updateByPrimaryKeySelective(TbUserRole record);


    TbUserRole selectByPrimaryKey(Long id);
}
