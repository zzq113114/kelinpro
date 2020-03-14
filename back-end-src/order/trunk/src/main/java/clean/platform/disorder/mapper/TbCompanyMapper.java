/**
 * 版权信息: © 鑫为科技
 */
package clean.platform.disorder.mapper;

import clean.platform.disorder.model.entity.TbCompany;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface TbCompanyMapper {



    int insert(TbCompany record);



    int insertSelective(TbCompany record);



	int insertBatch(List<? extends TbCompany> list);


    int deleteByPrimaryKey(Long id);


    int updateByPrimaryKey(TbCompany record);


    int updateByPrimaryKeySelective(TbCompany record);

    TbCompany selectByPrimaryKey(Long id);
}
