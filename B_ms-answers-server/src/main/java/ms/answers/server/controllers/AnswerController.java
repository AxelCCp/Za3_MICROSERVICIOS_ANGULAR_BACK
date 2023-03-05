package ms.answers.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ms.answers.server.models.entity.Answer;
import ms.answers.server.models.services.IAnswerService;

@RestController
public class AnswerController {

	@PostMapping
	public ResponseEntity<?>create(@RequestBody Iterable<Answer> answers) {
		Iterable<Answer>answersDB = answerService.saveAll(answers);
		return ResponseEntity.status(HttpStatus.CREATED).body(answersDB); 
	}
	
	@GetMapping("/student/{studentId}/exam/{examId}")
	public ResponseEntity<?>findAnswerByStudentByExam(@PathVariable Long studentId, @PathVariable Long examId){
		Iterable<Answer>answers = answerService.findAnswerByStudentByExam(studentId, examId);
		return ResponseEntity.ok(answers);
	}
	
	@GetMapping("/student/{studentId}/answered-exams")
	public ResponseEntity<?>findExamsIdsWithAnswersByStudent(@PathVariable Long studentId){
		Iterable<Long>examsIds = answerService.findExamsIdsWithAnswersByStudent(studentId);
		return ResponseEntity.ok(examsIds);
		
	}
	
	@Autowired
	private IAnswerService answerService;
	
}
