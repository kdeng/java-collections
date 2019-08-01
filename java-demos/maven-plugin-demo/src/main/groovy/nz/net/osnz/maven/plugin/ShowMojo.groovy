package nz.net.osnz.maven.plugin

import org.apache.maven.plugin.AbstractMojo
import org.apache.maven.plugin.MojoExecutionException
import org.apache.maven.plugin.MojoFailureException
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.Parameter

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Mojo(name='hello')
public class ShowMojo extends AbstractMojo {

	@Parameter(defaultValue = 'Hello')
	private String greetingMessage

	@Parameter(defaultValue = 'World', property = 'name')
	private String name

	@Parameter(defaultValue = 'default message', property = 'message')
	private String _message


	void setMessage(String comingMessage) {
		this._message = comingMessage;
	}

	@Override
	void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("==== ${greetingMessage} ${name} -> ${_message}")
	}

}
