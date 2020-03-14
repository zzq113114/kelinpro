package clean.platform.disorder.model.query;

import java.io.Serializable;

/**
 * 所有查询条件基类
 *
 * @author ZX
 * @create 2020-03-09 23:13
 **/

public class BaseQuery implements Serializable {

    private Integer pageNo;

    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
