package ms.courses.server.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import ms.exams.commons.models.entity.Exam;
import ms.students.commons.models.entity.Student;

@Entity
@Table(name="courses")
public class Course implements Serializable{

	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	public Course() {
		this.students = new ArrayList<>();
		this.exams = new ArrayList<>();
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

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	
	//			-----------
	public void addStudents(Student student) {
		this.students.add(student);
	}
	
	public void removeStudents(Student student) {
		this.students.remove(student);
	}
	//			-----------


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String name;
	
	@Column(name="create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@OneToMany(fetch = FetchType.LAZY)  					//A COURSE TO VARIOUS STUDENTS.
	private List<Student>students;
	
	@ManyToMany												//A COURSE CAN HAVE VARIOUS EXAMS AND AN EXAM CAN BE IN DIFFERENT COURSES AT THE SAME TIME.			
	private List<Exam>exams;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 63484379352859172L;
}
