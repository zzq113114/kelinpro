package clean.platform.disorder.provider;

import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.facade.IStaffFacade;
import clean.platform.disorder.mapper.TbStaffMapper;
import clean.platform.disorder.mapper.extend.TbStaffExtendMapper;
import clean.platform.disorder.model.entity.TbStaff;
import clean.platform.disorder.model.entity.vo.TbStaffVo;
import clean.platform.disorder.model.query.QueryStaff;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工业务功能实现类
 *
 * @author ZX
 * @create 2020-02-02 14:45
 **/

@Service
public class StaffService extends BaseService implements IStaffFacade {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private TbStaffMapper staffMapper;

    @Autowired
    private TbStaffExtendMapper staffExtendMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addStaff(TbStaff tbStaff) throws OperationException {
        try {
            tbStaff.setIsActive((short) 1);
            tbStaff.setIsDelete((short) 0);
            tbStaff.setCreateTime(getNowDate());
            staffMapper.insertSelective(tbStaff);
        } catch (Exception e){
            logger.error("新增员工信息异常:{}",e);
            throw new OperationException("新增员工信息异常",e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStaff(TbStaff tbStaff) throws OperationException {
        try {
            tbStaff.setCreateTime(null);
            staffMapper.updateByPrimaryKeySelective(tbStaff);
        } catch (Exception e){
            logger.error("更新员工信息异常:{}",e);
            throw new OperationException("更新员工信息异常",e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteStaff(Long id) throws OperationException {
        try {
            TbStaff tbStaff = staffMapper.selectByPrimaryKey(id);
            tbStaff.setIsDelete((short) 1);
            staffMapper.updateByPrimaryKey(tbStaff);
        } catch (Exception e){
            logger.error("删除员工信息异常:{}",e);
            throw new OperationException("删除员工信息异常",e);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public TbStaffVo queryStaffInfo(Long id) throws OperationException {
        TbStaffVo tbStaffVo = new TbStaffVo();
        try {
            tbStaffVo = staffExtendMapper.selectByPK(id);
        } catch (Exception e){
            logger.error("查询员工详情异常:{}",e);
            throw new OperationException("查询员工详情异常",e);
        }
        return tbStaffVo;
    }

    @Override
    public PageInfo<TbStaffVo> queryStaffPageList(QueryStaff staffQuery) throws OperationException {
        PageInfo<TbStaffVo> pageInfo = new PageInfo<>();
        TbStaffVo tbStaffVo = new TbStaffVo();
        Page<TbStaffVo> pageStaffs = PageHelper.startPage(staffQuery.getPageNo(), staffQuery.getPageSize())
                .doSelectPage(() -> staffExtendMapper.selectByVo(tbStaffVo));
        pageInfo = new PageInfo<>(pageStaffs);
        return pageInfo;
    }

    @Override
    public List<TbStaffVo> selectStaffList(QueryStaff staffQuery) throws OperationException {
        TbStaffVo tbStaffVo = new TbStaffVo();
        List<TbStaffVo> staffVos = new ArrayList<>();
        try {
            staffVos = staffExtendMapper.selectByVo(tbStaffVo);
        } catch (Exception e){
            logger.error("查询所有员工信息异常:{}",e);
            throw new OperationException("查询所有员工信息异常",e);
        }
        return staffVos;
    }
}
