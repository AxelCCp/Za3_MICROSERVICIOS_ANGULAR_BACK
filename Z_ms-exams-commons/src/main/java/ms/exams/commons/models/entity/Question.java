package ms.exams.commons.models.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="questions")
public class Question implements Serializable{
	
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

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	@Override
	public boolean equals(Object obj) {
		// THIS INSTANCE IS COMPARED WITH THE OBJ Q ARRIVED BY PARAMETER. WITH THE CLASS IT MAKES THE MAPPING IN THE DB AND IF IT FINDS IT, IT DELETES IT.
		if(this == obj) {
			return true;
		}		
		//IF OBJ IS NOT AN INSTANCE OF Question, RETURNS FALSE
		if(!(obj instanceof Question)) {
			return false;
		}		
		//IF OBJ IS AN INSTANCE OF Question,
		Question a = (Question) obj;
				
		//IF ID != NULL AND IF EQUALS THE ID OF "a". IF IT IS COMPLIED, IT WILL RETURN TRUE AND IF NOT, IT WILL RETURN FALSE. FOR EACH OBJ ON THE LIST TO GO SEARCH TO BE ABLE TO DELETE. AND WHEN IT FINDS IT, IT WILL DELETE IT.
		return this.id != null && this.id.equals(a.id);
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String text;
	
	@JsonIgnoreProperties(value="questions")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="exam_id")			//THE QUESTION CLASS IS THE OWNER OF THE RELATIONSHIP. SINCE IT HAS THE JOIN COLUMN WITH THE FOREIGN EXAMEN_ID.
	private Exam exam;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6565999321362347403L;
}
