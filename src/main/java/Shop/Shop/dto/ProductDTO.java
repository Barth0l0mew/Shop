package Shop.Shop.dto;

import Shop.Shop.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private String title;
    private BigDecimal price;
    private String description;
    private String link;
    private List<Category> categories;
}
