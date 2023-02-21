package ms.commons.service.rest.server.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

//QUITO EL @SERVICE PQ SE VA A HEREDAR, NO A INYECTAR.
public class CommonService <E, R extends PagingAndSortingRepository<E,Long>> implements ICommonService<E>{

	@Override
	@Transactional(readOnly=true)
	public Iterable<E> findAll() {
		return genericDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<E> getById(Long Id) {
		return genericDao.findById(Id);
	}

	@Override
	@Transactional(readOnly=false)
	public E save(E entity) {
		return genericDao.save(entity);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteById(Long id) {
		genericDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<E> findAll(Pageable pageable) {
		return genericDao.findAll(pageable);
	}
	
	
	@Autowired
	protected R genericDao;

}
