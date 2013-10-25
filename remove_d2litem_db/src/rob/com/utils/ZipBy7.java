package rob.com.utils;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.ISevenZipInArchive;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;

public class ZipBy7
{
	static public boolean isArchive(String archiveFilename)

	{
		boolean zipFile = false;
		// ===========================================================
		// Try to open the file as a zip file if that fails 
		// then assume it is not a zip file
		// ===========================================================

		try
		{
			RandomAccessFile randomAccessFile = new RandomAccessFile(
					archiveFilename, "r");
			ISevenZipInArchive inArchive = SevenZip.openInArchive(null,
					new RandomAccessFileInStream(randomAccessFile));
			inArchive.close();
			zipFile = true;
		} // end of try
		catch (Exception e)
		{
		} // end of catch
		return zipFile;
	} // end of method

} // end of class

