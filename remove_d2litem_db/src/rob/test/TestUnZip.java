package rob.test;

import java.io.File;

import javax.swing.JFileChooser;

import rob.com.utils.ZipBy7;

public class TestUnZip
{
	public static void main(String[] args)
	{
		JFileChooser getPath = new JFileChooser();
		getPath.setCurrentDirectory(new File("H:/_var/assign_raw/gist/7010/test"));
		if (getPath.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			if(ZipBy7.isArchive(getPath.getSelectedFile()))
			{
				ZipBy7.unZipWholeFile(getPath.getSelectedFile(), 
						getPath.getSelectedFile().getParentFile());
			}
			else
			{
				System.out.println("Regular file");
			}
		}

	}

}
