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
            query = "SELECT d FROM Dinner AS d ORDER BY d.dinner_id DESC"
            ),
    @NamedQuery(
            name = "getDinnersCount",
            query = "SELECT COUNT(d) FROM Dinner AS d"
            ),
})
@Entity
public class Dinner {
    @Id
    @Column(name = "dinner_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dinner_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "dinner", length = 225, nullable = false)
    private String dinner;

    public Integer getDinner_id(){
        return dinner_id;
    }

    public void setDinner_id(Integer dinner_id){
        this.dinner_id = dinner_id;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public String getDinner(){
        return dinner;
    }

    public void setDinner(String dinner){
        this.dinner = dinner;
    }
}
