package ms.answers.server.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ms.exams.commons.models.entity.Question;
import ms.students.commons.models.entity.Student;

@Entity
@Table(name="answers")
public class Answer  implements Serializable{

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String text;
	
	@ManyToOne(fetch=FetchType.LAZY)                      //A STUDENT CAN HAVE MANY ANSWERS. WHILE THE ANSWERS ARE FROM A SINGLE STUDENT IN PARTICULAR.
	private Student student;
	
	@OneToOne(fetch = FetchType.LAZY)					  //THE RELATION IS 1 TO 1 BETWEEN QUESTION AND ANSWER.
	private Question question;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4025650857751187443L;
}
