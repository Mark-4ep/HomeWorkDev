package org.example.client;

import jakarta.persistence.*;
import lombok.Data;
import org.example.ticket.Ticket;

import java.util.List;
import java.util.Set;


@Data
@Entity
public class Client {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id ;
    @Column
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<Ticket> tickets;

}
