package mx.com.makeitworth.ss.services.impl;

import mx.com.makeitworth.ss.dtos.response.SecretSantaPairingResponse;
import mx.com.makeitworth.ss.entities.FamilyMember;
import mx.com.makeitworth.ss.entities.MainFamily;
import mx.com.makeitworth.ss.entities.SecretSantaHistory;
import mx.com.makeitworth.ss.repositories.SecretSantaHistoryRepository;
import mx.com.makeitworth.ss.services.FamilyMemberService;
import mx.com.makeitworth.ss.services.MainFamilyService;
import mx.com.makeitworth.ss.services.SecretSantaService;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SecretSantaServiceImpl implements SecretSantaService {

    private final FamilyMemberService familyMemberService;
    private final MainFamilyService mainFamilyService;
    private final SecretSantaHistoryRepository secretSantaHistoryRepository;

    public SecretSantaServiceImpl(FamilyMemberService familyMemberService, MainFamilyService mainFamilyService,
                                  SecretSantaHistoryRepository secretSantaHistoryRepository) {
        this.familyMemberService = familyMemberService;
        this.mainFamilyService = mainFamilyService;
        this.secretSantaHistoryRepository = secretSantaHistoryRepository;
    }

    @Override
    @Transactional
    public List<SecretSantaPairingResponse> generatePairings(Long mainFamilyId, int year) {
        MainFamily mainFamily = this.mainFamilyService.findById(mainFamilyId);

        // Fetch all family members using FamilyMemberService
        List<FamilyMember> allMembers = this.familyMemberService.getFamilyMembersByMainFamily(mainFamilyId);

        if (allMembers.size() < 2) {
            throw new IllegalArgumentException("Not enough family members to generate pairings.");
        }

        // Fetch Secret Santa history for the past three years
        int minYear = year - 3;
        List<SecretSantaHistory> recentHistory = this.secretSantaHistoryRepository.findByMainFamilyIdAndYearGreaterThan(mainFamilyId, minYear);

        // Build a set of invalid pairings from history
        Set<String> invalidPairings = recentHistory.stream()
                .map(history -> history.getGiver().getId() + "-" + history.getReceiver().getId())
                .collect(Collectors.toSet());

        // Generate pairings
        return generateValidPairings(allMembers, invalidPairings, mainFamily, year);
    }

    @Override
    public void saveNewlySecretSantaService(FamilyMember giver, FamilyMember receiver, int year, MainFamily mainFamily) {
        SecretSantaHistory history = new SecretSantaHistory();
        history.setGiver(giver);
        history.setReceiver(receiver);
        history.setMainFamily(mainFamily);
        history.setYear(year);
        secretSantaHistoryRepository.save(history);
    }

    private List<SecretSantaPairingResponse> generateValidPairings(List<FamilyMember> allMembers,
                                                                   Set<String> invalidPairings,
                                                                   MainFamily mainFamily, int year) {
        List<FamilyMember> receivers = new ArrayList<>(allMembers);
        Collections.shuffle(receivers); // Randomize the list of receivers
        List<SecretSantaPairingResponse> pairings = new ArrayList<>();

        for (FamilyMember giver : allMembers) {
            FamilyMember receiver = this.findValidReceiver(giver, receivers, invalidPairings);
            if (receiver == null) {
                throw new IllegalStateException("Unable to generate valid pairings with the given constraints.");
            }

            this.saveNewlySecretSantaService(giver, receiver, year, mainFamily);
            // Create a pairing DTO with both names and IDs
            pairings.add(new SecretSantaPairingResponse(
                    giver.getId(),
                    giver.getName(),
                    giver.getFamily().getName(),
                    receiver.getId(),
                    receiver.getName(),
                    receiver.getFamily().getName()
            ));

            receivers.remove(receiver); // Remove the receiver from the list to prevent reuse
            invalidPairings.add(giver.getId() + "-" + receiver.getId()); // Add to invalid pairings
        }

        return pairings;
    }

    private FamilyMember findValidReceiver(FamilyMember giver, List<FamilyMember> receivers,
                                           Set<String> invalidPairings) {
        for (FamilyMember receiver : receivers) {
            // Exclude self-pairing
            if (giver.getId().equals(receiver.getId())) continue;

            // Exclude same-family pairings
            if (giver.getFamily().getId().equals(receiver.getFamily().getId())) continue;

            // Exclude pairings that occurred in the past three years
            if (invalidPairings.contains(giver.getId() + "-" + receiver.getId())) continue;

            return receiver; // Valid receiver found
        }

        return null; // No valid receiver found
    }

}