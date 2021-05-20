package pl.infoshare.springdi.airports;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
class CsvReaderImpl implements CsvReader {
    @Override
    public <R> List<R> parse(Reader source, RowMapper<R> rowMapper, String separator, int skipLines) {
        try (var bufferedReader = new BufferedReader(source)) {
            return bufferedReader
                    .lines()
                    .skip(skipLines)
                    .map(l -> l.split(separator))
                    .map(rowMapper)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
