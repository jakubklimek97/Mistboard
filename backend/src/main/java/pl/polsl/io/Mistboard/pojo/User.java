package pl.polsl.io.Mistboard.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    private String role;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        //this.password = passwordEncoder.encode(password);
    }



    public Integer getId(){
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Game> getCreatedGames() {
        return createdGames;
    }
}
