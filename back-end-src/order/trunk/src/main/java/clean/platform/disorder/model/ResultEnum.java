package clean.platform.disorder.model;

public enum ResultEnum {

    RESULT_SUCCESS(2000,"success"),
    RESULT_COMPANY_ADD_ERROR(2001,"Add New Company Error"),
    RESULT_COMPANY_QUERY_ERROR(2002,"Query Company Error"),
    RESULT_COMPANY_DUPLICATE_NAME(2003, "Company Name Has Exists"),
    RESULT_COMPANY_UPDATE_ERROR(2004, "Company Update Error"),
    RESULT_COMPANY_DELETE_ERROR(2005, "Company Delete Error"),
    RESULT_COMPANY_SERVICE_ITEMS_QUERY_ERROR(2006,"Company Service Items Query Error"),

    RESULT_ORDER_ADD_ERROR(2007,"Order add Error"),
    RESULT_ORDER_UPDATE_ERROR(2008,"Order Update Error"),
    RESULT_ORDER_DELETE_ERROR(2009,"Order Delete Error"),
    RESULT_ORDER_DUPLICATE_NAME(2010,"Order Has Exists"),
    RESULT_ORDER_QUERY_ERROR(2011,"Order Query Error"),

    RESULT_STAFF_ADD_ERROR(2012,"Staff add ERROR"),
    RESULT_STAFF_UPDATE_ERROR(2013,"Staff update Error"),
    RESULT_STAFF_DELETE_ERROR(2014,"Staff delete Error"),
    RESULT_STAFF_DUPLICATE_NAME(2015,"Staff has Exists"),
    RESULT_STAFF_QUERY_ERROR(2016, "Staff Query Error"),

    RESULT_DISPATCH_ORDER_ERROR(2017,"Order Dispatch is Error"),
    RESULT_ORDER_FINISHED_ERROR(2018,"Order Finished Error"),

    RESULT_QUERY_ORIGINS_ERROR(2019,"Origin Query Error"),

    RESULT_USER_ADD_ERROR(2020,"User Add Error"),
    RESULT_USER_MODIFY_ERROR(2021,"User Modify Error"),
    RESULT_USER_QUERY_INFO_ERROR(2022,"User Query Info Error"),
    RESULT_USER_QUERY_PAGE_ERROR(2023,"User Query Page Error"),
    RESULT_USER_DELETE_ERROR(2024,"User Delete Error"),

    RESULT_USER_LOGIN_ERROR(2025,"User Login Error"),
    RESULT_USER_LOGIN_USER_OR_PASSWORD_ERROR(2026,"User Login UserAccount or Password Error"),

    RESULT_ROLE_QUERY_LIST_ERROR(2027,"Role Query List Error"),
    RESULT_QUERY_COMPANY_SERVICE_ITEM_CONFIG_ERROR(2028,"查询家政公司服务配置信息失败"),
    RESULT_SERVICE_ITEM_CONFIG_ERROR(2029,"家政公司配置信息新增/修改异常"),
    RESULT_ORDER_CANCEL_ERROR(2030,"下单取消异常"),
    RESULT_ORDER_AGAIN_ERROR(2031,"下单重新分派异常"),
    RESULT_ORDER_RESET_ERROR(2032,"重新分派订单异常"),
    RESULT_FUN_MENU_QUERY_ERROR(2033,"查询所有功能列表失败"),
    RESULT_ROLE_ADD_ERROR(2034,"角色增加失败"),
    RESULT_ROLE_INFO_ERROR(2034,"查询角色详情失败"),
    RESULT_ROLE_DELETE_ERROR(2035,"角色删除失败"),
    RESULT_ROLE_DUPLICATE_ERROR(2035,"角色删除失败"),
    RESULT_ROLE_UPDATE_ERROR(2036,"角色删除失败"),
    RESULT_ROLE_FUNMENU_ERROR(2036,"查询角色功能权限失败");


    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
