package nz.net.osnz.practice.servlet

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import javax.servlet.ServletConfig
import javax.servlet.ServletException
import javax.servlet.ServletOutputStream
import javax.servlet.annotation.WebInitParam
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 *
 * @author Kefeng Deng (deng@51any.com)
 */
@WebServlet(urlPatterns = "/hello", name = "AnnotationTest", initParams = [@WebInitParam(name = "fromAnnotation", value = "xyz")])
public class HelloServlet extends HttpServlet {

	String fromAnnotation

	ServletConfig config

	public void init(ServletConfig config) throws ServletException {
		super.init(config)
		this.config = config
		fromAnnotation = this.config.getInitParameter("fromAnnotation")
	}

	@PostConstruct
	private void myPostConstructMethod() {
		System.err.println("PostConstruct called")
	}


	@PreDestroy
	private void myPreDestroyMethod() {
		System.err.println("PreDestroy called")
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			ServletOutputStream out = response.getOutputStream();
			out.println("<html>");
			out.println("<body>");
			out.println("Hello " + this.fromAnnotation + "!");
			out.println("</body>");
			out.println("</html>");
			out.flush();
		}
		catch (Exception e) {
			throw new ServletException(e);
		}
	}

}

