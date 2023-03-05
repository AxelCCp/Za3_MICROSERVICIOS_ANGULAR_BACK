package ms.answers.server.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ms.answers.server.models.dao.IAnswerDao;
import ms.answers.server.models.entity.Answer;

@Service
public class AnswerService implements IAnswerService{

	@Override
	public Iterable<Answer> saveAll(Iterable<Answer> answers) {
		// TODO Auto-generated method stub
		return answerDao.saveAll(answers);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Answer> findAnswerByStudentByExam(Long studentId, Long examId) {
		// TODO Auto-generated method stub
		return answerDao.findAnswerByStudentByExam(studentId, examId);
	}

	@Override
	public Iterable<Long> findExamsIdsWithAnswersByStudent(Long studentId) {
		// TODO Auto-generated method stub
		return answerDao.findExamsIdsWithAnswersByStudent(studentId);
	}

	@Autowired
	private IAnswerDao answerDao;

	
}
