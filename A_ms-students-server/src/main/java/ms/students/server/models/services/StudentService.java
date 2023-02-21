package ms.students.server.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ms.students.commons.models.entity.Student;
import ms.students.server.models.dao.IStudentDao;

@Service
public class StudentService implements IStudentService{

	
	@Override
	public List<Student> findByNameOrLastname(String text) {
		return iStudentRepository.findByNameOrLastname(text);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return (List<Student>) iStudentRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Student findById(Long id) {
		// TODO Auto-generated method stub
		return iStudentRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Student save(Student student) {
		// TODO Auto-generated method stub
		return iStudentRepository.save(student);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		iStudentRepository.deleteById(id);
	}
	
	@Autowired
	private IStudentDao iStudentRepository;


}
