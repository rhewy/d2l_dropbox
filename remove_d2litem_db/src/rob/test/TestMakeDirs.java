package rob.test;

import java.util.ArrayList;

import javax.swing.JFileChooser;

import rob.com.utils.D2L;
import rob.com.utils.D2LStudentSubmissionInfo;

public class TestMakeDirs
{
	public static void main(String[] args)
	{
		JFileChooser getPath = new JFileChooser();
		if (getPath.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			ArrayList<D2LStudentSubmissionInfo> testList = D2L
					.loadStudentList(getPath.getSelectedFile());
			D2L.makeDirs(getPath.getSelectedFile().getParentFile(), testList);
		}
	}

}
