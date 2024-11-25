package mx.com.makeitworth.ss.services.impl;

import mx.com.makeitworth.ss.dtos.request.AddFamilyRequest;
import mx.com.makeitworth.ss.dtos.response.FamilyResponse;
import mx.com.makeitworth.ss.entities.Family;
import mx.com.makeitworth.ss.entities.MainFamily;
import mx.com.makeitworth.ss.repositories.FamilyRepository;
import mx.com.makeitworth.ss.repositories.MainFamilyRepository;
import mx.com.makeitworth.ss.services.FamilyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class FamilyServiceImpl implements FamilyService {

    private final FamilyRepository familyRepository;
    private final MainFamilyRepository mainFamilyRepository;

    public FamilyServiceImpl(FamilyRepository familyRepository, MainFamilyRepository mainFamilyRepository) {
        this.familyRepository = familyRepository;
        this.mainFamilyRepository = mainFamilyRepository;
    }

    @Override
    public FamilyResponse addFamily(AddFamilyRequest request) {
        // Validate the main family ID exists
        MainFamily mainFamily = this.mainFamilyRepository.findById(request.getMainFamilyId()).orElseThrow(
                () ->  new IllegalArgumentException("Main family with ID " + request.getMainFamilyId() + " does not exist."));

        // Map request DTO to entity
        Family family = new Family();
        family.setName(request.getName());
        family.setMainFamily(mainFamily);

        // Save the family to the database
        family = familyRepository.save(family);

        // Map the saved entity to a response DTO
        FamilyResponse response = new FamilyResponse();
        response.setId(family.getId());
        response.setName(family.getName());
        response.setMainFamilyId(family.getMainFamily().getId());
        return response;
    }

    @Override
    public List<Family> getFamiliesByMainFamilyId(Long mainFamilyId) {
        return familyRepository.findByMainFamilyId(mainFamilyId);
    }
}
