package bassit.dependencyinjectioncolors.config;

import bassit.dependencyinjectioncolors.services.BluePrinter;
import bassit.dependencyinjectioncolors.services.ColorPrinter;
import bassit.dependencyinjectioncolors.services.GreenPrinter;
import bassit.dependencyinjectioncolors.services.RedPrinter;
import bassit.dependencyinjectioncolors.services.impl.ColorPrinterImpl;
import bassit.dependencyinjectioncolors.services.impl.SpanishBluePrinter;
import bassit.dependencyinjectioncolors.services.impl.SpanishGreenPrinter;
import bassit.dependencyinjectioncolors.services.impl.SpanishRedPrinter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrinterConfig {

    @Bean
    public BluePrinter bluePrinter(){
        return new SpanishBluePrinter();
    }

    @Bean
    public RedPrinter redPrinter(){
        return new SpanishRedPrinter();
    }

    @Bean
    public GreenPrinter greenPrinter(){
        return new SpanishGreenPrinter();
    }

    @Bean
    public ColorPrinter colorPrinter(BluePrinter bluePrinter, RedPrinter redPrinter, GreenPrinter greenPrinter){
        return new ColorPrinterImpl(redPrinter, bluePrinter, greenPrinter);
    }
}
