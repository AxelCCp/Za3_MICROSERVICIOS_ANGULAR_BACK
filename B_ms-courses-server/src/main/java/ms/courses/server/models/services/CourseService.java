package ms.courses.server.models.services;

import org.springframework.stereotype.Service;

import ms.commons.service.rest.server.models.services.CommonService;
import ms.courses.server.models.dao.ICourseDao;
import ms.courses.server.models.entity.Course;

@Service
public class CourseService extends CommonService<Course, ICourseDao> implements ICourseService{

	@Override
	public Course findCourseByStudentId(Long id) {
		// TODO Auto-generated method stub
		return genericDao.findCourseByStudentId(id);
	}

}
