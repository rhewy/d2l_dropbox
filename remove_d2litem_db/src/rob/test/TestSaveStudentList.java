package rob.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
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
				ObjectInputStream in = new ObjectInputStream(
						new FileInputStream(getPath.getSelectedFile()));
				ArrayList<D2LStudentSubmissionInfo> testList = (ArrayList<D2LStudentSubmissionInfo>) in
						.readObject();
				for (D2LStudentSubmissionInfo info : testList)
				{
					System.out.println(info.getFirstLast());
				}
			}
		}

	}

}
