package ms.exams.server.models.services;

import java.util.List;

import ms.commons.service.rest.server.models.services.ICommonService;
import ms.exams.commons.models.entity.Exam;
import ms.exams.commons.models.entity.Subject;

public interface IExamService extends ICommonService<Exam>{
	
	public List<Exam>findByName(String text);
	
	public Iterable<Subject>findAllSubjects();
	
}
