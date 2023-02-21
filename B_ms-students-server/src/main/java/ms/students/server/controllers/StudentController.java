package ms.students.server.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ms.commons.service.rest.server.controllers.CommonRestController;
import ms.students.commons.models.entity.Student;
import ms.students.server.models.services.IStudentService;

@RestController
@RequestMapping("/st")
public class StudentController extends CommonRestController<Student, IStudentService>{
	
	@GetMapping("/findbyname/{text}")
	public ResponseEntity<?>findByNameOrLastname(@PathVariable String text) {
		return ResponseEntity.ok(service.findByNameOrLastname(text));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?>update(@RequestBody Student student, @PathVariable Long id){
		
		Optional <Student> optionalStudent = service.getById(id);
		if(optionalStudent.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Student studentDB = optionalStudent.get();
		studentDB.setName(student.getName());
		studentDB.setLastname(student.getLastname());
		studentDB.setAge(student.getAge());
		studentDB.setEmail(student.getEmail());
		studentDB.setCountry(student.getCountry());
		studentDB.setCreateAt(student.getCreateAt());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(studentDB));
	}
	
}
