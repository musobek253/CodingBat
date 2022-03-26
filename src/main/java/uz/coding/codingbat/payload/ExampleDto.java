package uz.coding.codingbat.payload;

import lombok.Data;
import uz.coding.codingbat.entity.Task;
import javax.validation.constraints.NotNull;

@Data
public class ExampleDto {

    @NotNull(message = "text bo'sh bo'lmasligi kerak")
    private String text;
    private Task taskId;
}
