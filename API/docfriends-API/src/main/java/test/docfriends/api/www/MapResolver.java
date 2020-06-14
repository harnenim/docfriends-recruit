package test.docfriends.api.www;

import java.io.Serializable;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import test.docfriends.api.spring.Params;

/**
 * 
 * MapResolver.java
 *
 * <pre>
 * Please write a description. 
 * </pre>
 *
 * @author 송규호 
 * @date 2019. 4. 12.
 */
@Component
public class MapResolver implements HandlerMethodArgumentResolver, Serializable {
	 
	private static final long serialVersionUID = 4533062076083018410L;
	
	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		return Params.class.isAssignableFrom(methodParameter.getParameterType());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mvContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Params parameters = new Params();
		Iterator iterator = webRequest.getParameterNames();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String[] values = webRequest.getParameterValues(key);
			if (values!=null) {
				parameters.put(key, (values.length > 1) ? values : values[0]);
			}
		}
		
		return parameters;
	}
	
	public static String getIp(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");
	    
	    if (ip == null || ip.equals("") || ip.equals("unknown")) {
	        //Proxy 서버인 경우
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null || ip.equals("") || ip.equals("unknown")) {
	        //Weblogic 서버인 경우
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null || ip.equals("") || ip.equals("unknown")) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	    }
	    if (ip == null || ip.equals("") || ip.equals("unknown")) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	    }
	    if (ip == null || ip.equals("") || ip.equals("unknown")) {
	        ip = request.getRemoteAddr();
	    }
	    
	    return ip;
	}
}
