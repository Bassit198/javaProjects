package bassit.dependencyinjectioncolors.services.impl;

import bassit.dependencyinjectioncolors.services.BluePrinter;
import org.springframework.stereotype.Component;

@Component
public class EnglishBluePrinter implements BluePrinter {

    @Override
    public String print() {
        return "Blue";
    }
}
