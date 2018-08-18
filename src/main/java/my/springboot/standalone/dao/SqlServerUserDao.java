package my.springboot.standalone.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sop4j.dbutils.DbUtils;

import my.springboot.standalone.model.User;

public class SqlServerUserDao {
	  private final JdbcTemplate jdbcTemplate;

	  public SqlServerUserDao(JdbcTemplate jdbcTemplate) {
	    this.jdbcTemplate = jdbcTemplate;
	  }

	  public List<User> getLatestUserData(LocalDate fromDate) {
	    ResultSet rs = null;
	    List<User> users = new ArrayList<>();

	    try (CallableStatement stmt = Objects.requireNonNull(jdbcTemplate.getDataSource())
	        .getConnection().prepareCall(GET_LATEST_USER_DATA)) {

	      stmt.setString("FromDate", new SimpleDateFormat("MM/dd/yyyy").format(fromDate));

	      boolean results = stmt.execute();
	      while (results) {
	        rs = stmt.getResultSet();
	        while (rs.next()) {
	          User user = new User();
	          user.setUserId(rs.getInt(1));
	          user.setEmail(rs.getString(2));
	          user.setfName(rs.getString(3));
	          user.setlName(rs.getString(4));

	          users.add(user);
	        }
	        results = stmt.getMoreResults();
	      }
	    } catch (SQLException | ParseException e) {
	      System.out.println("Exception occurred in SqlServerUserDao.");
	      e.printStackTrace();
	    } finally {
	      DbUtils.closeQuietly(rs);
	    }
	    return users;
	  }
}
