package my.springboot.standalone.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.springboot.standalone.dao.SqlServerUserDao;
import my.springboot.standalone.model.User;

@Service
public class SqlServerDataFetchService {
	private final SqlServerUserDao sqlServeruserDao;

	  @Autowired
	  public SqlServerDataFetchService(SqlServerUserDao sqlServeruserDao) {
		  this.sqlServeruserDao = sqlServeruserDao;
	  }
	  
	  public List<User> getLatestUserData(LocalDate fromDate) {
		  return sqlServeruserDao.getLatestUserData(fromDate);
	  }
}
