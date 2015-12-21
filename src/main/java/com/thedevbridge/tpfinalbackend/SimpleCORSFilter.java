package com.thedevbridge.tpfinalbackend;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Allow CORS : Any origin request
 * @author valdese
 *
 */
@Component
public class SimpleCORSFilter implements Filter {
	
	
	/** The LOG field */
	private static final Log LOG = LogFactory.getLog(SimpleCORSFilter.class);
	
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletResponse response = (HttpServletResponse) res;
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Language, Cache-Control, Pragma, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, Authorization");
		chain.doFilter(req, res);
	}


	public void destroy() {
		
	}


	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
