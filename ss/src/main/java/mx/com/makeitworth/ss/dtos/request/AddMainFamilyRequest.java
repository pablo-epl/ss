package mx.com.makeitworth.ss.dtos.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMainFamilyRequest {
    @NotBlank
    private String name;
}
