package uz.coding.codingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String infoText;
    private String solution;   //yechim
    private String hint;       //korsatma
    private String method;     //usul
    private String active;
    @ManyToOne
    private Language language;
}
