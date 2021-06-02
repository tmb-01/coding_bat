package uz.pdp.coding_bat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.coding_bat.entity.Topic;

import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProblemDto {
    private String name;
    private String question;
    private String hint;
    private String solution;
    private String editor;
    private boolean completed;

    private Long languageId;
}
