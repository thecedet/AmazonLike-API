package AmazonLike.payload;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ErrorResponse {
    
    @ApiModelProperty(position = 0)
    private final String type;
    @ApiModelProperty(position = 1)
    private final String message;
    @ApiModelProperty(position = 2)
    private final int code;
    @ApiModelProperty(position = 3)
    private final String codeMessage;


}
