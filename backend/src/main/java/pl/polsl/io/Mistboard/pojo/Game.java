package pl.polsl.io.Mistboard.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    @OneToOne(mappedBy = "game", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Description description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private User author;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Description getDescription() {
        return description;
    }
}
