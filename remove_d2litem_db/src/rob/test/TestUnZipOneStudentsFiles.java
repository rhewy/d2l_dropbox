package rob.test;

import java.io.File;

import javax.swing.JFileChooser;
import rob.com.utils.D2L;

public class TestUnZipOneStudentsFiles
{
	public static void main(String[] args)
	{
		JFileChooser getPath = new JFileChooser();
		getPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		getPath.setCurrentDirectory(new File("H:/_var/assign_raw/gist/7010/test"));
		if (getPath.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			D2L.unZipOneStudentsFiles(getPath.getSelectedFile());
		}
	}

} 
