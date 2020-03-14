package clean.platform.disorder.facade;

import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.model.entity.vo.TbOriginVo;

import java.util.List;

/**
 * 配置维护信息接口
 */
public interface IMaintenancFacade {

    List<TbOriginVo> allOriginVos() throws OperationException;
}
