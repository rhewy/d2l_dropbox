package rob.com.utils;

import java.util.ArrayList;

public class ExtensionsToSkip
{
	private ArrayList<FileExtension> extensions;

	public ExtensionsToSkip()
	{
		this.extensions  = new ArrayList<FileExtension>();
		
		FileExtension odb = new FileExtension(".odb", "LibreOfice Base File");
		extensions.add(odb);

		FileExtension odt = new FileExtension(".odt","LibreOfice Writer File");
		extensions.add(odt);

		FileExtension odp = new FileExtension(".odp","LibreOfice Impress File");
		extensions.add(odp);

		FileExtension odg = new FileExtension(".odg","LibreOfice Drawing File");
		extensions.add(odg);

		FileExtension ods = new FileExtension(".ods","LibreOfice Calc File");
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
