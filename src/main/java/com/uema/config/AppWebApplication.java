package com.uema.config;

import com.uema.controllers.UemaControllers;
import com.uema.parametrizacao.controllers.ParametrizacaoController;
import com.uema.parametrizacao.models.ParametrizacaoModel;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Locale;

@EnableWebMvc // Habilita funcionalidades como manipulação de json,
@ComponentScan(basePackageClasses = {UemaControllers.class, ParametrizacaoController.class, ParametrizacaoModel.class})
public class AppWebApplication extends WebMvcConfigurerAdapter {

    private static final String PREFIX = "/WEB-INF/views/";
    private static final String SUFFIX = ".html";
    private static final String UTF    = "UTF-8";

    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding(UTF);
        return resolver;
    }

    @Bean
    public TemplateEngine templateEngine() {
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