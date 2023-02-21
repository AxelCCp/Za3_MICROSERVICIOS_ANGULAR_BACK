package ms.exams.server.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ms.exams.commons.models.entity.Exam;

public interface IExamDao extends PagingAndSortingRepository<Exam, Long>{
	
	//SEARCH AN EXAM BY HIS NAME. IT WILL COMPLETE THE WORD TO RIGHT OR LEFT, FOR THE %?1%
	@Query("select e from Exam e where e.name like %?1%")
	public List<Exam>findByName(String text);
	
}
