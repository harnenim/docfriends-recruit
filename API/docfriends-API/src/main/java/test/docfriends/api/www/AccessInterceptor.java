package test.docfriends.api.www;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author 송규호
 *
 */
public class AccessInterceptor implements HandlerInterceptor {
	
	/**
	 * url 이동 전 세션 체크를 통해 이동할 수 있는지 체크한 후 결과를 반환한다.
	 * @param request
	 * @param response
	 * @param handler
	 * @return 화면 전환 가능 여부
	 * @throws Exception 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		HttpSession httpSession = request.getSession();
		
//		String reqUri = request.getRequestURI();
//		Session session = (Session) httpSession.getAttribute("session");
		
		return true;
	}

	/**
	 * url 핸들링 이후 동작 메소드
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	/**
	 * 동작이 끝난 후 실행되는 메소드
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		Collection<String> headers = response.getHeaders(HttpHeaders.SET_COOKIE);
		
		if (headers.size() == 0) {
			response.addHeader(HttpHeaders.SET_COOKIE, "a=; Secure; SameSite=None");
			return;
		}
		
		boolean isFirst = true;
		for (String header : headers) {
			if (isFirst) {
				response.setHeader(HttpHeaders.SET_COOKIE, header + "; Secure; SameSite=None");
				isFirst = false;
				continue;
			}
			response.addHeader(HttpHeaders.SET_COOKIE, header + "; Secure; SameSite=None");
		}
	}
}
