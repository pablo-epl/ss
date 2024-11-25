package mx.com.makeitworth.ss.repositories;

import mx.com.makeitworth.ss.entities.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long> {

    // Find all family members within a specific family
    List<FamilyMember> findByFamilyId(Long familyId);

    // Find all family members within a main family (across multiple families)
    @Query("SELECT f FROM FamilyMember f WHERE f.family.mainFamily.id = :mainFamilyId")
    List<FamilyMember> findByMainFamilyId(@Param("mainFamilyId") Long mainFamilyId);

    // Find family members by name (useful for user interfaces or validations)
    List<FamilyMember> findByNameContaining(String name);

    // Retrieve family members for a specific family, excluding specific IDs
    @Query("SELECT f FROM FamilyMember f WHERE f.family.id = :familyId AND f.id NOT IN :excludedIds")
    List<FamilyMember> findEligibleFamilyMembers(
            @Param("familyId") Long familyId,
            @Param("excludedIds") List<Long> excludedIds
    );

    // Retrieve family members for a specific main family, excluding specific IDs
    @Query("SELECT f FROM FamilyMember f WHERE f.family.mainFamily.id = :mainFamilyId AND f.id NOT IN :excludedIds")
    List<FamilyMember> findEligibleFamilyMembersInMainFamilyConsiderateExcludedIds(
            @Param("mainFamilyId") Long mainFamilyId,
            @Param("excludedIds") List<Long> excludedIds
    );

    // Retrieve family members for a specific main family, excluding specific IDs
    @Query("SELECT f FROM FamilyMember f WHERE f.family.mainFamily.id = :mainFamilyId")
    List<FamilyMember> findEligibleFamilyMembersInMainFamily(
            @Param("mainFamilyId") Long mainFamilyId
    );
}


