package uz.pdp.coding_bat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private int rating;
    @Column(nullable = false)
    private String description;
    private boolean completed;
    @ManyToOne(optional = false)
    private ProgrammingLanguage language;
}
