package mx.com.makeitworth.ss.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "familymember")
public class FamilyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "family_id", nullable = false)
    private Family family;

    private String email;
}
