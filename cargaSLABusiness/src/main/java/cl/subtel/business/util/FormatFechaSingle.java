package cl.subtel.business.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

@Component("formatoFechas")
@PropertySource("classpath:formatoFecha.properties")
@Scope("singleton")
public class FormatFechaSingle {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Value("${formato.fecha}")
	private String formatoFecha;

	@Value("${formato.hora}")
	private String formatoHora;

	public String getFormatoFecha() {
		return formatoFecha;
	}

	public String getFormatoHora() {
		return formatoHora;
	}

}
