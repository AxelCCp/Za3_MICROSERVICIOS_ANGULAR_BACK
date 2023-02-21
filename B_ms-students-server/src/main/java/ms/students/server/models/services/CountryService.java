package ms.students.server.models.services;

import org.springframework.stereotype.Service;
import ms.commons.service.rest.server.models.services.CommonService;
import ms.students.commons.models.entity.Country;
import ms.students.server.models.dao.ICountryDao;

@Service
public class CountryService extends CommonService<Country, ICountryDao> implements ICountryService{
	

}
