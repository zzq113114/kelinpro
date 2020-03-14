package clean.platform.disorder.model.query;

/**
 * 用户查询对象
 *
 * @author ZX
 * @create 2020-02-03 19:34
 **/

public class QueryUser extends BaseQuery {

    private String userName;

    private String userAccount;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
