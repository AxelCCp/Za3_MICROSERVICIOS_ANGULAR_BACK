package ms.students.server.models.services;

import java.util.List;

import ms.commons.service.rest.server.models.services.ICommonService;
import ms.students.commons.models.entity.Student;

public interface IStudentService extends ICommonService<Student>{
	
	public List<Student>findByNameOrLastname(String text);
	
	
}
