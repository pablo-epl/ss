package mx.com.makeitworth.ss.controllers;

import lombok.extern.slf4j.Slf4j;
import mx.com.makeitworth.ss.dtos.response.SecretSantaPairingResponse;
import mx.com.makeitworth.ss.services.SecretSantaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/secretSanta")
@Validated
public class SecretSantaController {

    private final SecretSantaService secretSantaServiceImpl;

    public SecretSantaController(SecretSantaService secretSantaServiceImpl) {
        this.secretSantaServiceImpl = secretSantaServiceImpl;
    }

    @PostMapping("/generate")
    public ResponseEntity<List<SecretSantaPairingResponse>> generateSecretSantaPairings(
            @RequestParam Long mainFamilyId,
            @RequestParam int year) {
        List<SecretSantaPairingResponse> pairings = this.secretSantaServiceImpl.generatePairings(mainFamilyId, year);
        return ResponseEntity.ok(pairings);
    }
}
