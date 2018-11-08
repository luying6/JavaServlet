package main.java.com.javaservlet.code.model;

/**
 * @author luying
 * @date 2018/10/31
 */
public class User {
    private Integer id;
    private String adminName;
    private String password;
    private Integer age;
    private String creatTime;
    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String toString(){
        return "id:" + id + " adminName:"+adminName + " password:" + password+ " age:"+age
                + " createTime:" + creatTime+ " updateTime:"+ updateTime;
    }
}
