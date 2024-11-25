package mx.com.makeitworth.ss.controllers;

import jakarta.validation.Valid;
import mx.com.makeitworth.ss.dtos.request.AddMainFamilyRequest;
import mx.com.makeitworth.ss.dtos.response.MainFamilyResponse;
import mx.com.makeitworth.ss.services.MainFamilyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mainfamilies")
@Validated
public class MainFamilyController {

    private final MainFamilyService mainFamilyServiceImpl;

    public MainFamilyController(MainFamilyService mainFamilyServiceImpl) {
        this.mainFamilyServiceImpl = mainFamilyServiceImpl;
    }

    @PostMapping
    public ResponseEntity<MainFamilyResponse> addMainFamily(
            @Valid @RequestBody AddMainFamilyRequest request) {
        MainFamilyResponse response = this.mainFamilyServiceImpl.addMainFamily(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}