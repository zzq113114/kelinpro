/**
 * 版权信息: © 中金财金服
 */
package clean.platform.disorder.mapper.extend;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import clean.platform.disorder.model.entity.vo.TbStaffOrderVo;

/*****************************************************************************
 *创建时间 : 2020年04月02日
 *实现功能 : TODO
 *作者 : codeGenerator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2020年04月02日 v0.0.1 codeGenerator 创建
 *****************************************************************************/
public interface TbStaffOrderExtendMapper {


	List<TbStaffOrderVo> selectByVo(TbStaffOrderVo record);


	TbStaffOrderVo selectByPK(@Param("id") Long id);

}
