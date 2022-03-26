package uz.coding.codingbat.payload;


import lombok.Data;


@Data
public class AnswerDto {
    private String text;
    private Integer correct;
    private Integer taskId;
    private Integer usersId;

}
