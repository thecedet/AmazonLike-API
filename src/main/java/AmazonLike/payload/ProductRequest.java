package AmazonLike.payload;

import java.util.List;

import AmazonLike.model.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductRequest {
    
    @ApiModelProperty(position = 0)
    private Integer id;
    @ApiModelProperty(position = 1)
    private String name;
    @ApiModelProperty(position = 2)
    private float price;
    @ApiModelProperty(position = 3)
    private List<Category> categories;
    @ApiModelProperty(position = 4)
    private String shortDescription;
    private String description;
    
}