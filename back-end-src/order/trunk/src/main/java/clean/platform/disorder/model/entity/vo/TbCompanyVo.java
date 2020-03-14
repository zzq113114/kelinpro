/**
 * 版权信息: © 鑫为科技
 */
package clean.platform.disorder.model.entity.vo;

import clean.platform.disorder.model.entity.TbCompany;

import java.util.List;

/*****************************************************************************
 *创建时间 : 2019年03月06日
 *实现功能 : TODO  从数据库到前端
 *作者 : code
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2019年03月06日 v0.0.1 AUTO CREATE 创建
 *****************************************************************************/
public class TbCompanyVo extends TbCompany {

    /**
     * 选择的服务项目列表
     */
    private List<Integer>  items;

    private String startCreateTime;

    private String endCreateTime;

    public List<Integer> getItems() {
        return items;
    }

    public void setItems(List<Integer> items) {
        this.items = items;
    }

    public String getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(String startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public String getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(String endCreateTime) {
        this.endCreateTime = endCreateTime;
    }
}
