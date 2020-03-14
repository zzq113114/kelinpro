package clean.platform.disorder.provider;

import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.facade.IRoleFacade;
import clean.platform.disorder.mapper.TbRoleMapper;
import clean.platform.disorder.mapper.TbRoleMenuMapper;
import clean.platform.disorder.mapper.extend.TbFunMenuExtendMapper;
import clean.platform.disorder.mapper.extend.TbRoleExtendMapper;
import clean.platform.disorder.model.dto.MenuActionInfo;
import clean.platform.disorder.model.entity.TbRoleMenu;
import clean.platform.disorder.model.entity.vo.TbFunMenuVo;
import clean.platform.disorder.model.entity.vo.TbRoleVo;
import clean.platform.disorder.model.query.QueryRole;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色定义功能实现
 *
 * @author ZX
 * @create 2020-02-04 14:50
 **/

@Service
public class RoleService extends BaseService implements IRoleFacade {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private TbRoleExtendMapper roleExtendMapper;

    @Autowired
    private TbFunMenuExtendMapper funMenuExtendMapper;

    @Autowired
    private TbRoleMapper roleMapper;

    @Autowired
    private TbRoleMenuMapper roleMenuMapper;

    @Override
    public List<TbRoleVo> queryRoles() throws OperationException {
        List<TbRoleVo> roleVoList = new ArrayList<>();
        TbRoleVo roleVo = new TbRoleVo();
        try {
            roleVoList = roleExtendMapper.selectByVo(roleVo);
        } catch (Exception e){
            logger.error("查询所有角色异常:{}",e);
            throw new OperationException("查询所有角色异常:{}",e);
        }
        return roleVoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRole(TbRoleVo tbRoleVo) throws OperationException {
        try {
            roleMapper.insertSelective(tbRoleVo);
            Long roleId = tbRoleVo.getId();
            List<Integer> funMenuIds = tbRoleVo.getFunMenuIds();
            List<TbRoleMenu> roleMenus = new ArrayList<>();
            addRoleMenu(roleId, funMenuIds);
//            roleMenuMapper.insertBatch(roleMenus);
        } catch (Exception e){
            logger.error("新增角色异常:{}",e);
            throw new OperationException("新增角色异常",e);
        }
    }

    private void addRoleMenu(Long roleId, List<Integer> funMenuIds) {
        funMenuIds.forEach(menuId -> {
            TbRoleMenu roleMenu = new TbRoleMenu();
            roleMenu.setRoleId(roleId.intValue());
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insertSelective(roleMenu);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Long roleId) throws OperationException {
        try {
            roleExtendMapper.deleteRole(roleId);
        } catch (Exception e){
            logger.error("删除角色异常:{}",e);
            throw new OperationException("删除角色异常",e);
        }
    }

    @Override
    public PageInfo<TbRoleVo> queryRolePageList(QueryRole queryRole) throws OperationException {
        PageInfo<TbRoleVo> pageInfo = new PageInfo<>();
        TbRoleVo tbRoleVo = new TbRoleVo();
        if (StringUtils.isNotEmpty(queryRole.getRoleName())){
            tbRoleVo.setRoleName(queryRole.getRoleName());
        }
        Page<TbRoleVo> pageRoles = PageHelper.startPage(queryRole.getPageNo(), queryRole.getPageSize())
                .doSelectPage(() -> roleExtendMapper.selectByVo(tbRoleVo));
        pageRoles.getResult().stream().forEach(roleVo->{
            List<TbFunMenuVo> menuVos = roleExtendMapper.selectFunMenuListByRole(roleVo.getId());
            roleVo.setFunMenus(menuVos);
            String funMenus = String.join(",",menuVos.stream().map(menu->menu.getMenuName()).collect(Collectors.toList()));
            roleVo.setFunMenuStr(funMenus);
        });
        pageInfo = new PageInfo<>(pageRoles);
        return pageInfo;
    }

    @Override
    public List<TbFunMenuVo> queryAllFunMenus() throws OperationException {
        TbFunMenuVo funMenuVo = new TbFunMenuVo();
        try {
            List<TbFunMenuVo> funMenuVoList = funMenuExtendMapper.selectByVo(funMenuVo);
            return funMenuVoList;
        } catch (Exception e){
            logger.error("查询所有授权功能异常:{}",e);
            throw  new OperationException("查询所有授权功能异常",e);
        }
    }

    @Override
    public TbRoleVo queryRoleById(long roleId) throws OperationException {
        TbRoleVo roleVo = new TbRoleVo();
        try {
            roleVo = roleExtendMapper.selectByPK(roleId);
            List<TbFunMenuVo> menuVos = roleExtendMapper.selectFunMenuListByRole(roleVo.getId());
            roleVo.setFunMenus(menuVos);
            List<Integer> menuIds = menuVos.stream().map(menu->menu.getId()).collect(Collectors.toList());
            roleVo.setFunMenuIds(menuIds);
            return roleVo;
        } catch (Exception e){
            logger.error("查询角色详情异常:{}",e);
            throw new OperationException("查询角色详情异常",e);
        }
    }

    @Override
    public List<TbRoleVo> queryRoleByRoleName(String roleName) throws OperationException {
        List<TbRoleVo> roleVos = new ArrayList<>();
        try {
            TbRoleVo tbRoleVo = new TbRoleVo();
            tbRoleVo.setRoleName(roleName);
            roleVos = roleExtendMapper.queryRoleInfoByName(tbRoleVo);
        } catch (Exception e){
            logger.error("根据角色名称查询角色信息异常:{}",e);
            throw new OperationException("根据角色名称查询角色信息异常",e);
        }
        return roleVos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRoleInfo(TbRoleVo roleVo) throws OperationException {
        try {
            Long roleId = roleVo.getId();
            roleExtendMapper.deleteRoleMenusByRoleId(roleId); //删除所有该角色相关的授权功能
            roleMapper.updateByPrimaryKeySelective(roleVo); //修改角色主信息
            List<Integer> funMenuIds = roleVo.getFunMenuIds();
            List<TbRoleMenu> roleMenus = new ArrayList<>();
            addRoleMenu(roleId, funMenuIds);
//            roleMenuMapper.insertBatch(roleMenus); //重新增加角色相关的授权功能
        } catch (Exception  e){
            logger.error("修改角色信息异常:{}",e);
            throw new OperationException("修改角色信息异常",e);
        }
    }

    @Override
    public List<TbFunMenuVo> queryFunMenuList(Long roleId) throws OperationException {
        List<TbFunMenuVo> menuVos = new ArrayList<>();
        try {
            menuVos = roleExtendMapper.selectFunMenuListByRole(roleId);
            return menuVos;
        } catch (Exception e){
            logger.error("查询角色相关功能权限异常:{}",e);
            throw new OperationException("查询角色相关功能权限异常",e);
        }
    }

    @Override
    public List<MenuActionInfo> initMenuAction(Long roleId) throws OperationException {
        List<MenuActionInfo> menuActionInfos = new ArrayList<>();
        try {
            MenuActionInfo menuActionInfo = new MenuActionInfo();
            menuActionInfo.setName("首页");
            menuActionInfo.setPath("/home");
            menuActionInfos.add(menuActionInfo);
            List<TbFunMenuVo> menuVos = roleExtendMapper.selectFunMenuListByRole(roleId);
            menuActionInfos.addAll(menuVos.stream().filter(menu -> menu.getParentId() == null).map(menu -> {
                MenuActionInfo menuAction = new MenuActionInfo();
                //获得父目录
                menuAction.setName(menu.getMenuName());
                menuAction.setPath(menu.getPath());
                if(StringUtils.isNotEmpty(menu.getPath()))
                    menuAction.setPath(menu.getPath());
                //查询子目录
                TbFunMenuVo vo = new TbFunMenuVo();
                vo.setParentId(menu.getId());
                List<TbFunMenuVo> funMenuVos = funMenuExtendMapper.selectByVo(vo);
                List<MenuActionInfo> childMenuActions = funMenuVos.stream().map(child->{
                    MenuActionInfo childAction = new MenuActionInfo();
                    //获得父目录
                    childAction.setName(child.getMenuName());
                    childAction.setPath(child.getPath());
                    return childAction;
                }).collect(Collectors.toList());
                menuAction.setChild(childMenuActions);
                return menuAction;
            }).collect(Collectors.toList()));
            return menuActionInfos;
        } catch (Exception e){
            logger.error("查询角色相关功能权限列表异常:{}",e);
            throw new OperationException("查询角色相关功能权限列表异常",e);
        }
    }
}
