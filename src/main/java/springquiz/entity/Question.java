package springquiz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "question")
public class Question {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "question_txt")
    @NotNull
    private String questionTxt;

    @OneToMany(mappedBy = "question",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    private Set<Alternative> alternativeList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_category")
    @NotNull
    // TODO: Cascade on category delete.
    private Category category;

    public Question() {

    }

    public Question(String questionTxt) {
        this.questionTxt = questionTxt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionTxt() {
        return questionTxt;
    }

    public void setQuestionTxt(String questionTxt) {
        this.questionTxt = questionTxt;
    }

    public Set<Alternative> getAlternativeList() {
        return alternativeList;
    }

    public void setAlternativeList(Set<Alternative> alternativeList) {
        this.alternativeList = alternativeList;
    }

    /**
     * Adding a single alternative
     *
     * @param alternative
     */
    public void addAlternative(Alternative alternative) {
        if (alternativeList == null)
            alternativeList = new HashSet<>();

        alternativeList.add(alternative);
        alternative.setQuestion(this);
    }

    /**
     * Removing alternative
     *
     * @param alternative
     */
    public void removeAlternative(Alternative alternative) {
        if (alternativeList == null)
            return;

        alternativeList.remove(alternative);
    }

    /**
     * Clearing all alternatives, adding new.
     *
     * @param alternatives new alternatives.
     */
    public void updateAlternativeList(Set<Alternative> alternatives) {
        if (alternativeList == null) {
            this.alternativeList = alternatives;
            return;
        }
        alternativeList.clear();
        alternatives.forEach(this::addAlternative);
    }

    /**
     * Making all alternatives reference this question.
     */
    public void validateAlternatives() {
        if (alternativeList == null)
            return;

        alternativeList.forEach(alternative -> alternative.setQuestion(this));
    }

    @NotNull
    public Category getCategory() {
        return category;
    }

    public void setCategory(@NotNull Category category) {
        this.category = category;
    }
}
