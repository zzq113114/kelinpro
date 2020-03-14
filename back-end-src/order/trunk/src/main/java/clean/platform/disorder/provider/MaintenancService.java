package clean.platform.disorder.provider;

import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.facade.IMaintenancFacade;
import clean.platform.disorder.mapper.extend.TbOriginExtendMapper;
import clean.platform.disorder.model.entity.vo.TbOriginVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MaintenancService implements IMaintenancFacade {

    private Logger logger = LogManager.getLogger(MaintenancService.class);

    @Autowired
    private TbOriginExtendMapper originExtendMapper;


    @Override
    public List<TbOriginVo> allOriginVos() throws OperationException {
        try {
            List<TbOriginVo> originVos = originExtendMapper.selectOriginVoList();
            return originVos;
        } catch (Exception e){
            logger.error("查询信息来源网站异常:{}",e);
            throw new OperationException("查询信息来源网站异常",e);
        }

    }
}
