package uz.pdp.coding_bat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.coding_bat.entity.ProgrammingLanguage;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TopicDto {
    private String title;
    private int rating;
    private String description;
    private boolean completed;
    private Long languageId;
}
