package io.osnz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Service
@Slf4j
public class LogService {

  public void printMessage(Audit.Level level, String message, Object... args) {
    switch (level) {
      case INFO: info(message, args); break;
      case WARN: warn(message, args); break;
      case DEBUG: debug(message, args); break;
      case ERROR: error(message, args); break;
    }
  }

  public void info(String message, Object... args) {
    log.info(message, args);
  }

  public void debug(String message, Object... args) {
    log.debug(message, args);
  }

  public void warn(String message, Object... args) {
    log.warn(message, args);
  }

  public void error(String message, Object... args) {
    log.error(message, args);
  }

}
