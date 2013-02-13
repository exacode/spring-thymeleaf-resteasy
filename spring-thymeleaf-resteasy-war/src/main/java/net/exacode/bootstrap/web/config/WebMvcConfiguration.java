package net.exacode.bootstrap.web.config;

import java.util.Locale;

import net.exacode.bootstrap.web.config.WebMvcConfiguration.CacheableThymeleafConfiguration;
import net.exacode.bootstrap.web.config.WebMvcConfiguration.NonCacheableThymeleafConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "net.exacode.bootstrap.web")
@Import({ CacheableThymeleafConfiguration.class,
		NonCacheableThymeleafConfiguration.class })
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	@Autowired
	public SpringTemplateEngine templateEngine(
			ServletContextTemplateResolver templateResolver) {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver);
		return engine;
	}

	@Bean
	@Autowired
	public ThymeleafViewResolver thymeleafViewResolver(
			SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine);
		resolver.setCharacterEncoding("UTF-8");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.ENGLISH);
		return localeResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Configuration
	@Profile({ ApplicationProfiles.PRODUCTION })
	static class CacheableThymeleafConfiguration {
		@Bean
		public ServletContextTemplateResolver templateResolver() {
			ServletContextTemplateResolver resolver = new NonCacheableThymeleafConfiguration()
					.templateResolver();
			resolver.setCacheable(true);
			return resolver;
		}
	}

	@Configuration
	@Profile({ ApplicationProfiles.TEST, ApplicationProfiles.DEVELOPMENT })
	static class NonCacheableThymeleafConfiguration {
		@Bean
		public ServletContextTemplateResolver templateResolver() {
			ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
			resolver.setPrefix("/WEB-INF/templates/");
			resolver.setSuffix(".html");
			resolver.setTemplateMode("HTML5");
			resolver.setCacheable(false);
			return resolver;
		}
	}

}
