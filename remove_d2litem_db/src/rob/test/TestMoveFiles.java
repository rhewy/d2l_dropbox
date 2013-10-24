package rob.test;

import java.util.ArrayList;

import javax.swing.JFileChooser;

import rob.com.utils.D2L;
import rob.com.utils.D2LStudentSubmissionInfo;

public class TestMoveFiles
{
	public static void main(String[] args)
	{
		JFileChooser getPath = new JFileChooser();
		if (getPath.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			ArrayList<D2LStudentSubmissionInfo> list = D2L
					.loadStudentList(getPath.getSelectedFile());
			D2L.moveFile(getPath.getSelectedFile().getParentFile(), list);
		}
	}
}
