package dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import entities.User;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

public class UserDAO {
	
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public void insert(List<User> listUser) {
		String sql = "INSERT INTO user_info (id, name, amount) VALUES (?, ?, ?);";
		for (User user: listUser) {
			jdbcTemplate.update(sql, user.getId(), user.getName(), user.getAmount());
			System.out.println("Inserted user: " + user.getId() +" - " +user.getName());
		}
	}
	
	public void insertWithoutTransaction(List<User> listUser) {
		String sql = "INSERT INTO user_info (id, name, amount) VALUES (?, ?, ?);";
		for (User user: listUser) {
			jdbcTemplate.update(sql, user.getId(), user.getName(), user.getAmount());
			System.out.println("Inserted user: " + user.getId() +" - " +user.getName());
		}
	}
	
    public User findByCustomerId(int id) {
        String sql = "SELECT * FROM user_info WHERE id = ?";
        return (User) jdbcTemplate.queryForObject(
            sql, 
            new Object[]{id}, 
            new BeanPropertyRowMapper<User>(User.class));
    }
    
    public void updateAmount(int id, int amount) {
        String sql = String.format("UPDATE user_info SET amount=%d WHERE id=%d", amount, id);
 
        jdbcTemplate.execute(sql);
    }

    @Transactional(rollbackFor = Exception.class)
    public void transfer(int sourceId, int destId, int amount) throws Exception{
        User sourceUser = findByCustomerId(sourceId);
        User destUser = findByCustomerId(destId);
        int sourceAmount = sourceUser.getAmount();
        int destAmount = destUser.getAmount();
 
        // Update source account
        int subtract = sourceAmount - amount;
        updateAmount(sourceId, subtract);
        if (subtract <= 20000) {
            throw new Exception("Not allow send money if current amount is less than 20$");
        }
        
        // Update dest account
        updateAmount(destId, destAmount + amount);
		System.out.println("Transfer successfully from: " + sourceUser.getName() + "(Id: " + sourceUser.getId() +") to: " + destUser.getName() + "(Id: " + destUser.getId() + ")");
    }

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
