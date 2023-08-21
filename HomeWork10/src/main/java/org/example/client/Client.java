package org.example.client;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Client {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id ;
    @Column
    private String name;
}
