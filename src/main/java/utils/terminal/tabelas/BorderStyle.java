package utils.terminal.tabelas;

import com.github.freva.asciitable.AsciiTable;

public enum BorderStyle {

    NO_BORDERS(AsciiTable.NO_BORDERS),
    BASIC_ASCII(AsciiTable.BASIC_ASCII),
    BASIC_ASCII_NO_DATA_SEPARATORS(AsciiTable.BASIC_ASCII_NO_DATA_SEPARATORS),
    BASIC_ASCII_NO_DATA_SEPARATORS_NO_OUTSIDE_BORDER(AsciiTable.BASIC_ASCII_NO_DATA_SEPARATORS_NO_OUTSIDE_BORDER),
    FANCY_ASCII(AsciiTable.FANCY_ASCII);

    private final Character[] borderCharacters;

    BorderStyle(Character[] borderCharacters) {
       this.borderCharacters = borderCharacters;
    }

    public Character[] getBorderCharacters() {
        return borderCharacters;
    }

}
