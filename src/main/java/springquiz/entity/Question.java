package springquiz.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
            cascade = CascadeType.ALL)
    private List<Alternative> alternativeList;

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

    public List<Alternative> getAlternativeList() {
        return alternativeList;
    }

    public void setAlternativeList(List<Alternative> alternativeList) {
        this.alternativeList = alternativeList;
    }

    public void addAlternative(Alternative alternative) {
        if (alternativeList == null)
            alternativeList = new ArrayList<>();

        alternativeList.add(alternative);
        alternative.setQuestion(this);
    }
}
