package pl.polsl.io.Mistboard.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    @JsonManagedReference
    private Set<Game> createdGames;

    private Role role;

    public Integer getId(){
        return id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Game> getCreatedGames() {
        return createdGames;
    }
}
