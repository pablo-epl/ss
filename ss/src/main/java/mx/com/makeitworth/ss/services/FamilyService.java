package mx.com.makeitworth.ss.services;

import mx.com.makeitworth.ss.dtos.request.AddFamilyRequest;
import mx.com.makeitworth.ss.dtos.response.FamilyResponse;
import mx.com.makeitworth.ss.entities.Family;

import java.util.List;

public interface FamilyService {

    /**
     * Adds a new family to a main family group.
     * @param request The request containing family details.
     * @return The response containing the created family details.
     */
    FamilyResponse addFamily(AddFamilyRequest request);

    List<Family> getFamiliesByMainFamilyId(Long mainFamilyId);

}
