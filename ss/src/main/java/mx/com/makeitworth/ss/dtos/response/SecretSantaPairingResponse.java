package mx.com.makeitworth.ss.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SecretSantaPairingResponse {
    private Long giverId;
    private String giverName;
    private String giverFamily;
    private Long receiverId;
    private String receiverName;
    private String receiverFamily;
}
