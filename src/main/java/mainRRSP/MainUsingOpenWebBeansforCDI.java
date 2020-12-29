package mainRRSP;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import org.apache.webbeans.config.WebBeansContext;
import org.apache.webbeans.spi.ContainerLifecycle;

public class MainUsingOpenWebBeansforCDI {

	public static void main(String[] args) {

		final ContainerLifecycle lifecycle = WebBeansContext.currentInstance().getService(ContainerLifecycle.class);
		lifecycle.startApplication(null);

		final BeanManager beanManager = lifecycle.getBeanManager();
		final Bean<?> bean = beanManager.getBeans(Service.class).iterator().next();

		final Service service = (Service) lifecycle.getBeanManager().getReference(bean, Service.class,
				beanManager.createCreationalContext(bean));
		service.test_writeJsonToFile();
		lifecycle.stopApplication(null);
		// https://www.codota.com/code/java/classes/org.apache.webbeans.spi.ContainerLifecycle
		// https://medium.com/danieldiasjava/using-apache-openwebbeans-in-java-se-2af5d0312a22
	}

}
