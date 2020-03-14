/**
 * 版权信息: © 鑫为科技
 */
package clean.platform.disorder.model.entity.vo;

import clean.platform.disorder.model.entity.TbFunMenu;
import clean.platform.disorder.model.entity.TbRole;

import java.util.List;

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
public class TbRoleVo extends TbRole {

    /**
     * 授权功能列表
     */
    List<Integer> funMenuIds;

    List<TbFunMenuVo> funMenus;

    String funMenuStr;

    public List<Integer> getFunMenuIds() {
        return funMenuIds;
    }

    public void setFunMenuIds(List<Integer> funMenuIds) {
        this.funMenuIds = funMenuIds;
    }

    public List<TbFunMenuVo> getFunMenus() {
        return funMenus;
    }

    public void setFunMenus(List<TbFunMenuVo> funMenus) {
        this.funMenus = funMenus;
    }

    public String getFunMenuStr() {
        return funMenuStr;
    }

    public void setFunMenuStr(String funMenuStr) {
        this.funMenuStr = funMenuStr;
    }
}
