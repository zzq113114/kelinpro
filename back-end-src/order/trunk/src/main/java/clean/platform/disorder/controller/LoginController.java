package clean.platform.disorder.controller;

import clean.platform.disorder.configuer.TokenGenerator;
import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.facade.IUserFacade;
import clean.platform.disorder.model.ResponseResult;
import clean.platform.disorder.model.dto.LoginUser;
import clean.platform.disorder.model.entity.vo.TbUserVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static clean.platform.disorder.model.ResultEnum.RESULT_USER_LOGIN_ERROR;
import static clean.platform.disorder.model.ResultEnum.RESULT_USER_LOGIN_USER_OR_PASSWORD_ERROR;

/**
 * 登录授权控制器
 *
 * @author ZX

 **/

@RestController
@RequestMapping("/login")
public class LoginController {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private IUserFacade userService;

    @RequestMapping(value = "/byAccount", method = RequestMethod.POST)
    public ResponseResult<LoginUser> userLogin(@RequestBody TbUserVo tbUserVo) {
        ResponseResult<LoginUser> result = new ResponseResult<>();
        try {
            LoginUser loginUser = userService.userLogin(tbUserVo);
            if (Objects.nonNull(loginUser)) //登录用户成功，生成Token
                loginUser.setToken(TokenGenerator.generateToken(loginUser.getUserAccount()));
            if (Objects.isNull(loginUser)) {
                result.setResultEnum(RESULT_USER_LOGIN_USER_OR_PASSWORD_ERROR);
                return result;
            }
            result.setData(loginUser);
        } catch (OperationException e) {
            logger.error("用户登录异常:{}", e);
            result.setResultEnum(RESULT_USER_LOGIN_ERROR);
        }
        return result;
    }
}
