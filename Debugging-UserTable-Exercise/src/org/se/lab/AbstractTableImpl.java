package org.se.lab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractTableImpl
{
	/*
	 * Dependency: ---[1]-> connection:Connection
	 */
	private Connection connection;
	protected final Connection getConnection()
	{
		return connection;
	}
	public final void setConnection(final Connection connection)
	{
		if (connection == null)
			throw new NullPointerException("connection");
		this.connection = connection;
	}


	/*
	 * Helper methods
	 */

	protected void closeResultSet(ResultSet rs)
	{
		if (rs != null)
		{
			try
			{
				rs.close();
			} 
			catch (SQLException e)
			{
				throw new TableException("result set closing failure", e);
			}
		}
	}

	protected void closePreparedStatement(PreparedStatement pstmt)
	{
		if (pstmt != null)
		{
			try
			{
				pstmt.close();
			} 
			catch (SQLException e)
			{
				throw new TableException("prepared statement closing failure", e);
			}
		}
	}

	protected void closeStatement(Statement stmt) 
	{
		if (stmt != null)
		{
			try
			{
				stmt.close();
			} 
			catch (SQLException e)
			{
				throw new TableException("statement closing failure", e);
			}
		}
	}

}
