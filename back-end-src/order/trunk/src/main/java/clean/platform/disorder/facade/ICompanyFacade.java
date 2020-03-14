package clean.platform.disorder.facade;

import clean.platform.disorder.exception.OperationException;
import clean.platform.disorder.model.dto.CompanyVo;
import clean.platform.disorder.model.entity.TbCompanyItem;
import clean.platform.disorder.model.entity.TbServiceItem;
import clean.platform.disorder.model.entity.vo.TbCompanyItemVo;
import clean.platform.disorder.model.entity.vo.TbCompanyVo;
import clean.platform.disorder.model.entity.vo.TbServiceItemConfigVo;
import clean.platform.disorder.model.entity.vo.TbServiceItemVo;
import clean.platform.disorder.model.query.QueryCompany;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICompanyFacade {

    /**
     * 家政公司注册
     */
    public void saveCompany(CompanyVo ompanyVo) throws OperationException;

    /**
     * 根据家政公司名称查询家政公司
     * @param companyName
     * @return
     * @throws OperationException
     */
    public List<TbCompanyVo> queryCompanyByName(String companyName) throws OperationException;

    /**
     * 修改家政公司信息
     * @param ompanyVo
     * @throws OperationException
     */
    public void updateCompany(CompanyVo ompanyVo) throws OperationException;

    /**
     * 删除家政公司
     * @param compangId
     * @throws OperationException
     */
    public void deleteCompany(Long compangId) throws OperationException;

    /**
     * 根据条件分页查询家政公司信息
     */
    public PageInfo<TbCompanyVo> queryCompanyPage(QueryCompany queryCompany) throws OperationException;

    /**
     * 根据id查询家政公司详情
     * @param companyId
     * @return
     * @throws OperationException
     */
    public TbCompanyVo queryCompany(Long companyId) throws OperationException;


    /**
     * 查询家政公司服务范围选项
     * @return
     * @throws OperationException
     */
    public List<TbServiceItemVo> serviceItems() throws OperationException;

    /**
     * 查询家政公司服务项目配置信息
     * @param companyId
     * @return
     * @throws OperationException
     */
    public List<TbCompanyItemVo> selectCompanyConfig(Long companyId) throws OperationException;

    /**
     * 保存家政服务配置信息
     * @param tbServiceItemConfigVo
     * @throws OperationException
     */
    public void saveItemConfig(TbServiceItemConfigVo tbServiceItemConfigVo) throws OperationException;
}
