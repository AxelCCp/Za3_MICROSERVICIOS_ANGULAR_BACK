package ms.students.server.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	
	@PostMapping("/create-with-photo")
	public ResponseEntity<?>createWithPhoto(@Valid Student student, BindingResult result, @RequestParam MultipartFile file) throws IOException {
		if(!file.isEmpty()) {
			student.setPhoto(file.getBytes());
		}
		return super.create(student, result);
	}
	
	
	@PutMapping("/update-with-photo/{id}")
	public ResponseEntity<?>updateWithPhoto(@Valid Student student, BindingResult result, @PathVariable Long id, @RequestParam MultipartFile file) throws IOException{
		if(result.hasErrors()) {
			return this.validation(result);
		}
		Optional<Student> o = service.getById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();	
		}
		Student studentDB = o.get();
		studentDB.setName(student.getName());
		studentDB.setLastname(student.getLastname());
		studentDB.setEmail(student.getEmail());
		if(!file.isEmpty()) {
			studentDB.setPhoto(file.getBytes());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(studentDB));
	}
	
	
	@GetMapping("upload-photo/{id}")
	public ResponseEntity<?>uploadPhoto(@PathVariable Long id){
		Optional<Student> o = service.getById(id);
		if(o.isEmpty() || o.get().getPhoto() == null) {
			return ResponseEntity.notFound().build();
		}
		Resource photo = new ByteArrayResource(o.get().getPhoto());
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(photo);
	}
	
}
