package nz.net.osnz

import bathe.BatheBooter
import org.junit.Test

/**
 *
 * @author Kefeng Deng (deng@51any.com)
 */
class LocalRunner {

	@Test
	public void runWebApplication() {
		new BatheBooter().runWithLoader(null, null, "nz.ac.auckland.war.WebAppRunner",
				[
						"-Pclasspath:/war.properties"
//						,
//						"-P${System.getProperty('user.home')}/.webdev/sample.properties"
				] as String[])
	}

}
