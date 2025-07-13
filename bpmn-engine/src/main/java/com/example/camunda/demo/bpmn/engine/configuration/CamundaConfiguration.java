package com.example.camunda.demo.bpmn.engine.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.camunda.connect.plugin.impl.ConnectProcessEnginePlugin;

@Configuration
public class CamundaConfiguration {

  @Bean
  @ConditionalOnProperty(value = "app.excamad.enabled", havingValue = "true")
  public FilterRegistrationBean<CorsFilter> processCorsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(false);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);

    FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
    bean.setOrder(0);
    return bean;
  }

  @Bean
  public ConnectProcessEnginePlugin connectProcessEnginePlugin() {
    return new ConnectProcessEnginePlugin();
  }

}
