package ms.exams.server.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ms.commons.service.rest.server.controllers.CommonRestController;
import ms.exams.commons.models.entity.Exam;
import ms.exams.commons.models.entity.Question;
import ms.exams.server.models.services.IExamService;

@RestController
public class ExamController extends CommonRestController<Exam, IExamService>{
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?>update(@Valid @RequestBody Exam exam, BindingResult result, @PathVariable Long id ){
		if(result.hasErrors()) {
			return this.validation(result);
		}
		Optional<Exam> o = service.getById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Exam examDB = o.get();
		examDB.setName(exam.getName());
		
		//HERE YOU SEE WHAT QUESTIONS EXIST IN THE DATABASE, BUT THEY ARE NOT IN THE JSON THAT IS BEING SENT.
		//SURELY QUESTIONS WERE ADDED AND DELETED BEFORE, AND THIS CHANGE MUST BE REFLECTED.
		//IT IS ALSO TO DELETE THE QUESTIONS FROM THE DATABASE THAT ARE NOT IN THE JSON.
		
		//1.-CREATE ARRAY OF THE DELETED QUESTIONS. THE QUESTIONS OF THE DATABASE ARE ITERATED AND ASKED IF THIS QUESTION EXISTS IN THE JSON RECEIVED FROM @REQUESBODY. AND IF THE QUESTION DOES NOT EXIST IN THE JSON, IT IS DELETED FROM THE DATABASE.
		List<Question>deletedQuestions = new ArrayList<>();
		examDB.getQuestions().forEach(databaseQuestions -> {
			if(!exam.getQuestions().contains(databaseQuestions)) {     							//EL CONTAINS() : USE THE EQUALS() OF THE QUESTION CLASS.
				deletedQuestions.add(databaseQuestions);
			}
		});
		//2.-FOR EACH QUESTION TO BE DELETED, I DELETE IT.
		deletedQuestions.forEach(p -> {
			examDB.removeQuestion(p);
		});
		//3.-ONCE THE QUESTIONS THAT WERE NOT IN THE JSON ARE DELETED FROM THE DATABASE, NOW YOU HAVE TO ADD THE NEW QUESTIONS AND MODIFY THE EXISTING QUESTIONS. AND THE QUESTIONS THAT WERE NOT TOUCHED, REMAIN AS IS.
		examDB.setQuestions(exam.getQuestions());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examDB));
	}
	
	
	//SEARCH BY DE EXAM NAME
	@GetMapping("/filtrar/{texto}")
	public ResponseEntity<?>filtrate(@PathVariable String text){
		return ResponseEntity.ok(service.findByName(text));
	}

	
	//RETURN A SUBJECT LIST
	@GetMapping("/subjects")
	public ResponseEntity<?>subjectsList(){
		return ResponseEntity.ok(service.findAllSubjects());
	}
}
