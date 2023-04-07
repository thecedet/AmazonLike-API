package AmazonLike.payload;

import java.util.List;

import AmazonLike.model.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserResponse {

  @ApiModelProperty(position = 0)
  private Integer id;
  @ApiModelProperty(position = 1)
  private String username;
  @ApiModelProperty(position = 2)
  private String email;
  @ApiModelProperty(position = 3)
  List<Role> appUserRoles;

}
