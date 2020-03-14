package clean.platform.disorder.controller;

import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.facade.IUserFacade;
import clean.platform.disorder.model.ResponseResult;
import clean.platform.disorder.model.ResultEnum;
import clean.platform.disorder.model.entity.vo.TbUserVo;
import clean.platform.disorder.model.query.QueryUser;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理控制器
 *
 * @author ZX
 * @create 2020-02-03 19:16
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private IUserFacade userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<?> addUser(@RequestBody TbUserVo userVo){
        ResponseResult<?> result = new ResponseResult<>();
        try {
            userService.addUser(userVo);
        } catch (OperationException e) {
            logger.error("新增用户异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_USER_ADD_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseResult<?> updateUser(@RequestBody TbUserVo userVo){
        ResponseResult<?> result = new ResponseResult<>();
        try {
            userService.updateUser(userVo);
        } catch (OperationException e) {
            logger.error("用户修改异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_USER_MODIFY_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseResult<?> deleteUser(@PathVariable("id")Long id){
        ResponseResult<?> result = new ResponseResult<>();
        try {
            userService.deleteUser(id);
        } catch (OperationException e) {
            logger.error("删除用户异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_USER_DELETE_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public ResponseResult<TbUserVo> queryUserInfo(@PathVariable("id")Long id){
        ResponseResult<TbUserVo> result = new ResponseResult<>();
        try {
            TbUserVo userVo = userService.userInfo(id);
            result.setData(userVo);
        } catch (OperationException e) {
            logger.error("查询用户详情异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_USER_QUERY_INFO_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public ResponseResult<PageInfo<TbUserVo>> queryUserPage(@RequestBody QueryUser queryUser){
        ResponseResult<PageInfo<TbUserVo>> result = new ResponseResult<>();
        try {
            PageInfo<TbUserVo> userVoPage = userService.queryUserPage(queryUser);
            result.setData(userVoPage);
        } catch (OperationException e) {
            logger.error("查询用户分页信息异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_USER_QUERY_PAGE_ERROR);
        }
        return result;
    }
}
