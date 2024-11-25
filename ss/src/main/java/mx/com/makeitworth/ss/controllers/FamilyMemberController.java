package mx.com.makeitworth.ss.controllers;

import jakarta.validation.Valid;
import mx.com.makeitworth.ss.dtos.request.AddFamilyMemberRequest;
import mx.com.makeitworth.ss.dtos.response.AddFamilyMemberResponse;
import mx.com.makeitworth.ss.services.FamilyMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/familyMembers")
@Validated
public class FamilyMemberController {

    private final FamilyMemberService familyMemberServiceImpl;

    public FamilyMemberController(FamilyMemberService familyMemberServiceImpl) {
        this.familyMemberServiceImpl = familyMemberServiceImpl;
    }

    @PostMapping
    public ResponseEntity<AddFamilyMemberResponse> addFamilyMember(
            @Valid @RequestBody AddFamilyMemberRequest request) {
        AddFamilyMemberResponse response = familyMemberServiceImpl.addFamilyMember(request);
        return ResponseEntity.ok(response);
    }
}

