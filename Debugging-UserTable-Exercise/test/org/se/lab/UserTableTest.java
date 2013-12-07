package org.se.lab;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UserTableTest 
	extends AbstractJdbcTest
{	
	private UserTable table;
	
	@Before
	public void setup() throws ClassNotFoundException, SQLException   
	{
		table = new UserTableImpl();
		((UserTableImpl)table).setConnection(getConnection());	
	}
	

	
	@Test
	public void testInsert()
	{
		table.insert(new User("Marge", "Simpson", "marge", "**********"));		
	}
	
	@Test
	public void testFindById()
	{
		User user = table.findById(2);
		
		System.out.println(user);
	}
	
	@Test
	public void testFindAll() 
	{
		List<User> users = table.findAll();
		
		System.out.println(users);
	}
	

	@Test
	public void testUpdate()
	{
		User bart = new User(2,"Bart", "Simpson", "bart", "xxxxxxxxxxxx");
		table.update(bart);
	}
	
	
	@Test
	public void testDelete()
	{
		table.delete(1);
	}
}
