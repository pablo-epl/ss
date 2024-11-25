package mx.com.makeitworth.ss.services.impl;

import mx.com.makeitworth.ss.dtos.request.AddFamilyMemberRequest;
import mx.com.makeitworth.ss.dtos.response.AddFamilyMemberResponse;
import mx.com.makeitworth.ss.entities.Family;
import mx.com.makeitworth.ss.entities.FamilyMember;
import mx.com.makeitworth.ss.repositories.FamilyMemberRepository;
import mx.com.makeitworth.ss.repositories.FamilyRepository;
import mx.com.makeitworth.ss.services.FamilyMemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyMemberServiceImpl implements FamilyMemberService {

    private final FamilyMemberRepository familyMemberRepository;
    private final FamilyRepository familyRepository;

    public FamilyMemberServiceImpl(FamilyMemberRepository familyMemberRepository, FamilyRepository familyRepository) {
        this.familyMemberRepository = familyMemberRepository;
        this.familyRepository = familyRepository;
    }

    @Override
    public AddFamilyMemberResponse addFamilyMember(AddFamilyMemberRequest request) {
        // Validate the family ID exists
        Family family = familyRepository.findById(request.getFamilyId())
                .orElseThrow(() -> new IllegalArgumentException("Family with ID " + request.getFamilyId() + " does not exist."));

        // Map request DTO to FamilyMember entity
        FamilyMember familyMember = new FamilyMember();
        familyMember.setName(request.getName());
        familyMember.setEmail(request.getEmail());
        familyMember.setFamily(family);

        // Save the FamilyMember entity
        familyMember = this.familyMemberRepository.save(familyMember);

        // Map to Response DTO and returns
        return new AddFamilyMemberResponse(familyMember.getId(), familyMember.getName(), familyMember.getEmail(), family.getId());
    }

    @Override
    public List<FamilyMember> getFamilyMembersByFamilyId(Long familyId) {
        return this.familyMemberRepository.findByFamilyId(familyId);
    }

    @Override
    public List<FamilyMember> getFamilyMembersByMainFamily(Long mainFamilyId){
        return this.familyMemberRepository.findByMainFamilyId(mainFamilyId);
    }
}
