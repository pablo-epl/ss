package mx.com.makeitworth.ss.services;

import mx.com.makeitworth.ss.dtos.response.SecretSantaPairingResponse;
import mx.com.makeitworth.ss.entities.FamilyMember;
import mx.com.makeitworth.ss.entities.MainFamily;

import java.util.List;

public interface SecretSantaService {
    List<SecretSantaPairingResponse> generatePairings(Long mainFamilyId, int year);

    void saveNewlySecretSantaService(FamilyMember giver, FamilyMember receiver, int year, MainFamily mainFamily);
}
