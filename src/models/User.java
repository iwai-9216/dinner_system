package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "users")
@NamedQueries({
    @NamedQuery(
            name = "getAllUsers",
            query = "SELECT u FROM User AS u ORDER BY u.id DESC"
            ),
    @NamedQuery(
            name = "getAllUsersCount",
            query = "SELECT COUNT(u) FROM User AS u"
            ),
    @NamedQuery(
            name = "checkRegisteredUser_id",
            query = "SELECT COUNT(u) FROM User AS u WHERE u.user_id = :user_id"
            ),
    @NamedQuery(
            name = "checkLoginCodeAndPassword",
            query = "SELECT u FROM User AS u WHERE u.delete_flag = 0 AND u.user_id = :user_id AND u.password = :pass"
            )
})
@Entity
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private String user_id;

    @Column(name = "user_name", nullable = false)
    private String user_name;

    @Column(name = "password", length = 64, nullable = false)
    private String password;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;


    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getUser_id(){
        return user_id;
    }

    public void setUser_id(String user_id){
        this.user_id = user_id;
    }

    public String getUser_name(){
        return user_name;
    }

    public void setUser_name(String user_name){
        this.user_name = user_name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }
}
