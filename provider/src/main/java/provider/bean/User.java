package provider.bean;

import javax.persistence.*;

/**
 * @author lirui
 * @ClassName:
 * @Description:
 * @date 2020/6/3 10:07
 */
@Entity
@Table(name = "user")
public class User {
    public User() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    @Column
    private String username;

    @Column
    private String password;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

