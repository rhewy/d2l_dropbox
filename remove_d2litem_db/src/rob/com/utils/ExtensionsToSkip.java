package rob.com.utils;

//==============================================================================
// File         : ExtensionsToSkip.java
//
// Current Author: Robert Hewlett
//
// Previous Author: None
//
// Contact Info: rob.hewy@gmail.com
//
// Purpose : To create a default list of extensions to skip when unzipping
//
// Dependencies: None
//
// Modification Log :
//    --> Created NOV-02-2013 (rh)
//    --> Updated MMM-DD-YYYY (fl)
//
// =============================================================================
import java.util.ArrayList;

public class ExtensionsToSkip
{
	private ArrayList<FileExtension> extensions;

	public ExtensionsToSkip()
	{
		this.extensions  = new ArrayList<FileExtension>();
		
		FileExtension odb = new FileExtension();
		odb.setExtention(".odb");
		odb.setDescription("LibreOfice Base File");
		extensions.add(odb);

		FileExtension odt = new FileExtension();
		odt.setExtention(".odt");
		odt.setDescription("LibreOfice Writer File");
		extensions.add(odt);

		FileExtension odp = new FileExtension();
		odp.setExtention(".odp");
		odp.setDescription("LibreOfice Impress File");
		extensions.add(odp);

		FileExtension odg = new FileExtension();
		odg.setExtention(".odg");
		odg.setDescription("LibreOfice Drawing File");
		extensions.add(odg);

		FileExtension ods = new FileExtension();
		ods.setExtention(".ods");
		ods.setDescription("LibreOfice Calc File");
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
