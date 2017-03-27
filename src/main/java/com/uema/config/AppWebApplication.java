package com.uema.config;

import com.uema.controllers.UemaControllers;
import com.uema.parametrizacao.controllers.ParametrizacaoController;
import com.uema.parametrizacao.models.ParametrizacaoModel;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.dialect.SpringStandardDialect;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Configuration
@EnableWebMvc // Habilita funcionalidades como manipulação de json,
@ComponentScan(basePackageClasses = {UemaControllers.class, ParametrizacaoController.class, ParametrizacaoModel.class})
public class AppWebApplication extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private static final String PREFIX = "/WEB-INF/views/";
    private static final String SUFFIX = ".html";
    private static final String UTF    = "UTF-8";
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /* ******************************************************************* */
    /*  GENERAL CONFIGURATION ARTIFACTS                                    */
    /*  Static Resources, i18n Messages, Formatters (Conversion Service)   */
    /* ******************************************************************* */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/resources/fonts/");
    }

    @Bean
    public Set<IDialect> thymeleafDialects() {
        Set<IDialect> dialects = new HashSet<IDialect>();
        dialects.add(new SpringStandardDialect());
        dialects.add(new LayoutDialect());
        return dialects;
    }

    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix(PREFIX);
        resolver.setSuffix(SUFFIX);
        resolver.setTemplateMode("HTML5");
        resolver.setOrder(1);
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new FixedLocaleResolver(new Locale("pt", "BR"));
    }

    // Método responsável por informar ao spring onde buscar as mensagens de
    // erros relacionadas às validações
    //
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
        bundle.setBasename("/WEB-INF/i18n/messages/");
        bundle.setDefaultEncoding("UTF-8");
        bundle.setCacheSeconds(1); // Permite atualizar o cache a cada segundo, para não precisar
        // subir o servidor todo o momento

        return bundle;
    }

    // Código responsável por definir ao Spring como será a formatação
    // de datas salvas na base de dados
    //
    @Bean
    public FormattingConversionService mvcConversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(true);

        DateFormatterRegistrar registrar = new DateFormatterRegistrar();
        registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
        registrar.registerFormatters(conversionService);

        return conversionService;
    }
}