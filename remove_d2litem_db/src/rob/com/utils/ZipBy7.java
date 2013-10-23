package rob.com.utils;

import java.io.RandomAccessFile;
import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.ISevenZipInArchive;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;

public class ZipBy7
{
	
	public static int getNumberOfItemsInArchive(String archiveFile) throws Exception {
	    ISevenZipInArchive archive;
	    RandomAccessFile randomAccessFile;

	    randomAccessFile = new RandomAccessFile(archiveFile, "r");

	    archive = SevenZip.openInArchive(ArchiveFormat.ZIP, // null - autodetect
	            new RandomAccessFileInStream(
	                    randomAccessFile));

	    int numberOfItems = archive.getNumberOfItems();

	    archive.close();
	    randomAccessFile.close();

	    return numberOfItems;
	}
}

