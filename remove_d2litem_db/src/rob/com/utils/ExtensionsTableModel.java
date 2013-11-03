package rob.com.utils;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class ExtensionsTableModel extends DefaultTableModel
{
	private static final long serialVersionUID = 1L;

	public ExtensionsTableModel(ArrayList<FileExtension> list)
	{
		this.setColumnIdentifiers(new Object[]
		{ String.class, String.class });
		
		for (FileExtension ext : list)
		{
			this.insertRow(this.getRowCount(), 
					new Object[] {ext.getExtention(), ext.getDescription()});
		}
	}

	public ArrayList<FileExtension> getDataAsFileExtensions()
	{
		ArrayList<FileExtension> list = new ArrayList<FileExtension>();
		FileExtension tmpExt;
		for (int i = 0; i < this.getRowCount(); i++)
		{
			tmpExt = new FileExtension(this.getValueAt(i, 0), this.getValueAt(i, 1));
			list.add(tmpExt);
		}

		return list;
	}

}
