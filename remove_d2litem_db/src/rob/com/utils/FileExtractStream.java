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
//    --> Updated OCT-26-2013 (rh) updated for chunk-ing the write
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
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// ==============================================================================
	// Method : write
	//
	// Current Author: Robert Hewlett
	//
	// Previous Author: None
	//
	// Contact Info: rob.hewy@gmail.com
	//
	// Purpose : This is a requirement of ISequentialOutStream.
	//
	// Note: After testing  on larger files within a zip file, I realized
	// this method can be called several times on a single file within the
	// archive ... the library chunks the decompression
	//
	// Dependencies: None
	//
	// Modification Log :
	// --> Created OCT-23-2013 (rh)
	// --> Updated OCT-26-2013 (rh) --> modified for chunking
	//
	// =============================================================================
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
			bytesWritten = data.length;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		// ===========================================================
		// Send back the chunk size written on this call
		// if zero is sent back something went wrong
		// ===========================================================
		return bytesWritten;
	}
	// ==============================================================================
	// Method : close
	//
	// Current Author: Robert Hewlett
	//
	// Previous Author: None
	//
	// Contact Info: rob.hewy@gmail.com
	//
	// Purpose : Simple pass-through to close the stream  
	//
	// Note: Needed an external method to close the file
	//       Previously I was closing the file too early 
	//
	// Dependencies: None
	//
	// Modification Log :
	// --> Created OCT-26-2013 (rh)
	// --> Updated MM-DD-YYYY (fl)
	//
	// =============================================================================

	public void close()
	{
		try
		{
			this.out.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
