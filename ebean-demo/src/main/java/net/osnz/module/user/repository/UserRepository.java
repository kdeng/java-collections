package net.osnz.module.user.repository;


import io.ebean.EbeanServer;
import io.ebean.EbeanServerFactory;
import io.ebean.config.ServerConfig;
import net.osnz.module.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@Repository
@SuppressWarnings("unused")
public final class UserRepository {

	private final static Logger log = LoggerFactory.getLogger(UserRepository.class);

//  private final static String QUERY_GET = "SELECT * FROM User WHERE id=?";
//
//  private final static String QUERY_FINDALL = "SELECT * FROM User";

	@Autowired
	private DataSource dataSource;

	private EbeanServer ebeanServer;

	@PostConstruct
	public void postConstruct() {
		try {
			ServerConfig serverConfig = new ServerConfig();
			serverConfig.setName("osnz");

			//dataSource.getConnection().setAutoCommit(false);

			serverConfig.setDataSource(dataSource);

			serverConfig.setDefaultServer(true);
			serverConfig.setRegister(true);

			serverConfig.setDdlGenerate(false);
			serverConfig.setDdlRun(false);
//      serverConfig.setLoggingLevel(LogLevel.valueOf("DEBUG"));
//      serverConfig.setDebugSql(true);
//      serverConfig.setLoggingToJavaLogger(true);
			serverConfig.addPackage("net.osnz.module.user.domain");

//      serverConfig.addClass(User.class);

			this.ebeanServer = EbeanServerFactory.create(serverConfig);

			log.debug("User Repository Bean is ready");
		} catch (Exception ex) {
			log.error("Failed to create ebeanTemplate", ex);
		}
	}

	public final User get(Long id) {
		return ebeanServer.find(User.class, id);
	}

	public List<User> findAll() {
		return ebeanServer.find(User.class).findList();
	}

	public Long save(User user) {
		Long userId = null;
		try {
			ebeanServer.save(user);
			userId = user.getId();
		} catch (Exception ex) {
			log.error("Failed to save user : " + user.getMobile(), ex);
		}
		return userId;
	}

}
