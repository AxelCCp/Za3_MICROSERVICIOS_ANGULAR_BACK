package ms.exams.server.models.dao;

import org.springframework.data.repository.CrudRepository;

import ms.exams.commons.models.entity.Subject;

public interface ISubjectDao extends CrudRepository<Subject, Long>{

}
