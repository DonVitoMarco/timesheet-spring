package pl.thewalkingcode.model;


public class DepartmentFormDTO {

    private String name;
    private String action;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "DepartmentFormDTO{" +
                "name='" + name + '\'' +
                ", action='" + action + '\'' +
                '}';
    }

}
