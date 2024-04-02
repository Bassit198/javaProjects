package bassit.dependencyinjectioncolors.services.impl;

import bassit.dependencyinjectioncolors.services.BluePrinter;
import bassit.dependencyinjectioncolors.services.ColorPrinter;
import bassit.dependencyinjectioncolors.services.GreenPrinter;
import bassit.dependencyinjectioncolors.services.RedPrinter;
import org.springframework.stereotype.Component;

@Component
public class ColorPrinterImpl implements ColorPrinter {
    private RedPrinter redPrinter;
    private BluePrinter bluePrinter;
    private GreenPrinter greenPrinter;

    public ColorPrinterImpl(RedPrinter redPrinter, BluePrinter bluePrinter, GreenPrinter greenPrinter){
        this.redPrinter = redPrinter;
        this.bluePrinter = bluePrinter;
        this.greenPrinter = greenPrinter;
    }

    @Override
    public String print() {
        return String.join(", ", redPrinter.print(), bluePrinter.print(), greenPrinter.print());
    }
}
