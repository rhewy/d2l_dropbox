package rob.com.utils;

//==============================================================================
// File         : FileExtractStream.java
//
// Current Author: Robert Hewlett
//
// Previous Author: None
//
// Contact Info: rob.hewy@.com
//
// Purpose : a 'Go between class' or wrapper class to fuse the 7zip 
//           ISequentialOutStream interface with a standard Java
//           FileOutputStream
//
// Dependencies: JARS: sevenzipjbinding and sevenzipjbinding-AllWindows 
//
// Modification Log :
//    --> Created OCT-24-2013 (rh)
//    --> Updated MMM-DD-YYYY (fl)
//
// =============================================================================
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.SevenZipException;

public class FileExtractStream implements ISequentialOutStream
{
	// ===========================================================
	// You need a place (dir) to write to and a stream to do the writing
	// ===========================================================
	private File fileOut;
	private FileOutputStream out;

	// ===========================================================
	// Constructor: A location to write to is a requirement
	// ===========================================================	
	FileExtractStream(File fout)
	{
		try
		{
			// ===========================================================
			// make any missing parent dirs and initialize the stream
			// ===========================================================	
			this.fileOut = fout;
			fout.getParentFile().mkdirs();
			out = new FileOutputStream(this.fileOut);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	// ===========================================================
	// This is the one method required by ISequentialOutStream
	// ===========================================================
	@Override
	public int write(byte[] data) throws SevenZipException
	{
		int bytesWritten = 0;
		try
		{
			// ===========================================================
			// writes the byes out in one big chunk
			// ===========================================================	
			this.out.write(data);
			this.out.close();
			bytesWritten = data.length;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		// ===========================================================
		// send back the number of bytes written. BTW do not send back
		// zero and an exception will be thrown
		// ===========================================================	
		return bytesWritten;
	}
}
