package rob.test;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import rob.com.utils.D2L;
import rob.com.utils.D2LStudentSubmissionInfo;

public class TestMakeMoveUnzip
{
	public static void main(String[] args)
	{
		File dropbox_dir;
		String studentListFile = "_student_list.obj";

		JFileChooser getPath = new JFileChooser();
		getPath.setCurrentDirectory(new File(
				"H:/_var/assign_raw/gist/7132/"));
		getPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (getPath.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			dropbox_dir = getPath.getSelectedFile();
			ArrayList<D2LStudentSubmissionInfo> list = D2L
					.getStudentList(dropbox_dir);
			D2L.saveStudentList(new File(dropbox_dir, "_student_list.obj"),
					list);
			D2L.makeDirs(dropbox_dir, list);
			D2L.moveFiles(dropbox_dir, list);
			D2L.unZipAllStudentFiles(dropbox_dir, list);
		} // end of if the user selected a dir
	} // end of main
} // end of class
