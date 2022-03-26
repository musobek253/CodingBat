package uz.coding.codingbat.entity;
import lombok.*;
import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    private Integer correct;
    @OneToOne
    private Task task;
    @OneToOne
    private Users users;

}
