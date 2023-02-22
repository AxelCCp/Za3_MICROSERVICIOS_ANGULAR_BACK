package ms.students.commons.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="students")
public class Student implements Serializable{
	
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
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj) {
			return true;
		}
		
		if(!(obj instanceof Student)) {
			return false;
		}
			
		Student a = (Student) obj;
			
		return this.id != null && this.id.equals(a.id);
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String lastname;
	
	private Integer age;
	
	private String email;
	
	@Column(name="create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="country_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Country country;
	
															//A BINARY CONTENT IS SAVED IN PHOTO. 
	@Lob													//LOB ALLOWS A PERSISTENT PROPERTY OR FIELD, MUST BE PRESERVED AS A LARGE OBJ FOR A LARGE OBJ TYPE, AND Q IS COMPATIBLE WITH THE DB.
	@JsonIgnore												//IT IS IGNORED IN THE JSON, BECAUSE THE CONTENT IS VERY LARGE.
	private byte[]photo;

	
	private static final long serialVersionUID = 7705845283783940467L;
}
