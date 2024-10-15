package utils.terminal;

import org.beryx.textio.TextIO;
import org.beryx.textio.system.SystemTextTerminal;

import java.util.HashMap;

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

    /**
     * Retorna um valor numérico, a partir de 1, correspondente à numeração que o usuário entrar que corresponda
     * a algum dos valores passados. O primeiro elemento dos valores passados, por exemplo, caso selecionado,
     * retornará 1
     * @param prompt pergunta que será feita ao usuário
     * @param values valores que serão enumerados, conforme a ordem passada
     * @return valor inteiro, <i>[1, values.length]</i>, correspondente a algum dos valores passados
     */
    public static int getIntFromEnumeratedValues(String prompt, String... values) {
        HashMap<String, Integer> mapping = new HashMap<>();

        for (int i = 1; i <= values.length; i++) {
            mapping.put(values[i-1], i);
        }

        String selected = BetterInputs.prepareIO().newStringInputReader()
            .withNumberedPossibleValues(values)
            .read(prompt);

        return mapping.get(selected);
    }

}
