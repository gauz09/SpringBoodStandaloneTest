package my.springboot.standalone.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import my.springboot.standalone.model.User;

public class MySqlUserDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public MySqlUserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void insertUserData(User user) {
	    try {
	      jdbcTemplate.update(INSERT_USER, user.getUserId(),
	    		  user.getEmail(), user.getfName(),
	    		  user.getlName());
	    } catch (DataAccessException e) {
	      System.out.println("Exception while inserting user.");
	      e.printStackTrace();
	    }
	}	
}
