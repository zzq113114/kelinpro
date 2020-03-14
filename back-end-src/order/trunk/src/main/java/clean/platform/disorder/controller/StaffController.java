
package clean.platform.disorder.controller;

import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.facade.IStaffFacade;
import clean.platform.disorder.model.ResponseResult;
import clean.platform.disorder.model.ResultEnum;
import clean.platform.disorder.model.entity.vo.TbStaffVo;
import clean.platform.disorder.model.query.QueryStaff;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工操作控制器
 *
 * @author ZX
 * @create 2019-04-02 14:50
 **/


@RestController
@RequestMapping("/staff")
public class StaffController {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private IStaffFacade staffService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<?> addStaff(@RequestBody TbStaffVo staffVo){
        ResponseResult<?> result = new ResponseResult<>();
        try {
            staffService.addStaff(staffVo);
            result.setResultEnum(ResultEnum.RESULT_SUCCESS);
        } catch (OperationException e) {
            logger.error("创建员工异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_STAFF_ADD_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseResult<?> updateStaff(@RequestBody TbStaffVo staffVo){
        ResponseResult<?> result = new ResponseResult<>();
        try{
            staffService.updateStaff(staffVo);
            result.setResultEnum(ResultEnum.RESULT_SUCCESS);
        } catch(OperationException e){
            logger.error("员工更新操作异常", e);
            result.setResultEnum(ResultEnum.RESULT_STAFF_UPDATE_ERROR);
        }
        return result;
    }

    @RequestMapping("/delete/{id}")
    public ResponseResult<?> deleteStaff(@PathVariable("id")Long id){
        ResponseResult<?> result = new ResponseResult<>();
        try {
            staffService.deleteStaff(id);
            result.setResultEnum(ResultEnum.RESULT_SUCCESS);
        } catch (OperationException e) {
            logger.error("员工删除操作异常", e);
            result.setResultEnum(ResultEnum.RESULT_STAFF_DELETE_ERROR);
        }
        return result;
    }

    @RequestMapping("/info/{id}")
    public ResponseResult<TbStaffVo> queryStaffInfo(@PathVariable("id")Long id){
        ResponseResult<TbStaffVo> result = new ResponseResult<>();
        try {
            TbStaffVo staff =  staffService.queryStaffInfo(id);
            result.setData(staff);
        } catch (OperationException e) {
            logger.error("员工详情查询异常", e);
            result.setResultEnum(ResultEnum.RESULT_STAFF_QUERY_ERROR);;
        }
        return result;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public ResponseResult<PageInfo<TbStaffVo>> queryOrderPageList(@RequestBody QueryStaff staffQuery){
        ResponseResult<PageInfo<TbStaffVo>> result = new ResponseResult<>();
        try{
            PageInfo<TbStaffVo> pageStaff = staffService.queryStaffPageList(staffQuery);
            result.setData(pageStaff);
        } catch(OperationException e){
            logger.error("分页查询员工信息失败", e);
            result.setResultEnum(ResultEnum.RESULT_STAFF_QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseResult<List<TbStaffVo>> selectAllStaffList(@RequestBody QueryStaff staffQuery) {
        ResponseResult<List<TbStaffVo>> result = new ResponseResult<>();
        try {
            List<TbStaffVo> staffVos = staffService.selectStaffList(staffQuery);
            result.setData(staffVos);
        } catch (OperationException e) {
            logger.error("查询所有员工信息异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_STAFF_QUERY_ERROR);
        }
        return result;
    }
}
