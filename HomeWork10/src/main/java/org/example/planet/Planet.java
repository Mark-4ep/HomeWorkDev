package org.example.planet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "Planet")
@Entity
@Data
public class Planet {
    @Id
    private String id;
    @Column
    private String name;
}
