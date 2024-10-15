package utils.terminal;

import org.beryx.textio.TextIO;
import org.beryx.textio.system.SystemTextTerminal;

public class BetterInputs {

    private static TextIO textIO = new TextIO(new SystemTextTerminal());

    public static TextIO prepareIO() {
        return textIO;
    }

    public static boolean getConfirmation(String text, boolean defalt) {
        return textIO.newBooleanInputReader()
            .withFalseInput("N")
            .withTrueInput("S")
            .withDefaultValue(defalt)
            .read(text);
    }

}
