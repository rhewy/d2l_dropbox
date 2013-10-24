package rob.test;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import rob.com.utils.D2L;
import rob.com.utils.D2LStudentSubmissionInfo;

public class TestMakeDirs
{
	public static void main(String[] args)
	{
		JFileChooser getPath = new JFileChooser();
		if (getPath.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			ArrayList<D2LStudentSubmissionInfo> testList = D2L
					.loadStudentList(getPath.getSelectedFile());
			File tmpDir;
			for (D2LStudentSubmissionInfo info : testList)
			{
				tmpDir = new File(getPath.getSelectedFile().getParentFile(),
						info.getFirstLastStudID());
				tmpDir.mkdir();
				tmpDir = null;

			}
		}
	}

}
