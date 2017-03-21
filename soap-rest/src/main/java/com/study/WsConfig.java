package com.study;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Created by denis on 21.03.2017.
 */
@Configuration
@EnableWs
public class WsConfig {

    @Value("classpath:wsdl/SoapRestTypes.xsd")
    private Resource xsdSoapRestTypes;
    @Value("classpath:wsdl/SoapRestService.wsdl")
    private Resource wsdlSoapRestService;

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean("SoapRestService")
    public SimpleWsdl11Definition wsdlSchemaSoapRestService() {
        return new SimpleWsdl11Definition(wsdlSoapRestService);
    }

    @Bean("SoapRestTypes")
    public XsdSchema xsdSoapRestTypesTypes() {
        return new SimpleXsdSchema(xsdSoapRestTypes);
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPaths(
                "ru.study.com.soap.types");
        return marshaller;
    }
}
