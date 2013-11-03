package rob.com.utils;
//==============================================================================
//File         : ExtensionsToSkip.java
//
//Current Author: Robert Hewlett
//
//Previous Author: None
//
//Contact Info: rob.hewy@gmail.com
//
//Purpose : Helper class to store a list of FileExtension and convert
//          the list to a list of Object e.g. JTable friendly. 
//          Serializable as XML using Simple from IBM
//
//Dependencies: org.simpleframework.xml
//
//Modification Log :
//--> Created NOV-02-2013 (rh)
//--> Updated MMM-DD-YYYY (fl)
//
//=============================================================================
import java.util.ArrayList;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class ExtensionsToSkip
{
	@ElementList
	private ArrayList<FileExtension> extensions;

	public ExtensionsToSkip(ArrayList<FileExtension> list)
	{
		this.extensions = list;
	}

	public ExtensionsToSkip()
	{
		this.extensions = new ArrayList<FileExtension>();

	}

	public void loadDefault()
	{
		FileExtension odb = new FileExtension(".odb", "LibreOfice Base File");
		extensions.add(odb);

		FileExtension odt = new FileExtension(".odt", "LibreOfice Writer File");
		extensions.add(odt);

		FileExtension odp = new FileExtension(".odp", "LibreOfice Impress File");
		extensions.add(odp);

		FileExtension odg = new FileExtension(".odg", "LibreOfice Drawing File");
		extensions.add(odg);

		FileExtension ods = new FileExtension(".ods", "LibreOfice Calc File");
		extensions.add(ods);
	}

	public ArrayList<FileExtension> getExtensions()
	{
		return extensions;
	}

	public void setExtensions(ArrayList<FileExtension> extensions)
	{
		this.extensions = extensions;
	}

	public Object[][] getExtensionsAsObjects()
	{
		Object[][] extAsObj = new Object[this.extensions.size()][2];
		FileExtension ext;

		for (int i = 0; i < extensions.size(); i++)
		{
			ext = this.extensions.get(i);

			extAsObj[i][0] = ext.getExtention();
			extAsObj[i][1] = ext.getDescription();

		}

		return extAsObj;
	}

}
