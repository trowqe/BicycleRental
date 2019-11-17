package by.bokshic.bicycle.dao.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * Class that contains a connection to the database and wrapped it with methods 
 * for creation of statement.
 * 
 * 
 * @author khatkovskaya
 * 
 */
public class WrapperConnection {
	private Connection connection;
	private static Logger logger = LogManager.getLogger(WrapperConnection.class);
	
	public WrapperConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStatement() throws SQLException {
		if (connection != null) {
			Statement statement = connection.createStatement();
			if (statement != null) {
				return statement;
			}
		}
		throw new SQLException("Connection or statement is null");
	}

	public void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	}
	
	public void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	}
	
	public PreparedStatement getPreparedStatement(String query, Object ... params) throws SQLException {
		if (connection != null) {
			PreparedStatement statement = connection.prepareStatement(query);
			
			for (int i = 0; i < params.length; i++) {
				statement.setObject(i + 1, params[i]);
			}
			
			if (statement != null) {
				return statement;
			}
		}
		throw new SQLException("Connection or statement is null");
	}

}
