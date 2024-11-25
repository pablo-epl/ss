package mx.com.makeitworth.ss.repositories;

import mx.com.makeitworth.ss.entities.MainFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MainFamilyRepository extends JpaRepository<MainFamily, Long> {
    Optional<MainFamily> findMainFamiliesById(Long id);
}
