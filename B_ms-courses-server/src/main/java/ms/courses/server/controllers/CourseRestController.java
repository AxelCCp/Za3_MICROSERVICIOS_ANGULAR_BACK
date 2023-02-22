package ms.courses.server.controllers;

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
import ms.courses.server.models.entity.Course;
import ms.courses.server.models.services.ICourseService;
import ms.exams.commons.models.entity.Exam;
import ms.students.commons.models.entity.Student;

@RestController
public class CourseRestController extends CommonRestController<Course, ICourseService>{

	@PutMapping("/{id}")
	public ResponseEntity<?>editar(@Valid @RequestBody Course course, BindingResult result, @PathVariable Long id){
		if(result.hasErrors()) {
			return this.validation(result);
		}
		Optional<Course>o = this.service.getById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Course courseDB = o.get();
		courseDB.setName(course.getName());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(courseDB));
	}
	
	
	@PutMapping("/{id}/add-students")
	public ResponseEntity<?>addStudents(@RequestBody List<Student>students, @PathVariable Long id){
		Optional<Course>o = this.service.getById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Course courseDB = o.get();
		students.forEach(s -> {
			courseDB.addStudents(s);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(courseDB));
	}
	
	
	@PutMapping("/{id}/remove-student")
	public ResponseEntity<?>removeStudent(@RequestBody Student student, @PathVariable Long id){
		Optional<Course>o = this.service.getById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Course courseDB = o.get();
		courseDB.removeStudents(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(courseDB));
	}
	
	
	@GetMapping("/course-by-st-id/{id}")
	public ResponseEntity<?>getCourseByTheStudentId(@PathVariable Long id){
		Course course = service.findCourseByStudentId(id);
		return ResponseEntity.ok(course);
	}
	
	
	@PutMapping("/{id}/assign-exams")
	public ResponseEntity<?>assignExamToCourse(@RequestBody List<Exam>exams, @PathVariable Long id){
		Optional<Course>o = this.service.getById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Course courseDB = o.get();
		exams.forEach(a -> {
			courseDB.addExam(a);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(courseDB));
	}
	
	
	@PutMapping("/{id}/delete-exam")
	public ResponseEntity<?>deleteExam(@RequestBody Exam exam, @PathVariable Long id){
		Optional<Course>o = this.service.getById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Course courseDB = o.get();
		courseDB.removeExam(exam);
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(courseDB));
	}
	
	
	
}
