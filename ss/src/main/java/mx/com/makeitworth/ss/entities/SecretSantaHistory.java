package mx.com.makeitworth.ss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "secretsantahistory")
public class SecretSantaHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "giver_id", nullable = false)
    private FamilyMember giver;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private FamilyMember receiver;

    @ManyToOne
    @JoinColumn(name = "main_family_id", nullable = false)
    private MainFamily mainFamily;

    private int year;

}
