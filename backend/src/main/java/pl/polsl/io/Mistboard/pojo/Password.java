package pl.polsl.io.Mistboard.pojo;

import javax.persistence.*;

@Entity
public class Password {
    @Id
    private Integer id;


    @OneToOne
    @MapsId
    private User user;

    private String password;

}
