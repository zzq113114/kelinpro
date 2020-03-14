package clean.platform.disorder.model.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public class MenuActionInfo implements Serializable {

    private String name;

    private String path;

    private List<MenuActionInfo> child;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<MenuActionInfo> getChild() {
        return child;
    }

    public void setChild(List<MenuActionInfo> child) {
        this.child = child;
    }
}
