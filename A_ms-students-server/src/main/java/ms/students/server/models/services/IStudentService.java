package ms.students.server.models.services;

import java.util.List;

import ms.students.commons.models.entity.Student;

public interface IStudentService {
	
	public List<Student>findByNameOrLastname(String text);
	
	public List<Student>findAll();
	
	public Student findById(Long id);
	
	public Student save(Student student);
	
	public void deleteById(Long id);
}
