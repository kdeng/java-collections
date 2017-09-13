package nz.net.osnz.train.servlet

import javax.servlet.ServletConfig
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * @author Kefeng Deng (deng@51any.com) - Created on 11/03/15.
 */
@WebServlet(urlPatterns="/home")
class MyServlet extends HttpServlet {

	ServletConfig servletConfig

	@Override
	public void init(ServletConfig config) {
		this.servletConfig = config
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.writer.print("Hello world")
		response.writer.flush()
		response.writer.close()
	}

}
