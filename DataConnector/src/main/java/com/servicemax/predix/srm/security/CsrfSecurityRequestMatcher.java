package com.servicemax.predix.srm.security;



import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

//import org.springframework.security.web.util.matcher.RegexRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;

public class CsrfSecurityRequestMatcher {}//implements RequestMatcher {
	/*private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS|POST)$");
	private RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher("/api.*", null);

	@Override
	public boolean matches(HttpServletRequest request) {
		/*if (allowedMethods.matcher(request.getMethod()).matches()) {
			return false;
		}
		return !unprotectedMatcher.matches(request);*/
		/*return true;
	}
}
*/
