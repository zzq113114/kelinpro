package clean.platform.disorder.provider;

import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.facade.IUserFacade;
import clean.platform.disorder.mapper.TbUserMapper;
import clean.platform.disorder.mapper.TbUserRoleMapper;
import clean.platform.disorder.mapper.extend.TbUserExtendMapper;
import clean.platform.disorder.model.dto.LoginUser;
import clean.platform.disorder.model.entity.TbUser;
import clean.platform.disorder.model.entity.TbUserRole;
import clean.platform.disorder.model.entity.vo.TbUserVo;
import clean.platform.disorder.model.query.QueryUser;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 用户管理功能实现类
 *
 * @author ZX
 * @create 2020-02-03 19:21
 **/

@Service
public class UserService extends BaseService implements IUserFacade {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private TbUserExtendMapper userExtendMapper;

    @Autowired
    private TbUserRoleMapper userRoleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(TbUserVo tbUser) throws OperationException {
        try {
            Long roleId = tbUser.getRoleId();
            Long userId = getId();
            TbUserRole userRole = new TbUserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            userRoleMapper.insertSelective(userRole);
            tbUser.setId(userId);
            tbUser.setIsActive((short)1);
            userMapper.insertSelective(tbUser);
        } catch (Exception e){
            logger.error("用户增加异常:{}",e);
            throw new OperationException("用户增加异常",e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(TbUserVo tbUser) throws OperationException {
        try {
            Long userId = tbUser.getId();
            userRoleMapper.deleteByPrimaryKey(userId);
            TbUserRole userRole = new TbUserRole();
            userRole.setRoleId(tbUser.getRoleId());
            userRole.setUserId(userId);
            userRoleMapper.insertSelective(userRole);
            userMapper.updateByPrimaryKeySelective(tbUser);
        } catch (Exception e){
            logger.error("修改用户异常:{}",e);
            throw new OperationException("修改用户异常",e);
        }
    }

    @Override
    public void deleteUser(Long id) throws OperationException {
        try {
            TbUser tbUser = userMapper.selectByPrimaryKey(id);
            tbUser.setIsActive((short) 0);
            userMapper.updateByPrimaryKeySelective(tbUser);
        } catch (Exception e){
            logger.error("删除用户异常:{}",e);
            throw new OperationException("删除用户异常",e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public TbUserVo userInfo(Long id) throws OperationException {
        TbUserVo tbUserVo = new TbUserVo();
        try {
            tbUserVo = userExtendMapper.selectByPK(id);
        } catch (Exception e){
            logger.error("查询用户详情异常:{}",e);
            throw new OperationException("查询用户详情异常",e);
        }
        return tbUserVo;
    }

    @Override
    @Transactional(readOnly = true)
    public PageInfo<TbUserVo> queryUserPage(QueryUser queryUser) throws OperationException {
        PageInfo<TbUserVo> pageInfo = new PageInfo<>();
        TbUserVo tbUserVo = new TbUserVo();
        if (!StringUtils.isEmpty(queryUser.getUserAccount())){
            tbUserVo.setUserAccount(queryUser.getUserAccount());
        }
        if (!StringUtils.isEmpty(queryUser.getUserName())){
            tbUserVo.setUserName(queryUser.getUserName());
        }
        Page<TbUserVo> pageUsers = PageHelper.startPage(queryUser.getPageNo(), queryUser.getPageSize())
                .doSelectPage(() -> userExtendMapper.selectByVo(tbUserVo));
        pageInfo = new PageInfo<>(pageUsers);
        return pageInfo;
    }

    @Override
    public LoginUser userLogin(TbUserVo tbUserVo) throws OperationException {
        LoginUser loginUser = new LoginUser();
        try {
            loginUser = userExtendMapper.selectLoginUser(tbUserVo);
        } catch (Exception e){
            logger.error("用户登录异常:{}",e);
            throw new OperationException("用户登录异常",e);
        }
        return loginUser;
    }
}
