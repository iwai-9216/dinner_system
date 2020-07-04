package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name="dinners")
@NamedQueries({
    @NamedQuery(
            name = "getAllDinners",
            query = "SELECT d FROM Dinner AS d ORDER BY d.id DESC"
            ),
    @NamedQuery(
            name = "getDinnersCount",
            query = "SELECT COUNT(d) FROM Dinner AS d"
            ),
    @NamedQuery(
            name = "getMyAllDinners",
            query = "SELECT d FROM Dinner AS d WHERE d.user = :user ORDER BY d.id DESC"
            ),
    @NamedQuery(
            name = "getMyDinnersCount",
            query = "SELECT COUNT(d) FROM Dinner AS d WHERE d.user = :user"
            )
})
@Entity
public class Dinner {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "dish", length = 225, nullable = false)
    private String dish;


    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public String getDish(){
        return dish;
    }

    public void setDish(String dish){
        this.dish = dish;
    }
}
