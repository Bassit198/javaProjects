package bassit.dependencyinjectioncolors.services.impl;

import bassit.dependencyinjectioncolors.services.RedPrinter;

public class EnglishRedPrinter implements RedPrinter {
    @Override
    public String print() {
        return "Red";
    }
}
