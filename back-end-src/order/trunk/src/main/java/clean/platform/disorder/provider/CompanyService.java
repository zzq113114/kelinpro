package clean.platform.disorder.provider;

import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.facade.ICompanyFacade;
import clean.platform.disorder.mapper.TbCompanyItemMapper;
import clean.platform.disorder.mapper.TbCompanyMapper;
import clean.platform.disorder.mapper.TbServiceItemConfigMapper;
import clean.platform.disorder.mapper.extend.TbCompanyExtendMapper;
import clean.platform.disorder.mapper.extend.TbCompanyItemExtendMapper;
import clean.platform.disorder.mapper.extend.TbServiceItemExtendMapper;
import clean.platform.disorder.model.ConstDefiner;
import clean.platform.disorder.model.dto.CompanyVo;
import clean.platform.disorder.model.entity.TbCompany;
import clean.platform.disorder.model.entity.vo.TbCompanyItemVo;
import clean.platform.disorder.model.entity.vo.TbCompanyVo;
import clean.platform.disorder.model.entity.vo.TbServiceItemConfigVo;
import clean.platform.disorder.model.entity.vo.TbServiceItemVo;
import clean.platform.disorder.model.query.QueryCompany;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static clean.platform.disorder.model.ResultEnum.*;

/**
 * 家政公司服务功能实现类
 *
 * @author ZX
 * @create 2020-03-06 23:21
 **/

@Service
public class CompanyService extends BaseService implements ICompanyFacade {

    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private TbCompanyMapper companyMapper;

    @Autowired
    private TbCompanyExtendMapper companyExtendMapper;

    @Autowired
    private TbServiceItemExtendMapper serviceItemExtendMapper;

    @Autowired
    private TbCompanyItemExtendMapper companyItemExtendMapper;

    @Autowired
    private TbCompanyItemMapper companyItemMapper;

    @Autowired
    private TbServiceItemConfigMapper serviceItemConfigMapper;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCompany(CompanyVo companyVo) throws OperationException {
        List<TbCompanyVo> companyVos = companyExtendMapper.selectByName(companyVo.getCompanyName());
        if (companyVos.size() > 0) {
            throw new OperationException(RESULT_COMPANY_DUPLICATE_NAME);
        }
        try {
            long id = getId();
            companyVo.setId(id);
            companyVo.setCreateTime(getNowDate());
            companyVo.setUpdateTime(getNowDate());
            companyVo.setIsDelete(ConstDefiner.NOT_DELETE);
            companyMapper.insertSelective(companyVo);
            List<TbCompanyItemVo> itemVoList = getTbCompanyItemVos(companyVo);
            companyItemExtendMapper.insertCompanyItemsBatch(itemVoList);
        } catch (Exception e) {
            logger.error("新增家政公司异常:{}", e);
            throw new OperationException(RESULT_COMPANY_ADD_ERROR, e);
        }
    }

    @Override
    public List<TbCompanyVo> queryCompanyByName(String companyName) throws OperationException {
        try {
            return companyExtendMapper.selectByName(companyName);
        } catch (Exception e) {
            logger.error("根据家政公司名称查询公司信息异常:{}", e);
            throw new OperationException(RESULT_COMPANY_QUERY_ERROR, e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCompany(CompanyVo companyVo) throws OperationException {
        List<TbCompanyVo> companyVos = companyExtendMapper.selectByName(companyVo.getCompanyName());
        if (companyVos.size() > 0) {
            TbCompanyVo dbCompany = companyVos.get(0);
            if (dbCompany.getId().longValue() != companyVo.getId().longValue()) {
                throw new OperationException(RESULT_COMPANY_DUPLICATE_NAME);
            }
        }
        try {
            companyMapper.updateByPrimaryKeySelective(companyVo);
            //对于和家政公司关联的服务项信息采用先删除后插入的方式进行修改
            companyItemExtendMapper.deleteCompanyItem(companyVo.getId());
            List<TbCompanyItemVo> itemVoList = getTbCompanyItemVos(companyVo);
            companyItemExtendMapper.insertCompanyItemsBatch(itemVoList);
        } catch (Exception e) {
            logger.error("修改家政信息异常:{}", e);
            throw new OperationException(RESULT_COMPANY_UPDATE_ERROR, e);
        }

    }

    private List<TbCompanyItemVo> getTbCompanyItemVos(CompanyVo companyVo) {
        List<Integer> itemIds = companyVo.getItems();
        return itemIds.stream().map(itemId-> {
                    TbCompanyItemVo companyItemVo = new TbCompanyItemVo();
                    companyItemVo.setItemId(Long.valueOf(itemId));
                    companyItemVo.setCompanyId(companyVo.getId());
                    return companyItemVo;
                }).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCompany(Long compangId) throws OperationException {
        TbCompany company = companyExtendMapper.selectByPK(compangId);
        company.setIsDelete(ConstDefiner.IS_DELETE);
        try {
            companyMapper.updateByPrimaryKeySelective(company);
        } catch (Exception e) {
            logger.error("删除家政信息异常:{}", e);
            throw new OperationException(RESULT_COMPANY_DELETE_ERROR, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PageInfo<TbCompanyVo> queryCompanyPage(QueryCompany queryCompany) throws OperationException {
        PageInfo<TbCompanyVo> pageInfo = new PageInfo<>();
        try {
            TbCompanyVo vo = new TbCompanyVo();
            if (!StringUtils.isEmpty(queryCompany.getCompanyName())){
                vo.setCompanyName(queryCompany.getCompanyName());
            }
            if (!StringUtils.isEmpty(queryCompany.getContact())){
                vo.setContact(queryCompany.getContact());
            }
            if (!StringUtils.isEmpty(queryCompany.getStartCreateTime())){
                vo.setStartCreateTime(queryCompany.getStartCreateTime());
            }
            if (!StringUtils.isEmpty(queryCompany.getEndCreateTime())){
                vo.setEndCreateTime(queryCompany.getEndCreateTime());
            }
            //todo根据条件设置vo的值
            Page<TbCompanyVo> pageCompanys = PageHelper.startPage(queryCompany.getPageNo(), queryCompany.getPageSize())
                    .doSelectPage(() -> companyExtendMapper.selectByVo(vo));
            pageInfo = new PageInfo<>(pageCompanys);
            return pageInfo;
        } catch (Exception e) {
            logger.error("分页查询家装公司信息异常:{}",e);
            throw new OperationException(RESULT_COMPANY_QUERY_ERROR,e);
        }
    }

    @Override
    public TbCompanyVo queryCompany(Long companyId) throws OperationException {
        try {
            TbCompanyVo companyVo = companyExtendMapper.selectByPK(companyId);
            List<TbServiceItemVo> itemVos = companyItemExtendMapper.selectItemsByCompanyId(companyId);
            List<Integer> itemIds = itemVos.stream().map(item-> item.getId()).collect(Collectors.toList());
            companyVo.setItems(itemIds);
            return companyVo;
        } catch (Exception e){
            logger.error("查询家政公司详情异常:{}",e);
            throw new OperationException("查询家政公司详情异常",e);
        }

    }

    @Override
    public List<TbServiceItemVo> serviceItems() throws OperationException {
        try {
            return serviceItemExtendMapper.selectAllServiceItems();
        } catch(Exception e) {
            logger.error("查询所有服务项目列表异常:{}",e);
            throw new OperationException("查询所有服务项目列表异常",e);
        }
    }

    @Override
    public List<TbCompanyItemVo> selectCompanyConfig(Long companyId) throws OperationException {
        try {
            List<TbCompanyItemVo> companyItemVoList = companyExtendMapper.selectCompanyItem(companyId);
            companyItemVoList.stream().forEach(item->{
                item.setCompanyId(companyId);
                Long itemId = item.getItemId();
                TbServiceItemConfigVo itemConfigVo = companyExtendMapper.selectCompanyItemConfig(companyId, itemId);
                if(Objects.isNull(itemConfigVo)){
                    itemConfigVo = new TbServiceItemConfigVo();
                }
                item.setServiceItemConfigVo(itemConfigVo);
            });
            return companyItemVoList;
        } catch (Exception e){
            logger.error("查询家政公司服务配置信息异常:{}",e);
            throw new OperationException("查询家政公司服务配置信息异常",e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveItemConfig(TbServiceItemConfigVo tbServiceItemConfigVo) throws OperationException {
        if (tbServiceItemConfigVo.getId()==null){ //新增
            try {
                serviceItemConfigMapper.insertSelective(tbServiceItemConfigVo);
                int configId = tbServiceItemConfigVo.getId();
                TbCompanyItemVo companyItemVo = companyItemExtendMapper.selectByCompanyIdAndItemId(tbServiceItemConfigVo.getCompanyId(), tbServiceItemConfigVo.getItemId());
                companyItemVo.setConfigId(configId);
                companyItemMapper.updateByPrimaryKeySelective(companyItemVo);
            } catch (Exception e){
                logger.error("家政公司配置信息新增失败",e);
                throw new OperationException("家政公司配置信息新增失败",e);
            }
        } else { //修改
            try {
                serviceItemConfigMapper.updateByPrimaryKeySelective(tbServiceItemConfigVo);
            } catch (Exception e){
                logger.error("家政公司配置信息修改失败",e);
                throw new OperationException("家政公司配置信息修改失败",e);
            }
        }
    }
}
