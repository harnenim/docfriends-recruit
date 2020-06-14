package test.docfriends.api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import test.docfriends.api.www.AccessInterceptor;
import test.docfriends.api.www.MapResolver;

@Configuration
public class MvcConfiguerer implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AccessInterceptor()).addPathPatterns("/**");
	}
	
	@Autowired private MapResolver mapResolver;
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(mapResolver);
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
	        .allowCredentials(true)
	        .allowedOrigins("*")
	        .allowedHeaders("*")
	        .allowedMethods("*")
	        .maxAge(3600);
	}

}
