package ms.exams.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ms.commons.service.rest.server.controllers.CommonRestController;
import ms.exams.commons.models.entity.Subject;
import ms.exams.server.models.services.ISubjectService;

@RestController
@RequestMapping("/sub")
public class SubjectController extends CommonRestController<Subject, ISubjectService>{
	
	//DISPONIBLE SOLO EL FIND ALL POR AHORA
	
}
