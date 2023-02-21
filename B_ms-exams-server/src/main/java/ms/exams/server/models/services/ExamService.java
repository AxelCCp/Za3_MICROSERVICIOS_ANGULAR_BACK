package ms.exams.server.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ms.commons.service.rest.server.models.services.CommonService;
import ms.exams.commons.models.entity.Exam;
import ms.exams.commons.models.entity.Subject;
import ms.exams.server.models.dao.IExamDao;
import ms.exams.server.models.dao.ISubjectDao;

@Service
public class ExamService extends CommonService<Exam, IExamDao> implements IExamService{

	@Override
	@Transactional(readOnly = true)
	public List<Exam> findByName(String text) {
		return genericDao.findByName(text);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Subject> findAllSubjects() {
		return subjectDao.findAll();
	}
	
	@Autowired
	private ISubjectDao subjectDao;

}
