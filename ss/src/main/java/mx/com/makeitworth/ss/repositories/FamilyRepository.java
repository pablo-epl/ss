package mx.com.makeitworth.ss.repositories;

import mx.com.makeitworth.ss.entities.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {
    List<Family> findByMainFamilyId(Long mainFamilyId);
}
