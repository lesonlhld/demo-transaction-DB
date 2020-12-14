package stackjava.com.springjdbc.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import stackjava.com.springjdbc.entities.User;

public class UserDAO {
	
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public void insert(List<User> listUser) {
		String sql = "INSERT INTO user_info (id, name, address) VALUES (?, ?, ?);";
		for (User user: listUser) {
			jdbcTemplate.update(sql, user.getId(), user.getName(), user.getAddress());
			System.out.println("Inserted user: " + user.getId() +" - " +user.getName());
		}
	}

	public void insertWithoutTransaction(List<User> listUser) {
		String sql = "INSERT INTO user_info (id, name, address) VALUES (?, ?, ?);";
		for (User user: listUser) {
			jdbcTemplate.update(sql, user.getId(), user.getName(), user.getAddress());
			System.out.println("Inserted user: " + user.getId() +" - " +user.getName());
		}
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
