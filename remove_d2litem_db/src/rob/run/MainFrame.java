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
import rob.com.utils.ExtensionsTableModel;
import rob.com.utils.ExtensionsToSkip;
import rob.com.utils.FileExtension;
import rob.com.utils.ZipBy7;
import javax.swing.JButton;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class MainFrame
{

	private JFrame frmDlDropboxManager;
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
					window.frmDlDropboxManager.setVisible(true);
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
		frmDlDropboxManager = new JFrame();
		frmDlDropboxManager
				.setTitle("D2L Dropbox Manager by rob.hewy@gmail.com");
		frmDlDropboxManager.setBounds(100, 100, 520, 318);
		frmDlDropboxManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmDlDropboxManager.setJMenuBar(menuBar);

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
				jfc.setCurrentDirectory(new File("H:/_var/assign_raw/"));
				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					ArrayList<FileExtension> exts = ((ExtensionsTableModel) table
							.getModel()).getDataAsFileExtensions();
					ZipBy7.unZipWholeFile(jfc.getSelectedFile(), jfc
							.getSelectedFile().getParentFile(), exts);
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

		JMenuItem mntmUnzipAndOrganize = new JMenuItem(
				"Unzip and Organize a Dropbox");
		mntmUnzipAndOrganize.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				File dropbox_dir;
				String studentListFile = "_student_list.obj";

				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Select a Zipfile to extract ...");
				jfc.setCurrentDirectory(new File("H:/_var/assign_raw/"));
				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					ArrayList<FileExtension> exts = ((ExtensionsTableModel) table
							.getModel()).getDataAsFileExtensions();
					ZipBy7.unZipWholeFile(jfc.getSelectedFile(), jfc
							.getSelectedFile().getParentFile(), exts);

					dropbox_dir = jfc.getSelectedFile().getParentFile();
					ArrayList<D2LStudentSubmissionInfo> list = D2L
							.getStudentList(dropbox_dir);
					D2L.saveStudentList(new File(dropbox_dir, studentListFile),
							list);
					D2L.makeDirs(dropbox_dir, list);
					D2L.moveFiles(dropbox_dir, list);
					D2L.unZipAllStudentFiles(dropbox_dir, list);

				}
			}
		});
		mnFile.add(mntmUnzipAndOrganize);

		JMenuItem mntmSaveTheExtensions = new JMenuItem(
				"Save the Extensions List");
		mntmSaveTheExtensions.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Serializer serializer = new Persister();
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Save the Extensions List as XML ...");
				jfc.setCurrentDirectory(new File("H:/_var/assign_raw/"));
				if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
				{
//					
//					File result = jfc.getSelectedFile();
//					try
//					{
//						ExtensionsTableModel extTab = (ExtensionsTableModel) table.getModel();
//						ExtensionsToSkip extensions = new ExtensionsToSkip(extTab.getDataAsFileExtensions());
//						serializer.write(extensions, result);
//					}
//					catch (Exception e1)
//					{
//						e1.printStackTrace();
//					}
				}

			}
		});
		mnFile.add(mntmSaveTheExtensions);

		JMenuItem mntmLoadAnExtensions = new JMenuItem(
				"Load an Extensions File");
		mntmLoadAnExtensions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Serializer serializer = new Persister();
//				JFileChooser jfc = new JFileChooser();
//				jfc.setDialogTitle("Load Extensions in an XML ...");
//				jfc.setCurrentDirectory(new File("H:/_var/assign_raw/"));
//				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
//				{
//					File source = jfc.getSelectedFile();
//					try
//					{
//						ExtensionsToSkip extensions =  serializer.read(ExtensionsToSkip.class, source);
//						ExtensionsTableModel extTab = (ExtensionsTableModel) table.getModel();
//						
//						extTab.setDataAsFileExtensions(extensions.getExtensions());
//					}
//					catch (Exception e1)
//					{
//						e1.printStackTrace();
//					}
//				}

			}
		});
		mnFile.add(mntmLoadAnExtensions);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		mnFile.add(mntmExit);
		frmDlDropboxManager.getContentPane().setLayout(null);

		JLabel lblListOfExtensions = new JLabel(
				"List of extensions that will NOT be unzipped");
		lblListOfExtensions.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblListOfExtensions.setBounds(77, 11, 374, 27);
		frmDlDropboxManager.getContentPane().add(lblListOfExtensions);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(77, 36, 374, 188);
		frmDlDropboxManager.getContentPane().add(scrollPane);

		table = new JTable();
		table.setRowMargin(4);
		table.setRowHeight(18);
		ExtensionsToSkip skip = new ExtensionsToSkip();
		skip.loadDefault();
		
		ExtensionsTableModel tabMD = new ExtensionsTableModel(
				skip.getExtensions());
		table.setModel(tabMD);
		table.getColumnModel().getColumn(1).setPreferredWidth(357);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(table);

		JButton btnAdd = new JButton("+");
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				DefaultTableModel tmpTM = (DefaultTableModel) getTable()
						.getModel();
				Object[] row = new Object[]
				{ ".abc", "New File Extension" };

				tmpTM.insertRow(tmpTM.getRowCount(), row);

			}
		});
		btnAdd.setBounds(10, 34, 52, 27);
		frmDlDropboxManager.getContentPane().add(btnAdd);
	}

	public JFrame getFrame()
	{
		return frmDlDropboxManager;
	}

	public JTable getTable()
	{
		return table;
	}
}
