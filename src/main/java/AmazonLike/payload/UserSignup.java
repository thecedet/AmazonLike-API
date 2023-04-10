package AmazonLike.payload;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSignup {
  
  @ApiModelProperty(position = 0)
  private String username;
  @ApiModelProperty(position = 1)
  private String email;
  @ApiModelProperty(position = 2)
  private String password;

  public UserSignup(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

}
