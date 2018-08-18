package my.springboot.standalone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.springboot.standalone.dao.MySqlUserDao;
import my.springboot.standalone.model.User;

@Service
public class MySqlDataFetchService {
	private final MySqlUserDao mySqlUserDao;

	@Autowired
	public MySqlDataFetchService(MySqlUserDao mySqlUserDao) {
	    this.mySqlUserDao = mySqlUserDao;
	}
	
	public void insertUserData(User user) {
		mySqlUserDao.insertUserData(user);
	}
}
