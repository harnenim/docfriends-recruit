package test.docfriends.api.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
	
	class Customizer implements TomcatConnectorCustomizer {
		@SuppressWarnings("deprecation")
		@Override
		public void customize(Connector connector) {
			connector.setAttribute("relaxedPathChars", "<>[\\]^`{|}");
			connector.setAttribute("relaxedQueryChars", "<>[\\]^`{|}");
		}
	}

	@Override
	public void customize(TomcatServletWebServerFactory factory) {
		factory.addConnectorCustomizers(new Customizer());
	}
	
}
