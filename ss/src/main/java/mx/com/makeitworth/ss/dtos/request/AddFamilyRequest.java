package mx.com.makeitworth.ss.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class AddFamilyRequest {
    @NotBlank
    private String name;

    @NotNull
    private Long mainFamilyId;
}
