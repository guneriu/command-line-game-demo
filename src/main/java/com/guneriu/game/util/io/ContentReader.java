package com.guneriu.game.util.io;

import java.io.IOException;
import java.util.List;

/**
 * ContentReader for reading data from source
 *
 * Created by ugur on 25.06.2016.
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
