/**
 * 版权信息: © 中金财金服
 */
package clean.platform.disorder.model.entity.vo;

import clean.platform.disorder.model.entity.TbServiceItemConfig;

import java.math.BigDecimal;

/*****************************************************************************
 *创建时间 : 2019年04月08日
 *实现功能 : TODO
 *作者 : codeGenerator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2019年04月08日 v0.0.1 codeGenerator 创建
 *****************************************************************************/
public class TbServiceItemConfigVo extends TbServiceItemConfig {

    private Long companyId; //公司编号

    private Long itemId; //服务项目编号

    public TbServiceItemConfigVo(){
        setBookCount((short) 0);
        setUnitPrice(BigDecimal.valueOf(0,3));
        setUnit("");
        setSalePrice(BigDecimal.valueOf(0,3));
        setTotalTime(0.0f);
        setpCount((short)0);
        setTimeRangeStart((short)0);
        setTimeRangeEnd((short)0);
        setPriceRangeStart(BigDecimal.valueOf(0,3));
        setPriceRangeEnd(BigDecimal.valueOf(0,3));
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
