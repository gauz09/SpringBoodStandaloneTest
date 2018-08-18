package my.springboot.standalone;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import my.springboot.standalone.model.User;
import my.springboot.standalone.service.MySqlDataFetchService;
import my.springboot.standalone.service.SqlServerDataFetchService;

@SpringBootApplication
public class SpringBootStandaloneTestApplication implements CommandLineRunner {

	@Autowired
	private SqlServerDataFetchService sqlServerDataFetchService;
	
	@Autowired
	private MySqlDataFetchService mySqlDataFetchService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStandaloneTestApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		List<User> users = sqlServerDataFetchService.getLatestUserData(LocalDate.now());
		for (User user : users) {
			try {
				mySqlDataFetchService.insertUserData(user);
			} catch (Exception e) {
				System.out.println("Error inserting User Data " + user.getUserId());
				e.printStackTrace();
			}
	    }
	}
}
