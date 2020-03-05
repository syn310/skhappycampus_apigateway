package com.skcc;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class TxIdPreFilter extends ZuulFilter{
	
	private static final Logger log = LoggerFactory.getLogger(TxIdPreFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		RequestContext ctx = RequestContext.getCurrentContext();
//		UUID uuid = UUID.randomUUID();
//		String txId = String.format("%s-%s", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()), uuid.toString());
//		ctx.addZuulRequestHeader("X-TXID", txId);
		
		HttpServletRequest request = ctx.getRequest();
		String headerToken = request.getHeader("x-access-token");
		
		ctx.addZuulRequestHeader("x-access-token", headerToken);
//		System.out.println("x-access-token ===== " + headerToken);
		
		
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}
	
}
