package mainapp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.UserDAO;
import entities.User;

public class Demo {
	public static void main(String[] args) throws SQLException, Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");
		List<User> listUser = new ArrayList<User> ();
		listUser.add(new User(1, "Nicolas Cage", 100000));
		listUser.add(new User(2, "Abramovic", 200000));
		listUser.add(new User(1, "Thor", 300000));

		userDAO.insert(listUser);
//		userDAO.insertWithoutTransaction(listUser);

		userDAO.transfer(1, 2, 100000);
		
		((ClassPathXmlApplicationContext) ctx).close();
		System.out.println("Done!");
	}
}