package mx.com.makeitworth.ss.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "family")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "main_family_id", nullable = false)
    private MainFamily mainFamily;

}

