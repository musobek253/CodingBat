package uz.coding.codingbat.payload;

import lombok.Data;


@Data
public class TaskDto {
    private String name;
    private String infoText;
    private String solution;
    private String hint;
    private String method;
    private String active;
    private Integer languageId;
}
