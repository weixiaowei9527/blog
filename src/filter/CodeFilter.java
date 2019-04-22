package filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.management.RuntimeErrorException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * Servlet Filter implementation class RequestFilter
 */
@WebFilter(filterName="CodeFilter",urlPatterns="/*")
public class CodeFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CodeFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*CharaterRequest charaterRequest = new CharaterRequest(request);
		System.out.println("过滤");*/
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
class CharaterRequest extends HttpServletRequestWrapper{
	
	private HttpServletRequest request;
	public CharaterRequest(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
		this.request = request;
	}

	

	public String getParameter(String name) {
		String value = super.getParameter(name);
		if (value == null) {
			return null;
		}
		String method = super.getMethod();
		//equalsIgnoreCase方法在比较的过程中忽略大小写
		if("get".equalsIgnoreCase(method)) {
			try {
				value = new String(value.getBytes("iso-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO: handle exception
				throw new RuntimeException(e);
			}
		}
		return value;
	}
}
