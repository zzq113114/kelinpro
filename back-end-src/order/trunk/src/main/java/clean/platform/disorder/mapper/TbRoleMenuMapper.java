/**
 * 版权信息: © 可蔺
 */
package clean.platform.disorder.mapper;

import java.util.List;

import clean.platform.disorder.model.entity.TbRoleMenu;
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
public interface TbRoleMenuMapper {


    int insert(TbRoleMenu record);


    int insertSelective(TbRoleMenu record);

	int insertBatch(List<? extends TbRoleMenu> list);


    int deleteByPrimaryKey(Long id);


    int updateByPrimaryKey(TbRoleMenu record);


    int updateByPrimaryKeySelective(TbRoleMenu record);


    TbRoleMenu selectByPrimaryKey(Long id);
}
