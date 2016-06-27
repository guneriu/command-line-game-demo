package com.guneriu.game.util.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Reader to read file
 *
 * Created by ugur on 25.06.2016.
 */
public class LineReader implements ContentReader {

	private String fileName;

	/**
	 * creates {@link LineReader} to read file
	 * 
	 * @param fileName file to read
	 *
	 */
	public LineReader(String fileName) {
		if (fileName == null || fileName.isEmpty() || !isExist(fileName)) {
			throw new InvalidParameterException("file: " + fileName + " not found. Please specify existing fileName");
		}
		this.fileName = fileName;
	}
	
	private boolean isExist(String fileName) {
		return new File(fileName).exists();
	}

	@Override
	public long count() throws IOException {
		try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
			return lines.parallel().filter(s -> !s.isEmpty()).skip(1).count();
		}
	}

	@Override
	public List<String> read() throws IOException {
		try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
			return lines.parallel().filter(s -> !s.isEmpty()).skip(1).collect(Collectors.toList());
		}

	}

}
