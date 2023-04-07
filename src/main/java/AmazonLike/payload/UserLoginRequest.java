package AmazonLike.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserLoginRequest {
    
    private String username;
    private String password;

}
