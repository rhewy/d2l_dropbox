package rob.test;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import rob.com.utils.D2L;
import rob.com.utils.D2LStudentSubmissionInfo;
import rob.com.utils.ExtensionsToSkip;
import rob.com.utils.FileExtension;

public class TestUnZipAllStudentsFilesD2L
{
	public static void main(String[] args)
	{
		JFileChooser getPath = new JFileChooser();
		getPath.setCurrentDirectory(new File("H:/_var/assign_raw/gist/7010/test"));
		
		if (getPath.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			ArrayList<D2LStudentSubmissionInfo> testList = 
					D2L.loadStudentList(getPath.getSelectedFile());
			ExtensionsToSkip extFact = new ExtensionsToSkip();
			extFact.loadDefault();
			ArrayList<FileExtension> exts = extFact.getExtensions();
			
			D2L.unZipAllStudentFiles(getPath.getSelectedFile().getParentFile(), testList, exts);	
		}		
		System.out.println("Done .... ");
	}
}
