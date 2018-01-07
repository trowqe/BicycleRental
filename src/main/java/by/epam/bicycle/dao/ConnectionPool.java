package by.epam.bicycle.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.bicycle.config.ConfigurationManager;


/**
 * 
 * Singleton that contains connections to the database.
 *   
 * @author khatkovskaya
 * 
 */
public class ConnectionPool {
	
	/**
	 * Single instance of pool.
	 */
	private static ConnectionPool instance;
	
	/**
	 * Queue of created connections to the database.
	 */
	private final Queue<Connection> connections = new LinkedList<Connection>();
	
	/**
	 * Max count of connections in pool.
	 */
	private final int MAX_POOL_SIZE;
	
	private static AtomicBoolean isInitialized = new AtomicBoolean();
	private final Semaphore semaphore;
	
	private final static Lock lock = new ReentrantLock();
	private static final String DB_USER_CONFIG = "db.user";
	private static final String DB_PASSWORD_CONFIG = "db.password";
	private static final String DB_URL_CONFIG = "db.url";
	
	private static Logger logger = LogManager.getLogger(ConnectionPool.class);
	
	/**
	 * Sets the max size of pool from configuration file and initialize the connection pool.
	 */
	private ConnectionPool() {
		MAX_POOL_SIZE = Integer.parseInt(ConfigurationManager.getProperty("db.max_pool_size"));

		try {
			initializeConnectionPool();
		} catch (SQLException e) {
			logger.error(e);
		}
		semaphore = new Semaphore(connections.size(), true);
	}

	/**
	 * Returns single instance of this class.
	 */
	public static ConnectionPool getInstance() {
		if (!isInitialized.get()) {
			lock.lock();
			try {
				instance = new ConnectionPool();
				isInitialized.compareAndSet(false, true);
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}
	
	/**
	 * Creates connections to the database.
	 * 
	 * @throws SQLException if goes something wrong in createConnection method
	 */
	private void initializeConnectionPool() throws SQLException {
		while (!isConnectionPoolFull()) {
			connections.add(createConnection());
		}
	}

	/**
	 * Checks that connection pool is not full.
	 * 
	 * @return <tt>true</tt> if count of connections less that configuration max pool size.
	 */
	private boolean isConnectionPoolFull() {
		if (connections.size() < MAX_POOL_SIZE) {
			return false;
		}
		return true;
	}

	/**
	 * Create connection to the database which configurations in config.properies file.
	 * 
	 * @return created connection
	 * @throws SQLConnection when we cannot create connection to the database.
	 */
	private Connection createConnection() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		final String user = ConfigurationManager.getProperty(DB_USER_CONFIG);
		final String password = ConfigurationManager.getProperty(DB_PASSWORD_CONFIG);
		final String url = ConfigurationManager.getProperty(DB_URL_CONFIG);
		Connection connection = (Connection) DriverManager.getConnection(url, user, password);
		return connection;
	}

	public Connection getConnection() {
		Connection connection = null;

		if (semaphore.tryAcquire()) {
			if (connections.size() > 0) {
				connection = (Connection) connections.poll();
			}
		}

		return connection;
	}
	
	public void returnConnectionToPool(Connection connection) {
		connections.add(connection);
		semaphore.release();
	}

	protected void finilize() throws Throwable {
		try {
			Iterator<Connection> iterator = connections.iterator();
			while (iterator.hasNext()) {
				Connection connection = iterator.next();
				if (connection != null) {
					connection.close();
				}
				iterator.remove();
			}
		} catch (SQLException ex) {
			throw ex;
		} finally {
			super.finalize();
		}
	}
}
