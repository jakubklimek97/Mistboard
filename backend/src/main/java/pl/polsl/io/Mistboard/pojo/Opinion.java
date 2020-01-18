package pl.polsl.io.Mistboard.pojo;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    Game game;

    @ManyToOne
    User user;

    private Integer rating;

    String opinion;
}
