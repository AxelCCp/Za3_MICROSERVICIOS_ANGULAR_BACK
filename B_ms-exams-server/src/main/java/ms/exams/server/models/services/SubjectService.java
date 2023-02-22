package ms.exams.server.models.services;

import org.springframework.stereotype.Service;

import ms.commons.service.rest.server.models.services.CommonService;
import ms.exams.commons.models.entity.Subject;
import ms.exams.server.models.dao.ISubjectDao;

@Service
public class SubjectService extends CommonService<Subject, ISubjectDao> implements ISubjectService{}
