/**
 * 版权信息: © 鑫为科技
 */
package clean.platform.disorder.mapper;

import clean.platform.disorder.model.entity.TbCompanyItem;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface 	TbCompanyItemMapper {



    int insert(TbCompanyItem record);



    int insertSelective(TbCompanyItem record);


	int insertBatch(List<? extends TbCompanyItem> list);



    int deleteByPrimaryKey(Long id);


    int updateByPrimaryKey(TbCompanyItem record);

    int updateByPrimaryKeySelective(TbCompanyItem record);


    TbCompanyItem selectByPrimaryKey(Long id);
}
