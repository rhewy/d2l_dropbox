package rob.com.utils;
//==============================================================================
//File         : ExtensionsTableModel.java
//
//Current Author: Robert Hewlett
//
//Previous Author: None
//
//Contact Info: rob.hewy@gmail.com
//
//Purpose : Wrapper class --> add a little to the default table model
//          So that we can add a list and retrieve a list of FileExtensions
//
//Dependencies: org.simpleframework.xml
//
//Modification Log :
//--> Created NOV-02-2013 (rh)
//--> Updated MMM-DD-YYYY (fl)
//
//=============================================================================
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class ExtensionsTableModel extends DefaultTableModel
{
	private static final long serialVersionUID = 1L;

	public ExtensionsTableModel(ArrayList<FileExtension> list)
	{
		this.setColumnIdentifiers(new Object[]
		{ "Extension", "Description" });

		for (FileExtension ext : list)
		{
			this.insertRow(this.getRowCount(), new Object[]
			{ ext.getExtention(), ext.getDescription() });
		}
	}

	public ArrayList<FileExtension> getDataAsFileExtensions()
	{
		// ===========================================================
		// convert the list of objects to a list of FileExtensio
		// ===========================================================
		ArrayList<FileExtension> list = new ArrayList<FileExtension>();
		FileExtension tmpExt;
		for (int i = 0; i < this.getRowCount(); i++)
		{
			tmpExt = new FileExtension(this.getValueAt(i, 0), this.getValueAt(
					i, 1));
			list.add(tmpExt);
		}

		return list;
	}

	public void setDataAsFileExtensions(ArrayList<FileExtension> list)
	{
		// ===========================================================
		// clear all the existing rows
		// ===========================================================
		this.setRowCount(0);
		// ===========================================================
		// convert the list of FileExtensions to a list of objects
		// ===========================================================		
		FileExtension tmpExt;
		for (int i = 0; i < list.size(); i++)
		{
			tmpExt = list.get(i);
			this.addRow(new Object[]
			{ tmpExt.getExtention(), tmpExt.getDescription() });
		}

	}
}
