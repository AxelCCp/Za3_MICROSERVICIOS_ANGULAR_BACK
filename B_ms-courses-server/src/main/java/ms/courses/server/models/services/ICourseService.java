package ms.courses.server.models.services;

import ms.commons.service.rest.server.models.services.ICommonService;
import ms.courses.server.models.entity.Course;

public interface ICourseService extends ICommonService<Course>{
	public Course findCourseByStudentId(Long id);
}
