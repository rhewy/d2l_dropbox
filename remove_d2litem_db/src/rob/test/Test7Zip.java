package rob.test;

import javax.swing.JFileChooser;

import rob.com.utils.ZipBy7;

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
//    --> Updated OCT-25-2013 (rh) moved code to Test7ZipLib.java
//
// =============================================================================
public class Test7Zip
{
	public static void main(String[] args) throws Exception
	{
		JFileChooser getPath = new JFileChooser();
		if (getPath.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			if(ZipBy7.isArchive(getPath.getSelectedFile().getAbsolutePath()))
			{
				System.out.println("Zip file");
			}
			else
			{
				System.out.println("Regular file");
			}
		}

	}

} 
