package bassit.dependencyinjectioncolors;

//in this implementation, the beans are defined within the config file.
//firstly, the interface is created, then the interface is implemented in Impl, then within the colorprinterimpl, the interfaces are initialized and assigned values by the constructor
//then finally, the print method is called for each printers

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
