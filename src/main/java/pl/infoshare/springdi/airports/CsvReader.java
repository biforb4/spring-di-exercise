package pl.infoshare.springdi.airports;

import java.io.Reader;
import java.util.List;
import java.util.function.Function;

interface CsvReader {

    String DEFAULT_SEPARATOR = ",";

    @FunctionalInterface
    interface RowMapper<R> extends Function<String[], R> {
        @Override
        R apply(String[] input);
    }

    <R> List<R> parse(Reader source, RowMapper<R> rowMapper, String separator, int skipLines);

    default <R> List<R> parse(Reader source, RowMapper<R> rowMapper) {
        return this.parse(source, rowMapper, DEFAULT_SEPARATOR, 0);
    }

    default <R> List<R> parse(Reader source, RowMapper<R> rowMapper, String separator) {
        return this.parse(source, rowMapper, separator, 0);
    }
}
