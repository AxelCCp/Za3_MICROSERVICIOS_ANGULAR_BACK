package ms.courses.server.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import ms.courses.server.models.entity.Course;

public interface ICourseDao extends PagingAndSortingRepository<Course, Long>{

	
	@Query("select c from Course c join fetch c.students s where s.id=?1")
	public Course findCourseByStudentId(Long id);
	
	
}
