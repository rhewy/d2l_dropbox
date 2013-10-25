package rob.test;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import rob.com.utils.D2L;
import rob.com.utils.D2LStudentSubmissionInfo;

public class TestSaveStudentList
{
	public static void main(String[] args) throws Exception
	{
		File dropbox_dir;

		JFileChooser getPath = new JFileChooser();
		getPath.setCurrentDirectory(new File("H:/_var/assign_raw/gist/7010/test"));
		getPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (getPath.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			dropbox_dir = getPath.getSelectedFile();
			ArrayList<D2LStudentSubmissionInfo> list = D2L
					.getStudentList(dropbox_dir);
			getPath.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			if (getPath.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				D2L.saveStudentList(getPath.getSelectedFile(), list);
		
				ArrayList<D2LStudentSubmissionInfo> testList = 
						D2L.loadStudentList(getPath.getSelectedFile());
				for (D2LStudentSubmissionInfo info : testList)
				{
					System.out.println(info.getFirstLast());
				}
			}
		}

	}

}
