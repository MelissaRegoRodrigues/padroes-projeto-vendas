package utils;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.ColumnData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class TablePrinter {

    @SafeVarargs
    public static <T> void printTable(Collection<T> data, Function<Column, ColumnData<T>>... columnsConfig) {
        printTable(BorderStyle.FANCY_ASCII, data, columnsConfig);
    }

    @SafeVarargs
    public static <T> void printTable(BorderStyle borderStyle, Collection<T> data, Function<Column, ColumnData<T>>... columnsConfig) {
        List<ColumnData<T>> columnsData = new ArrayList<>();

        for (var columnConfig : columnsConfig) {
            columnsData.add(columnConfig.apply(new Column()));
        }
        System.out.println(AsciiTable.getTable(borderStyle.getBorderCharacters(), data, columnsData));
    }

}
