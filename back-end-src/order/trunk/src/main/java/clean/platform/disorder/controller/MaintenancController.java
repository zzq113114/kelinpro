package clean.platform.disorder.controller;

import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.facade.IMaintenancFacade;
import clean.platform.disorder.model.ResponseResult;
import clean.platform.disorder.model.ResultEnum;
import clean.platform.disorder.model.entity.vo.TbOriginVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 配置维护信息控制器
 */
@RestController
@RequestMapping("/maintenanc")
public class MaintenancController {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private IMaintenancFacade maintenancService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseResult<List<TbOriginVo>> queryAllOriginList(){
        ResponseResult<List<TbOriginVo>> result = new ResponseResult<>();
        try {
            List<TbOriginVo> originVos = maintenancService.allOriginVos();
            result.setData(originVos);
        } catch (OperationException e) {
            logger.error("查询所有信息来源网站异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_QUERY_ORIGINS_ERROR);
        }
        return result;
    }


}
