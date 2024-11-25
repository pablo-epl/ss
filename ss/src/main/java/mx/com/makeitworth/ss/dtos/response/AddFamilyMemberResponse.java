package mx.com.makeitworth.ss.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddFamilyMemberResponse {
    private Long id;
    private String name;
    private String email;
    private Long familyId;
}
