package clean.platform.disorder.controller;

import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.facade.IRoleFacade;
import clean.platform.disorder.model.ResponseResult;
import clean.platform.disorder.model.ResultEnum;
import clean.platform.disorder.model.dto.MenuActionInfo;
import clean.platform.disorder.model.entity.vo.TbFunMenuVo;
import clean.platform.disorder.model.entity.vo.TbRoleVo;
import clean.platform.disorder.model.query.QueryRole;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 角色管理控制器
 *
 * @author ZZ
 * @create 2020-01-04 14:48
 **/

@RestController
@RequestMapping("/role")
public class RoleController {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private IRoleFacade roleService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseResult<List<TbRoleVo>>  queryRoleList(){
        ResponseResult<List<TbRoleVo>> result = new ResponseResult<>();
        try {
            List<TbRoleVo> roleVoList = roleService.queryRoles();
            result.setData(roleVoList);
        } catch (OperationException e) {
            logger.error("查询所有角色异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_ROLE_QUERY_LIST_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/rolePage", method = RequestMethod.POST)
    public ResponseResult<PageInfo<TbRoleVo>> queryRolePage(@RequestBody QueryRole queryRole){
        ResponseResult<PageInfo<TbRoleVo>> result = new ResponseResult<>();
        try {
            PageInfo<TbRoleVo> pageInfo = roleService.queryRolePageList(queryRole);
            result.setData(pageInfo);
        } catch (OperationException e) {
            logger.error("分页查询角色异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_ROLE_QUERY_LIST_ERROR);
        }
        return result;
    }

    @RequestMapping(value="/funMenuList", method = RequestMethod.POST)
    public ResponseResult<List<TbFunMenuVo>> queryAllFunMenu(){
        ResponseResult<List<TbFunMenuVo>> result = new ResponseResult<>();
        try {
            List<TbFunMenuVo> funMenuVoList = roleService.queryAllFunMenus();
            funMenuVoList = funMenuVoList.stream().filter(menuVo->menuVo.getParentId() == null).collect(Collectors.toList());
            result.setData(funMenuVoList);
        } catch (OperationException e) {
            logger.error("查询所有功能异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_FUN_MENU_QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseResult<?> adRole(@RequestBody TbRoleVo roleVo){
        ResponseResult<?> result = new ResponseResult<>();
        try {
            List<TbRoleVo> hasRoles = roleService.queryRoleByRoleName(roleVo.getRoleName());
            if (Objects.nonNull(hasRoles) && hasRoles.size()>0){
                logger.info("角色名称重复");
                result.setResultEnum(ResultEnum.RESULT_ROLE_DUPLICATE_ERROR);
                return result;
            }
            roleService.addRole(roleVo);
        } catch (OperationException e) {
            logger.error("增加角色异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_ROLE_ADD_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/info/{roleId}", method = RequestMethod.GET)
    public ResponseResult<TbRoleVo> queryRoleInfo(@PathVariable("roleId")Long roleId){
        ResponseResult<TbRoleVo> result = new ResponseResult<>();
        try {
            TbRoleVo roleVo = roleService.queryRoleById(roleId);
            result.setData(roleVo);
        } catch (OperationException e) {
            logger.error("查询角色详情异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_ROLE_INFO_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/delete/{roleId}", method = RequestMethod.GET)
    public ResponseResult<?> deletRole(@PathVariable("roleId")Long roleId){
        ResponseResult<?> result = new ResponseResult<>();
        try {
            roleService.deleteRole(roleId);
        } catch (OperationException e) {
            logger.error("角色删除异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_ROLE_DELETE_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseResult<?> updateRole(@RequestBody TbRoleVo roleVo){
        ResponseResult<?> result = new ResponseResult<>();
        try {
            List<TbRoleVo> hasRoles = roleService.queryRoleByRoleName(roleVo.getRoleName());
            if (Objects.nonNull(hasRoles) && hasRoles.size()>0){
                Optional<TbRoleVo> optionalRoleVo = hasRoles.stream().filter(role->role.getId().longValue()!=roleVo.getId().longValue()).findAny();
                if (optionalRoleVo.isPresent()){
                    logger.info("角色名称重复");
                    result.setResultEnum(ResultEnum.RESULT_ROLE_DUPLICATE_ERROR);
                    return result;
                }
            }
            roleService.updateRoleInfo(roleVo);
        } catch (OperationException e) {
            logger.error("角色修改异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_ROLE_UPDATE_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/funMenu/{roleId}", method = RequestMethod.GET)
    public ResponseResult<List<TbFunMenuVo>> queryRoleFunMenuList(@PathVariable("roleId")Long roleId){
        ResponseResult<List<TbFunMenuVo>> result = new ResponseResult<>();
        try {
            List<TbFunMenuVo> funMenuVoList = roleService.queryFunMenuList(roleId);
            result.setData(funMenuVoList);
        } catch (OperationException e) {
            logger.error("查询角色功能权限异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_ROLE_FUNMENU_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/menuAction/{roleId}", method = RequestMethod.GET)
    public ResponseResult<List<MenuActionInfo>> initMenuList(@PathVariable("roleId")Long roleId){
        ResponseResult<List<MenuActionInfo>> result = new ResponseResult<>();
        try {
            List<MenuActionInfo> menuActionInfos = roleService.initMenuAction(roleId);
            result.setData(menuActionInfos);
        } catch (OperationException e) {
            logger.error("查询角色功能权限异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_ROLE_FUNMENU_ERROR);
        }
        return result;
    }
}
