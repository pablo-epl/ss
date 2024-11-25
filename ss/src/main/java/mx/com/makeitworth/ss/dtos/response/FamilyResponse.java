package mx.com.makeitworth.ss.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FamilyResponse {
    private Long id;
    private String name;
    private Long mainFamilyId;
}

