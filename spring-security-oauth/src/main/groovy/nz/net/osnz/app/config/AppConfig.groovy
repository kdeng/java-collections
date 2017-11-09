package nz.net.osnz.app.config

import nz.net.osnz.common.jsp.EnableJSPView
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Configuration
@EnableJSPView
@PropertySource(value = 'classpath:application.properties')
class AppConfig {


}
