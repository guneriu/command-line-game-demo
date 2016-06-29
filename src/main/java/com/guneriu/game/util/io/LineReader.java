package com.guneriu.game.util.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Reader to read lines of inputStream
 *
 * Created by ugur on 25.06.2016.
 */
public class LineReader implements ContentReader {

	private BufferedReader reader;

	/**
	 * creates {@link LineReader} to read lines of inputStream
	 * 
	 * @param inputStream to read
	 *
	 */
	public LineReader(InputStream inputStream) {
		Objects.requireNonNull(inputStream, "InputStream must not be null");
		this.reader = new BufferedReader(new InputStreamReader(inputStream));
	}
	
	@Override
	public long count() throws IOException {
		try (Stream<String> lines = reader.lines()) {
			return lines.parallel().filter(s -> !s.isEmpty()).skip(1).count();
		}
	}

	@Override
	public List<String> read() throws IOException {
		try (Stream<String> lines = reader.lines()) {
			return lines.parallel().filter(s -> !s.isEmpty()).skip(1).collect(Collectors.toList());
		}

	}

}
