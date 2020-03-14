/**
 * 版权信息: © 鑫为科技
 */
package clean.platform.disorder.mapper;

import java.util.List;

import clean.platform.disorder.model.entity.TbStaff;
import org.springframework.stereotype.Repository;


@Repository
public interface TbStaffMapper {



    int insert(TbStaff record);



    int insertSelective(TbStaff record);


	int insertBatch(List<? extends TbStaff> list);


    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKey(TbStaff record);


    int updateByPrimaryKeySelective(TbStaff record);


    TbStaff selectByPrimaryKey(Long id);
}
