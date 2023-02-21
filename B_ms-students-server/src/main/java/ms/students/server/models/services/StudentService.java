package ms.students.server.models.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ms.commons.service.rest.server.models.services.CommonService;
import ms.students.commons.models.entity.Student;
import ms.students.server.models.dao.IStudentDao;

@Service
public class StudentService extends CommonService<Student, IStudentDao> implements IStudentService{

	
	@Override
	@Transactional(readOnly = true)
	public List<Student> findByNameOrLastname(String text) {
		return genericDao.findByNameOrLastname(text);
	}
	

}
