package ms.exams.commons.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="subjects")
public class Subject implements Serializable{
	
	public Subject() {
		this.sons = new ArrayList<>();
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

	public Subject getParent() {
		return parent;
	}

	public void setParent(Subject parent) {
		this.parent = parent;
	}

	public List<Subject> getSons() {
		return sons;
	}

	public void setSons(List<Subject> sons) {
		this.sons = sons;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@JsonIgnoreProperties(value = {"sons"})													
	@ManyToOne(fetch = FetchType.LAZY)		//ESTE ES UN SELF JOIN EN SQL : UNA ASIGNATURA PUEDE TENER VARIAS ASIGNATURAS HIJAS.														
	private Subject parent;
	
	@JsonIgnoreProperties(value = {"parent"}, allowSetters = true)								
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL)
	private List<Subject>sons;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2568830169460048162L;
}
