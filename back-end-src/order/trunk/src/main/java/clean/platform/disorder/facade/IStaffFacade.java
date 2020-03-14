package clean.platform.disorder.facade;

import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.model.entity.TbStaff;
import clean.platform.disorder.model.entity.vo.TbStaffVo;
import clean.platform.disorder.model.query.QueryStaff;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 员工管理类业务接口
 */
public interface IStaffFacade {

    void addStaff(TbStaff tbStaff) throws OperationException;

    void updateStaff(TbStaff tbStaff) throws OperationException;

    void deleteStaff(Long id) throws OperationException;

    TbStaffVo queryStaffInfo(Long id) throws OperationException;

    PageInfo<TbStaffVo> queryStaffPageList(QueryStaff staffQuery) throws OperationException;

    List<TbStaffVo> selectStaffList(QueryStaff staffQuery) throws OperationException;
}
