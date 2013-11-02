package rob.run;

import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import rob.com.utils.D2L;
import rob.com.utils.D2LStudentSubmissionInfo;
import rob.com.utils.ExtensionsToSkip;
import rob.com.utils.ZipBy7;
import rob.test.TestUnZipOneStudentsFiles;
import javax.swing.JButton;

public class MainFrame
{

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 520, 318);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				getFrame().dispose();
			}
		});

		JMenuItem mntmUnzipDropboxFile = new JMenuItem("Unzip Dropbox File");
		mntmUnzipDropboxFile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Select a Zipfile to extract ...");
				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					ExtensionsToSkip skip = new ExtensionsToSkip();
					ZipBy7.unZipWholeFile(jfc.getSelectedFile(), jfc
							.getSelectedFile().getParentFile(), skip
							.getExtensions());
				}
			}
		});
		mnFile.add(mntmUnzipDropboxFile);

		JMenuItem mntmOrganizeDropboxFiles = new JMenuItem(
				"Organize Dropbox Files");
		mntmOrganizeDropboxFiles.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				File dropbox_dir;
				String studentListFile = "_student_list.obj";

				JFileChooser getPath = new JFileChooser();
				getPath.setDialogTitle("Select a directory to reorganize ...");
				getPath.setCurrentDirectory(new File("H:/_var/assign_raw/"));
				getPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if (getPath.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					dropbox_dir = getPath.getSelectedFile();
					ArrayList<D2LStudentSubmissionInfo> list = D2L
							.getStudentList(dropbox_dir);
					D2L.saveStudentList(new File(dropbox_dir, studentListFile),
							list);
					D2L.makeDirs(dropbox_dir, list);
					D2L.moveFiles(dropbox_dir, list);
					D2L.unZipAllStudentFiles(dropbox_dir, list);
				} // end of if the user selected a dir
			}
		});
		mnFile.add(mntmOrganizeDropboxFiles);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		mnFile.add(mntmExit);
		frame.getContentPane().setLayout(null);

		JLabel lblListOfExtensions = new JLabel(
				"List of extensions that will NOT be unzipped");
		lblListOfExtensions.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblListOfExtensions.setBounds(77, 11, 374, 27);
		frame.getContentPane().add(lblListOfExtensions);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(77, 36, 374, 188);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setRowMargin(4);
		table.setRowHeight(18);
		ExtensionsToSkip skip = new ExtensionsToSkip();

		table.setModel(new DefaultTableModel(skip.getExtensionsAsObjects(),
				new String[]
		{ "Extension", "Description" })
		{
			Class[] columnTypes = new Class[]
			{ String.class, String.class };

			public Class getColumnClass(int columnIndex)
			{
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(357);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("+");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tmpTM = (DefaultTableModel) getTable().getModel();
				tmpTM.insertRow(tmpTM.getRowCount(), new Object[]{".abc","New File Extension"});
			}
		});
		btnAdd.setBounds(10, 34, 52, 27);
		frame.getContentPane().add(btnAdd);
	}

	public JFrame getFrame()
	{
		return frame;
	}
	public JTable getTable() {
		return table;
	}
}
