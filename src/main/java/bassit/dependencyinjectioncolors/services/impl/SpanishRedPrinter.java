package bassit.dependencyinjectioncolors.services.impl;

import bassit.dependencyinjectioncolors.services.RedPrinter;

public class SpanishRedPrinter implements RedPrinter {
    @Override
    public String print() {
        return "Rojo";
    }
}
