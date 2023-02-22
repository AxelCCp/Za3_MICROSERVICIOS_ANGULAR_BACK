package ms.exams.commons.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="exams")
public class Exam implements Serializable{

	public Exam() {
		this.questions = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	// SET MODIFICATION : YOU HAVE TO MODIFY THE SET TO INCLUDE THE INVERSE RELATIONSHIP. SO THAT FOR EACH QUESTION ASSIGN THE EXAM. ... WHEN ONE CREATES AN EXAM WITH ITS QUESTIONS WITH THE POST, "EXAM -> TO -> QUESTIONS" IS ASSIGNED AND MISSING ASSIGN "THE QUESTION -> AL -> EXAM" .... WITHOUT THIS THE FOREIGN KEY OF THE EXAM_ID WOULD BE NULL .
	public void setQuestions(List<Question> questions) {
		this.questions.clear();
		questions.forEach(q -> {
			this.addQuestion(q);
		});
	}

	public Subject getSubjects() {
		return subjects;
	}

	public void setSubjects(Subject subjects) {
		this.subjects = subjects;
	}

	public Boolean getAnswered() {
		return answered;
	}

	public void setAnswered(Boolean answered) {
		this.answered = answered;
	}
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	//METHOD TO ADD QUESTIONS TO THE EXAM
	public void addQuestion(Question question) {
		this.questions.add(question);
		question.setExam(this);	// THE EXAM IS ASSIGNED TO THE QUESTION.
	}
		
	//METHOD TO REMOVE QUESTIONS FROM THE EXAM.
	public void removeQuestion(Question question) {
		this.questions.remove(question);
		question.setExam(null);	// THE EXAM IS REMOVED FROM THE QUESTION WITH A NULL.
	}
	
	@Override
	public boolean equals(Object obj) {
		//THIS INSTANCE IS COMPARED WITH THE OBJ THAT ARRIVES BY PARAMETER. WITH THE CLASS IT MAKES THE MAPPING IN THE DB AND IF IT FINDS IT, IT DELETES IT.
		if(this == obj) {
			return true;
		}	
		//IF OBJ IS NOT AN INSTANCE OF Exam, RETURNS FALSE.
		if(!(obj instanceof Exam)) {
			return false;
		}		
		//IF OBJECT IS INSTANCE OF EXAM
		Exam a = (Exam) obj;
			
		//IF ID != NULL AND IF EQUALS THE ID OF "a". IF IT IS COMPLIED, IT WILL RETURN TRUE AND IF NOT, IT WILL RETURN FALSE. FOR EACH OBJ ON THE LIST TO GO SEARCH TO BE ABLE TO DELETE. AND WHEN IT FINDS IT, IT WILL DELETE IT.
		return this.id != null && this.id.equals(a.id);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Size(min=3, max=100)
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name="create_at")
	private Date createAt;
	
	@JsonIgnoreProperties(value="exam", allowSetters = true)
	@OneToMany(mappedBy="exam", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)		//WHEN AN EXAM IS DELETED, ITS QUESTIONS HAVE TO BE DELETED. WHEN AN EXAM IS CREATED, ITS QUESTIONS ARE ALSO CREATED. THE IDEA IS TO CREATE EVERYTHING TOGETHER, THE EXAM AND THE QUESTIONS. --- orphanRemoval : ANY QUESTION THAT IS NOT ASSIGNED TO AN EXAM WILL BE DELETED.  ---  mappedBy="exam" : TO ESTABLISH THE BI-DIRECTIONAL RELATIONSHIP.
	private List<Question>questions;
	
	//@NotNull
	@ManyToOne(fetch = FetchType.LAZY) 			//MANY EXAMS MAY BE ASSOCIATED WITH A SINGLE SUBJECT. THE RELATION IS UNIDIRECTIONAL, YOU WANT TO KNOW WHAT SUBJECT IS AN EXAM, BUT YOU DO NOT WANT TO KNOW THE EXAMS OF A SUBJECT.
	private Subject subjects;
	
	@Transient
	private Boolean answered;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3531829377465042864L;
}
