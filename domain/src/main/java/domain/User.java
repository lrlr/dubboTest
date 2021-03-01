package domain;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description: TODO 
 * @Author lirui
 * @Date 2020/3/10 
 * @Version V1.0
**/

public class User implements Serializable {
    public User(Integer id, Integer age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
 public  User(){}
    private Integer id;
private Integer age;
private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
