package clean.platform.disorder.facade;

import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.model.dto.MenuActionInfo;
import clean.platform.disorder.model.entity.TbRole;
import clean.platform.disorder.model.entity.vo.TbFunMenuVo;
import clean.platform.disorder.model.entity.vo.TbRoleVo;
import clean.platform.disorder.model.query.QueryRole;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 角色管理定义接口
 */
public interface IRoleFacade {

    List<TbRoleVo> queryRoles() throws OperationException;

    void addRole(TbRoleVo tbRoleVo) throws OperationException;

    void deleteRole(Long roleId) throws OperationException;

    PageInfo<TbRoleVo> queryRolePageList(QueryRole queryRole) throws OperationException;

    List<TbFunMenuVo> queryAllFunMenus() throws OperationException;

    TbRoleVo queryRoleById(long roleId) throws OperationException;

    List<TbRoleVo> queryRoleByRoleName(String roleName) throws OperationException;

    void updateRoleInfo(TbRoleVo roleVo) throws OperationException;

    List<TbFunMenuVo> queryFunMenuList(Long roleId) throws OperationException;

    List<MenuActionInfo> initMenuAction(Long roleId) throws OperationException;

}
