package mx.com.makeitworth.ss.services.impl;

import mx.com.makeitworth.ss.dtos.request.AddMainFamilyRequest;
import mx.com.makeitworth.ss.dtos.response.MainFamilyResponse;
import mx.com.makeitworth.ss.entities.MainFamily;
import mx.com.makeitworth.ss.repositories.MainFamilyRepository;
import mx.com.makeitworth.ss.services.MainFamilyService;
import org.springframework.stereotype.Service;

@Service
public class MainFamilyServiceImpl implements MainFamilyService {

    private final MainFamilyRepository mainFamilyRepository;

    public MainFamilyServiceImpl(MainFamilyRepository mainFamilyRepository) {
        this.mainFamilyRepository = mainFamilyRepository;
    }

    @Override
    public MainFamilyResponse addMainFamily(AddMainFamilyRequest request) {
        // Map request DTO to entity
        MainFamily mainFamily = new MainFamily();
        mainFamily.setName(request.getName());

        // Save the main family to the database
        mainFamily = this.mainFamilyRepository.save(mainFamily);

        // Map the saved entity to a response DTO and return it
        return new MainFamilyResponse(mainFamily.getId(), mainFamily.getName());
    }

    @Override
    public MainFamily findById(Long mainFamilyId) {
        return this.mainFamilyRepository.findById(mainFamilyId).orElseThrow(
                () -> new IllegalArgumentException("Not main family found for the given id.")
        );
    }
}
