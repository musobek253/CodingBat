package uz.coding.codingbat.payload;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CategoryDto {

    @NotNull(message = "name bo'sh bo'lmasligi kerak")
    private String name;
    @NotNull(message = "tasnifi bo'sh bo'masligi kerak")
    private String classification;
    @NotNull(message = "category tanlanmagan")
    private List<Integer> languageId;

}
