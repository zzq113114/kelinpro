/**
 * 版权信息: © 鑫为科技
 */
package clean.platform.disorder.model.entity.vo;

import clean.platform.disorder.model.entity.TbCompanyItem;

/*****************************************************************************
 *创建时间 : 2019年03月06日
 *实现功能 : TODO
 *作者 : code
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2019年03月06日 v0.0.1 AUTO CREATE 创建
 *****************************************************************************/
public class TbCompanyItemVo extends TbCompanyItem {

    /**
     * 服务项名称
     */
    private String itemName;

    private TbServiceItemConfigVo serviceItemConfigVo;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public TbServiceItemConfigVo getServiceItemConfigVo() {
        return serviceItemConfigVo;
    }

    public void setServiceItemConfigVo(TbServiceItemConfigVo serviceItemConfigVo) {
        this.serviceItemConfigVo = serviceItemConfigVo;
    }
}
