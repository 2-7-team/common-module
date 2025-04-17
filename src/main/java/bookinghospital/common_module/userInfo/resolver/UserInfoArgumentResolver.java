package bookinghospital.common_module.userInfo.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import bookinghospital.common_module.userInfo.UserDetails;
import bookinghospital.common_module.userInfo.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(UserInfo.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		if (request != null) {
			String userId = request.getHeader("X-User-Name");
			String userRole = request.getHeader("X-User-Role");

			log.info("X-USERNAME : " + userId);
			log.info("X-USER-ROLE : " + userRole);

			try{
				Long userLongId = Long.parseLong(userId);
				return new UserDetails(userLongId, userRole);
			}catch (NumberFormatException e) {
				throw new NumberFormatException("Invalid X-User-Name");
			}

		}
		log.info("cannot find X-User-Name or X-User-Role");
		return null;
	}
}
