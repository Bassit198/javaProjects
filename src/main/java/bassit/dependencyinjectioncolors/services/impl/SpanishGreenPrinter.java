package bassit.dependencyinjectioncolors.services.impl;

import bassit.dependencyinjectioncolors.services.GreenPrinter;

public class SpanishGreenPrinter implements GreenPrinter {
    @Override
    public String print() {
        return "Verde";
    }
}
