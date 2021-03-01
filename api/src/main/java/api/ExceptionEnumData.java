package api;


/**
 * 业务异常码
 */
public enum ExceptionEnumData {
    /**
     * 操作成功
     */
    SUCCESS("0000", "success"),
    /**
     * 操作失败
     */
    FAIL("9999", "server is error"),
    /**
     * 0001 参数不全，必填字段为空，参数格式不正确
     */
    INTERFACE_PARAM("0001", "参数格式不正确"),
    /**
     * 0002 鉴权失败
     */
    INTERFACE_JURISDICTION("0002", "鉴权失败"),
    /**
     * 0003 token校验失败
     */
    INTERFACE_TOKEN("0003", "token校验失败"),

    /**
     * 0004 数据异常，查询失败
     */
    DATA_SELECT("0004", "数据异常，查询失败"),
    /**
     * 0005 数据插入失败
     */
    DATA_INSERT("0005", "数据插入失败"),
    /**
     * 0006 数据更新失败
     */
    DATA_UPDATE("0006", "数据更新失败"),
    /**
     * 0007 数据删除失败
     */
    DATA_DELETE("0007", "数据删除失败"),

    /**
     * 0008 数据不存在
     */
    DATA_NULL("0008", "数据不存在");
    private String name;

    private String value;

    ExceptionEnumData(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static boolean isSuccess(String name) {
        return SUCCESS.getName().equals(name);
    }

}
