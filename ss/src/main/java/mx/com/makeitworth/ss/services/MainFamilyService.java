package mx.com.makeitworth.ss.services;

import mx.com.makeitworth.ss.dtos.request.AddMainFamilyRequest;
import mx.com.makeitworth.ss.dtos.response.MainFamilyResponse;
import mx.com.makeitworth.ss.entities.MainFamily;

public interface MainFamilyService {

    /**
     * Adds a new main family.
     * @param request The request containing the main family details.
     * @return The response containing the created main family details.
     */
    MainFamilyResponse addMainFamily(AddMainFamilyRequest request);

    MainFamily findById(Long mainFamilyId);
}

