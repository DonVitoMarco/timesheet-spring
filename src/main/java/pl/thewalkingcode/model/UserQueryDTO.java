package pl.thewalkingcode.model;

public class UserQueryDTO {

    private int index;
    private String username;
    private boolean enable;
    private String roles;
    private String department;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "UserQueryDTO{" +
                "index=" + index +
                ", username='" + username + '\'' +
                ", enable=" + enable +
                ", roles='" + roles + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

}
