package ms.exams.server.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ms.exams.commons.models.entity.Subject;

public interface ISubjectDao extends PagingAndSortingRepository<Subject, Long>{

}
