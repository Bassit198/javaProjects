package bassit.dependencyinjectioncolors;

//in this implementation, the beans are defined with @Component annotation on top of the implementing class

import bassit.dependencyinjectioncolors.services.ColorPrinter;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class DependencyInjectionColorsApplication implements CommandLineRunner {

	private ColorPrinter colorPrinter;

	public DependencyInjectionColorsApplication(ColorPrinter colorPrinter){
		this.colorPrinter = colorPrinter;
	}

	public static void main(String[] args) {
		SpringApplication.run(DependencyInjectionColorsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(colorPrinter.print());
	}
}
