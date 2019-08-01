package nz.net.osnz.demos.j2ee.filter

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class WebSocketFilter implements Filter {

  @Override
  void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    String uri = ((HttpServletRequest) request).getRequestURI();
    if (uri.startsWith("/ws/") || uri.startsWith("/websocket/")) {
      // Just let it go (assuming that files are in real not placed in a /spring folder!)
      chain.doFilter(request, response);
    } else {
      // Pass to Spring dispatcher servlet.
      request.getRequestDispatcher("/spring" + uri).forward(request, response);
    }
  }

  @Override
  void destroy() {

  }
}
