package io.osnz

import groovy.transform.CompileStatic
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct
import javax.sql.DataSource

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@CompileStatic
@Service
class ConnService {
  static final Logger LOG = LoggerFactory.getLogger(ConnService)
  final DataSource dataSource;

  ConnService(DataSource dataSource) {
    this.dataSource = dataSource
  }


  @PostConstruct
  void init() {
    LOG.info(dataSource.connection.toString())
  }

}
