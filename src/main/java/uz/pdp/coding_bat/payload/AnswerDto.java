package uz.pdp.coding_bat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.coding_bat.entity.Problem;
import uz.pdp.coding_bat.entity.UserEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnswerDto {
    private Long id;
    private String text;
    private boolean isCorrect;
    private Long userId;
    private Long problemId;
}
