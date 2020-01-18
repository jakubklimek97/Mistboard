package pl.polsl.io.Mistboard.pojo;

import javax.persistence.*;

@Entity
public class Password {
    @Id
    private Integer id;


    @OneToOne
    @MapsId
    private User user;

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public Password(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }
}
