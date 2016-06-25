package com.guneriu.game.io;

import java.io.IOException;
import java.util.List;

/**
 * ContentReader for reading data from source
 *
 */
public interface ContentReader {
	
	/**
	 * reads data from
	 * 
	 * @exception IOException  If an I/O error occurs
	 */
	public List<String> read() throws IOException;

	/**
	 * count of content
	 * 
	 * @exception IOException  If an I/O error occurs
	 */
	public long count() throws IOException;

}
