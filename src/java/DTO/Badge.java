
package DTO;

/**
 *
 * @author Nguyen Tien Dat
 */
public class Badge {

    private String IDBadge;
    private String name;
    private String condition;
    private String description;

    public Badge() {
    }

    public Badge(String IDBadge, String name, String condition, String description) {
        this.IDBadge = IDBadge;
        this.name = name;
        this.condition = condition;
        this.description = description;
    }

    // Getter và Setter
    public String getIDBadge() {
        return IDBadge;
    }

    public void setIDBadge(String IDBadge) {
        this.IDBadge = IDBadge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
