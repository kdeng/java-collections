package nz.net.osnz.practice.stencil

import nz.ac.auckland.stencil.Path
import nz.net.osnz.lmz.stencil.NamedStencil

import javax.servlet.http.HttpServletRequest

/**
 *
 * @author Kefeng Deng (deng@51any.com)
 */
@Path("/stencil/{name}")
class HomeStencil extends NamedStencil {
	@Override
	Map<String, Object> render(HttpServletRequest request, Map<String, String> pathParameters) {
		return [name: pathParameters.get('name')]
	}
}
