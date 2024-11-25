package mx.com.makeitworth.ss.services;

import mx.com.makeitworth.ss.dtos.request.AddFamilyMemberRequest;
import mx.com.makeitworth.ss.dtos.response.AddFamilyMemberResponse;
import mx.com.makeitworth.ss.entities.FamilyMember;

import java.util.List;

public interface FamilyMemberService {
    AddFamilyMemberResponse addFamilyMember(AddFamilyMemberRequest request);

    List<FamilyMember> getFamilyMembersByFamilyId(Long familyId);

    List<FamilyMember> getFamilyMembersByMainFamily(Long mainFamilyId);

}
