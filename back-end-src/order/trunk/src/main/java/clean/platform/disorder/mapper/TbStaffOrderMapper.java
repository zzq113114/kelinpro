/**
 * 版权信息: © 中金财金服
 */
package clean.platform.disorder.mapper;

import java.util.List;

import clean.platform.disorder.model.entity.TbStaffOrder;
import org.springframework.stereotype.Repository;


@Repository
public interface TbStaffOrderMapper {


    int insert(TbStaffOrder record);


    int insertSelective(TbStaffOrder record);


	int insertBatch(List<? extends TbStaffOrder> list);


    int deleteByPrimaryKey(Long id);


    int updateByPrimaryKey(TbStaffOrder record);


    int updateByPrimaryKeySelective(TbStaffOrder record);


    TbStaffOrder selectByPrimaryKey(Long id);
}
