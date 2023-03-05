package ms.answers.server.models.services;

import ms.answers.server.models.entity.Answer;

public interface IAnswerService {
	
	public Iterable<Answer>saveAll(Iterable<Answer> answers); 
	
	public Iterable<Answer>findAnswerByStudentByExam(Long studentId, Long examId);
	
	public Iterable<Long>findExamsIdsWithAnswersByStudent(Long studentId);

}
