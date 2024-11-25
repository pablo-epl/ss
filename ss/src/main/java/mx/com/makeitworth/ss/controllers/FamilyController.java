package mx.com.makeitworth.ss.controllers;

import jakarta.validation.Valid;
import mx.com.makeitworth.ss.dtos.request.AddFamilyRequest;
import mx.com.makeitworth.ss.dtos.response.FamilyResponse;
import mx.com.makeitworth.ss.services.FamilyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/families")
@Validated
public class FamilyController {

    private final FamilyService familyService;

    public FamilyController( FamilyService familyService) {
        this.familyService = familyService;
    }

    @PostMapping
    public ResponseEntity<FamilyResponse> addFamily(
            @Valid @RequestBody AddFamilyRequest request) {
        FamilyResponse response = this.familyService.addFamily(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

