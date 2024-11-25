package mx.com.makeitworth.ss.repositories;

import mx.com.makeitworth.ss.entities.SecretSantaHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecretSantaHistoryRepository extends JpaRepository<SecretSantaHistory, Long> {
    @Query("SELECT s FROM SecretSantaHistory s WHERE s.mainFamily.id = :mainFamilyId AND s.year > :yearThreshold")
    List<SecretSantaHistory> findByMainFamilyAndRecentYears(@Param("mainFamilyId") Long mainFamilyId, @Param("yearThreshold") int yearThreshold);

    /**
     * Finds Secret Santa history for a main family after a certain year.
     * @param mainFamilyId The main family ID.
     * @param year The minimum year to include.
     * @return A list of Secret Santa history records.
     */
    List<SecretSantaHistory> findByMainFamilyIdAndYearGreaterThan(Long mainFamilyId, int year);

}
