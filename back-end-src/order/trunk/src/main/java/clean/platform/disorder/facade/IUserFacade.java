package clean.platform.disorder.facade;

import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.model.dto.LoginUser;
import clean.platform.disorder.model.entity.TbUser;
import clean.platform.disorder.model.entity.vo.TbUserVo;
import clean.platform.disorder.model.query.QueryUser;
import com.github.pagehelper.PageInfo;

/**
 * 用户管理功能接口
 */
public interface IUserFacade {

    void addUser(TbUserVo tbUser) throws OperationException;

    void updateUser(TbUserVo tbUser) throws OperationException;

    void deleteUser(Long id) throws OperationException;

    TbUserVo userInfo(Long id) throws OperationException;

    PageInfo<TbUserVo> queryUserPage(QueryUser queryUser) throws OperationException;

    LoginUser userLogin(TbUserVo tbUserVo) throws OperationException;
}
