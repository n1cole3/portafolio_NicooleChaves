
package tienda.demo;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/*
    La clase implementa la interfaz por lo cual va a llevar metodos especificos de esa clase 
    lo que llevaria a los diferentes Beans
*/

public class ProjectConfig implements WebMvcConfigurer{
    /*
    Un @Bean son notaciones de spring que funcionan para definir metodos y seran 
    gestionados por el contenedor de Spring 
    
    El localeResolver se utiliza para sesion de usuario y configuracion regional
    */
     @Bean
    public SessionLocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
       
        return slr;
    }
    /*
       Este funciona para cambiar el locale en funcion de una solicitud osea modifica el de arriba
    */
    @Bean
    public LocaleChangeInterceptor localeChanceInterceptor(){
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    /*
    Este no lo entendi 
    */
    @Override
    public void addInterceptors(InterceptorRegistry registro){
        registro.addInterceptor(localeChanceInterceptor());
    }
    /*
        Este lee archivps y establece nombres base de los archivos de mensajes 
    */
    @Bean("messageBrouce")
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
}
