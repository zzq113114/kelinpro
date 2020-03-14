/**
 * 版权信息: © 鑫为科技
 */
package clean.platform.disorder.mapper.extend;

import java.util.List;

import clean.platform.disorder.model.entity.TbCompany;
import clean.platform.disorder.model.entity.vo.TbCompanyItemVo;
import clean.platform.disorder.model.entity.vo.TbServiceItemConfigVo;
import org.apache.ibatis.annotations.Param;

import clean.platform.disorder.model.entity.vo.TbCompanyVo;
import org.springframework.stereotype.Repository;


@Repository
public interface TbCompanyExtendMapper {


	List<TbCompanyVo> selectByVo(TbCompanyVo record);


	TbCompanyVo selectByPK(@Param("id") Long id);

	/**
	 * 根据名称查询
	 * @return
	 */
	List<TbCompanyVo> selectByName(@Param("companyName")String companyName);

	/**
	 * 查询家政公司服务项目
	 * @param companyId
	 * @return
	 */
	List<TbCompanyItemVo> selectCompanyItem(@Param("companyId")Long companyId);

	/**
	 * 查询家政公司服务项目配置明细
	 * @param companyId
	 * @param itemId
	 * @return
	 */
	TbServiceItemConfigVo selectCompanyItemConfig(@Param("companyId")Long companyId,@Param("itemId")Long itemId);

}
