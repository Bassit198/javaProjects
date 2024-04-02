package bassit.dependencyinjectioncolors.services.impl;

import bassit.dependencyinjectioncolors.services.BluePrinter;

public class EnglishBluePrinter implements BluePrinter {

    @Override
    public String print() {
        return "Blue";
    }
}
