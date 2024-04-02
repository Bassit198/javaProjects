package bassit.dependencyinjectioncolors.services.impl;

import bassit.dependencyinjectioncolors.services.RedPrinter;
import org.springframework.stereotype.Component;

@Component
public class EnglishRedPrinter implements RedPrinter {
    @Override
    public String print() {
        return "Red";
    }
}
