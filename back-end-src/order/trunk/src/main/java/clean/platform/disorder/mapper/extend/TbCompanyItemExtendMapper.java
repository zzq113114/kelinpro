/**
 * 版权信息: © 鑫为科技
 */
package clean.platform.disorder.mapper.extend;

import java.util.List;

import clean.platform.disorder.model.entity.vo.TbServiceItemVo;
import org.apache.ibatis.annotations.Param;

import clean.platform.disorder.model.entity.vo.TbCompanyItemVo;
import org.springframework.stereotype.Repository;


@Repository
public interface TbCompanyItemExtendMapper {


	List<TbCompanyItemVo> selectByVo(TbCompanyItemVo record);


	TbCompanyItemVo selectByCompanyIdAndItemId(@Param("companyId") Long companyId, @Param("itemId") Long itemId);

	int insertCompanyItemsBatch(List<TbCompanyItemVo> companyItemVos);

	List<TbServiceItemVo> selectItemsByCompanyId(@Param("companyId") Long companyId);

	void deleteCompanyItem(@Param("companyId") Long companyId);

}
