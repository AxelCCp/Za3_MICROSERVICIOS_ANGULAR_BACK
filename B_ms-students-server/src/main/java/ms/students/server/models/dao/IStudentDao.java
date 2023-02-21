package ms.students.server.models.dao;


import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import ms.students.commons.models.entity.Student;

public interface IStudentDao extends PagingAndSortingRepository<Student, Long>{
	
	
	@Query("select s from Student s where s.name like %?1% or s.lastname like %?1%")
	public List<Student>findByNameOrLastname(String text);
	
	

}
