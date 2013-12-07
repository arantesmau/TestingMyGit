package org.se.lab;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserTableImpl 
	extends AbstractTableImpl
	implements UserTable
{
	public void insert(final User user)
	{
		if (user == null)
			throw new IllegalArgumentException();

		final String SQL = "INSERT INTO user VALUES (NULL,?,?,?,?)";
		PreparedStatement pstmt = null;

		try
		{
			pstmt = getConnection().prepareStatement(SQL);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getPassword());
			pstmt.executeUpdate();
		} 
		catch (SQLException e)
		{
			throw new TableException("insert failure", e);
		} 
		finally
		{
			closePreparedStatement(pstmt);
		}
	}

	public void update(final User user)
	{
		if (user == null)
			throw new IllegalArgumentException();

		final String SQL = "UPDATE user SET firstname=?, lastname=?, "
				+ "username=?, password=? WHERE id=?";
		PreparedStatement pstmt = null;

		try
		{
			pstmt = getConnection().prepareStatement(SQL);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getPassword());
			pstmt.setLong(5, user.getId());
			pstmt.executeUpdate();
		} 
		catch (SQLException e)
		{
			throw new TableException("update failure", e);
		} 
		finally
		{
			closePreparedStatement(pstmt);
		}
	}

	
	public void delete(final long id) 
	{
		final String SQL = "DELETE FROM user WHERE ID = ?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = getConnection().prepareStatement(SQL);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} 
		catch (SQLException e)
		{
			throw new TableException("update failure", e);
		} 
		finally
		{
			closePreparedStatement(pstmt);
		}
	}


	public User findById(final long id) 
	{
		final String SQL = "SELECT * FROM users WHERE id=? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try
		{
			pstmt = getConnection().prepareStatement(SQL);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			user = new User(rs.getLong("id"), rs.getString("firstName"),
					rs.getString("lastName"), rs.getString("username"),
					rs.getString("password"));
		} 
		catch (SQLException e)
		{			
			throw new TableException("update failure");
		} 
		finally
		{
			closeResultSet(rs);
			closePreparedStatement(pstmt);
		}
		return user;
	}


	public List<User> findAll()
	{
		final String SQL = "SELECT * FROM user";
		Statement stmt = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();

		try
		{
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery(SQL);

			while (rs.next())
			{
				long id = rs.getLong("id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String username = rs.getString("username");
				String password = rs.getString("password");
				User user = new User(id, firstName, lastName, username,password);
				users.add(user);
			}
		} 
		catch (SQLException e)
		{
			throw new TableException("update failure", e);
		} 
		finally
		{
			closeResultSet(rs);
			closeStatement(stmt);
		}
		return users;
	}
}
