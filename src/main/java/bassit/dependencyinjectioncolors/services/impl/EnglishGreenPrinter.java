package bassit.dependencyinjectioncolors.services.impl;

import bassit.dependencyinjectioncolors.services.GreenPrinter;
import org.springframework.stereotype.Component;

@Component
public class EnglishGreenPrinter implements GreenPrinter {
    @Override
    public String print() {
        return "Green";
    }
}
