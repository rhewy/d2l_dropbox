package rob.test;
//==============================================================================
//File         : Test7ZipLib.java
//
//Current Author: Robert Hewlett
//
//Previous Author: None
//
//Contact Info: rob.hewy@gmail.com
//
//Purpose : Simple test method to make sure 7zip JNI is working/linkable
//
//Dependencies: None
//
//Modification Log :
// --> Created OCT-23-2013 (rh)
// --> Updated OCT-25-2013 (rh) moved code to Test7ZipLib.java
//
//=============================================================================
import java.io.RandomAccessFile;

import javax.swing.JFileChooser;

import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.ISevenZipInArchive;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;

public class Test7ZipLib
{
	public static void main(String[] args)
	{
		try
		{
			JFileChooser getPath = new JFileChooser();
			getPath.showOpenDialog(null);
			int num_files = getNumberOfItemsInArchive(getPath.getSelectedFile().getAbsolutePath());
			System.out.println(num_files);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

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
