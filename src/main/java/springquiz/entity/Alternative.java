package springquiz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "alternative")
public class Alternative {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "alternative_txt")
    @NotNull
    private String alternativeTxt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_question")
    @JsonIgnore
    private Question question;

    @Column(name = "is_correct")
    @NotNull
    private Boolean isCorrect;

    public Alternative() {

    }

    public Alternative(String alternativeTxt, Boolean isCorrect, Question question) {
        this.alternativeTxt = alternativeTxt;
        this.isCorrect = isCorrect;
        this.question = question;
    }

    public Alternative(String alternativeTxt, Boolean isCorrect) {
        this.alternativeTxt = alternativeTxt;
        this.isCorrect = isCorrect;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAlternativeTxt(@NotNull String alternativeTxt) {
        this.alternativeTxt = alternativeTxt;
    }


    @NotNull
    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(@NotNull Boolean correct) {
        isCorrect = correct;
    }

    public Integer getId() {
        return id;
    }

    public String getAlternativeTxt() {
        return alternativeTxt;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alternative that = (Alternative) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(alternativeTxt, that.alternativeTxt) &&
                Objects.equals(question, that.question) &&
                Objects.equals(isCorrect, that.isCorrect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alternativeTxt, question, isCorrect);
    }
}
