package com.baykalsoft.debtrack.config;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class SpringInternationalizationConfig implements WebMvcConfigurer {

//  @Bean
//  public MessageSource messageSource() {
//    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//    messageSource.setDefaultEncoding("UTF-8");
//    return messageSource;
//  }

  @Bean
  public LocaleResolver localeResolver() {
    CookieLocaleResolver slr = new CookieLocaleResolver();
    slr.setDefaultLocale(Locale.ENGLISH);
    return slr;
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
    lci.setParamName("lang");
    return lci;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor()).addPathPatterns("/**");
  }

}