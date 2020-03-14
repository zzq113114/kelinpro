package clean.platform.disorder.controller;

import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.facade.ICompanyFacade;
import clean.platform.disorder.model.ResponseResult;
import clean.platform.disorder.model.ResultEnum;
import clean.platform.disorder.model.dto.CompanyVo;
import clean.platform.disorder.model.entity.vo.TbCompanyItemVo;
import clean.platform.disorder.model.entity.vo.TbCompanyVo;
import clean.platform.disorder.model.entity.vo.TbServiceItemConfigVo;
import clean.platform.disorder.model.entity.vo.TbServiceItemVo;
import clean.platform.disorder.model.query.QueryCompany;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static clean.platform.disorder.model.ResultEnum.RESULT_QUERY_COMPANY_SERVICE_ITEM_CONFIG_ERROR;
import static clean.platform.disorder.model.ResultEnum.RESULT_SERVICE_ITEM_CONFIG_ERROR;

/**
 * 家政公司信息注册管理控制器
 *
 * @author ZX
 * @create 2019-03-06 22:24
 **/

@RestController
@RequestMapping("/company")
public class CompanyController {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private ICompanyFacade companyFacade;

    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public ResponseResult<?> addCompany(@RequestBody CompanyVo ompanyVo){
        ResponseResult<?> result = new ResponseResult<>();
        try {
            companyFacade.saveCompany(ompanyVo);
            result.setResultEnum(ResultEnum.RESULT_SUCCESS);
        } catch (OperationException e) {
            logger.error("新增家政公司信息异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_COMPANY_ADD_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseResult<?> modifyCompany(@RequestBody CompanyVo ompanyVo) {
        ResponseResult<?> result = new ResponseResult<>();
        try {
            companyFacade.updateCompany(ompanyVo);
            result.setResultEnum(ResultEnum.RESULT_SUCCESS);
        } catch (OperationException e) {
            logger.error("修改家政公司信息异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_COMPANY_UPDATE_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ResponseResult<?> deleteCompany(@PathVariable("id")Long companyId){
        ResponseResult<?> result = new ResponseResult<>();
        try {
            companyFacade.deleteCompany(companyId);
            result.setResultEnum(ResultEnum.RESULT_SUCCESS);
        } catch (OperationException e) {
            logger.error("家政公司信息删除异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_COMPANY_DELETE_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public ResponseResult<PageInfo<TbCompanyVo>> pageCompanys(@RequestBody QueryCompany queryCompany){
        ResponseResult<PageInfo<TbCompanyVo>> result = new ResponseResult<>();
        try {
            PageInfo<TbCompanyVo> pageCompany = companyFacade.queryCompanyPage(queryCompany);
            result.setData(pageCompany);
        } catch (OperationException e) {
            logger.error("分页查询家政公司信息异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_COMPANY_QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public ResponseResult<TbCompanyVo> queryCompanys(@PathVariable("id")Long companyId){
        ResponseResult<TbCompanyVo> result = new ResponseResult<>();
        try {
            TbCompanyVo vo = companyFacade.queryCompany(companyId);
            result.setData(vo);
            result.setResultEnum(ResultEnum.RESULT_SUCCESS);
        } catch (OperationException e) {
            logger.error("修改家政公司信息异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_COMPANY_UPDATE_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/serviceItems" , method = RequestMethod.GET)
    public ResponseResult<List<TbServiceItemVo>> queryAllServiceItems(){
        ResponseResult<List<TbServiceItemVo>>  result = new ResponseResult<>();
        try {
            List<TbServiceItemVo> items = companyFacade.serviceItems();
            result.setData(items);
        } catch (OperationException e) {
            logger.error("查询家政公司服务项目列表异常:{}",e);
            result.setResultEnum(ResultEnum.RESULT_COMPANY_SERVICE_ITEMS_QUERY_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/itemConfig/{companyId}", method = RequestMethod.GET)
    public ResponseResult<List<TbCompanyItemVo>> queryCompanyItemConfigInfo(@PathVariable("companyId")Long companyId){
        ResponseResult<List<TbCompanyItemVo>> result = new ResponseResult<>();
        try {
            List<TbCompanyItemVo> companyItemVoList = companyFacade.selectCompanyConfig(companyId);
            result.setData(companyItemVoList);
        } catch (OperationException e) {
            logger.error("查询家政公司服务配置信息异常:{}",e);
            result.setResultEnum(RESULT_QUERY_COMPANY_SERVICE_ITEM_CONFIG_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "/itemConfig/save", method = RequestMethod.POST)
    public ResponseResult<?> saveCompanyItemConfig(@RequestBody TbServiceItemConfigVo tbServiceItemConfigVo){
        ResponseResult<?> result = new ResponseResult<>();
        try {
            companyFacade.saveItemConfig(tbServiceItemConfigVo);
        } catch (OperationException e) {
            logger.error("配置家政公司服务异常:{}",e);
            result.setResultEnum(RESULT_SERVICE_ITEM_CONFIG_ERROR);
        }
        return result;
    }
}
