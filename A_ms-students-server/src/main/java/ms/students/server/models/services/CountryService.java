package ms.students.server.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ms.students.commons.models.entity.Country;
import ms.students.server.models.dao.ICountryDao;

@Service
public class CountryService implements ICountryService{

	@Override
	public List<Country> findAll() {
		// TODO Auto-generated method stub
		return (List<Country>) countryDao.findAll();
	}

	@Override
	public Country findById(Long id) {
		// TODO Auto-generated method stub
		return countryDao.findById(id).orElse(null);
	}

	@Override
	public Country save(Country country) {
		// TODO Auto-generated method stub
		return countryDao.save(country);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		countryDao.deleteById(id);
	}
	
	@Autowired
	private ICountryDao countryDao;

}
