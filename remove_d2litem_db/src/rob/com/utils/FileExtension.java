package rob.com.utils;
//==============================================================================
//File         : FileExtensions.java
//
//Current Author: Robert Hewlett
//
//Previous Author: None
//
//Contact Info: rob.hewy@gmail.com
//
//Purpose : Store file extension information
//
//Dependencies: None
//
//Modification Log :
// --> Created NOV-02-2013 (rh)
// --> Updated MMM-DD-YYYY (fl)
//
//=============================================================================
import java.io.Serializable;

public class FileExtension implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String extention;
	private String description;
	public FileExtension(String ext, String desc)
	{
	 this.extention = ext;
	 this.description = desc;
	}
	public FileExtension(Object ext, Object desc)
	{
	 this.extention = ext.toString();
	 this.description = desc.toString();
	}
	public String getExtention()
	{
		return extention;
	}
	public void setExtention(String extention)
	{
		this.extention = extention;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
}
