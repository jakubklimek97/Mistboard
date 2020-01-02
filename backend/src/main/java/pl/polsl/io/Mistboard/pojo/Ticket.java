package pl.polsl.io.Mistboard.pojo;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "helper_id")
    private User helperId;

    private Boolean isEscalated;

    @Temporal(TemporalType.TIME)
    private Date modificationDate;

    private TicketStatus ticketStatus;

    @OneToMany(mappedBy = "ticket")
    private Set<TicketMessage> messages;

}
