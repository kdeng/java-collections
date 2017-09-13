package net.osnz.module.user.repository;


import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;
import net.osnz.module.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
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

	@Inject
	private DataSource dataSource;

	private EbeanServer ebeanServer;

	@PostConstruct
	public void postConstruct() {
		try {
			ServerConfig serverConfig = new ServerConfig();
			serverConfig.setName("osnz");
			dataSource.getConnection().setAutoCommit(false);

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
		return Ebean.find(User.class, id);
	}

	public List<User> findAll() {
		return Ebean.find(User.class).findList();
	}

	public Long save(User user) {
		Long userId = null;
		try {
			Ebean.save(Arrays.asList(new User[]{user}));
			userId = user.getId();
		} catch (Exception ex) {
			log.error("Failed to save user : " + user.getMobile(), ex);
		}
		return userId;
	}

}
