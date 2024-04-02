package bassit.dependencyinjectioncolors.services.impl;

import bassit.dependencyinjectioncolors.services.GreenPrinter;

public class EnglishGreenPrinter implements GreenPrinter {
    @Override
    public String print() {
        return "Green";
    }
}
