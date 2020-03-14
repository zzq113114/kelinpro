package clean.platform.disorder.model.dto;

        import clean.platform.disorder.model.entity.vo.TbOrderVo;

public class OrderVo extends TbOrderVo {

    private String[] itemsName;

    public String[] getItemsName() {
        return itemsName;
    }

    public void setItemsName(String[] itemsName) {
        this.itemsName = itemsName;
    }
}
