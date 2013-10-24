package rob.test;
//==============================================================================
// File         : Test7Zip.java
//
// Current Author: Robert Hewlett
//
// Previous Author: None
//
// Contact Info: rob.hewy@gmail.com
//
// Purpose : Simple test method to make sure 7zip JNI is working/linkable
//
// Dependencies: None
//
// Modification Log :
//    --> Created OCT-23-2013 (rh)
//    --> Updated MMM-DD-YYYY (fl)
//
// =============================================================================
import javax.swing.JFileChooser;

import rob.com.utils.ZipBy7;

public class Test7Zip
{
	public static void main(String[] args) throws Exception
	{
		try
		{
			JFileChooser getPath = new JFileChooser();
			getPath.showOpenDialog(null);
			int num_files = ZipBy7.getNumberOfItemsInArchive(getPath.getSelectedFile().getAbsolutePath());
			System.out.println(num_files);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
