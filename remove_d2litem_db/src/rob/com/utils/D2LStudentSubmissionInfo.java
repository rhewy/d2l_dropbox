package rob.com.utils;

import java.io.Serializable;

// ==============================================================================
// Method : D2LStudentSubmissionInfo.java
//
// Current Author: Robert Hewlett
//
// Previous Author: None
//
// Contact Info: rob.hewy@gmail.com
//
// Purpose : Structure like class for storing the students name, d2l id (D2LItem)
//           and the dropbox id (DB)
//
// Dependencies: None
//
// Modification Log :
// --> Created OCT-22-2013 (rh)
// --> Updated MMM-DD-YYYY (fl)
//
// =============================================================================
public class D2LStudentSubmissionInfo implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String firstLast;
	private long db;
	private long d2lItem;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
		syncFirstLast();
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
		syncFirstLast();
	}

	public long getDb()
	{
		return db;
	}

	public void setDb(long db)
	{
		this.db = db;
	}

	public long getD2lItem()
	{
		return d2lItem;
	}

	public void setD2lItem(long d2lItem)
	{
		this.d2lItem = d2lItem;
	}

	public String getFirstLast()
	{
		return firstLast;
	}
	public String getFirstLastStudID()
	{
		return firstLast + "_" + this.d2lItem;
	}
	private void syncFirstLast()
	{
		this.firstLast = this.firstName + "_" + this.lastName;
	}

	// ==============================================================================
	// Method : equals
	//
	// Current Author: Robert Hewlett
	//
	// Previous Author: None
	//
	// Contact Info: rob.hewy@gmail.com
	//
	// Purpose : Help the arrayList determine contains
	//
	// Dependencies: None
	//
	// Modification Log :
	// --> Created OCT-22-2013 (rh)
	// --> Updated MMM-DD-YYYY (fl)
	//
	// =============================================================================
	@Override
	public boolean equals(Object value)
	{
		boolean same = false;
		// ===========================================================
		// use the d2litem (internal student id) to determine uniqueness
		// ===========================================================
		try
		{
			D2LStudentSubmissionInfo tmp = (D2LStudentSubmissionInfo) value;
			if (this.d2lItem == tmp.d2lItem)
			{
				same = true;
			}
		}
		catch (Exception e)
		{
		}
		return same;
	}

	// ===============================================================================================
	// This is the old equals based on first and last name
	// ===============================================================================================
	// @Override
	// public boolean equals(Object value) {
	// boolean same = false;
	//
	// try {
	// D2LStudentSubmissionInfo tmp = (D2LStudentSubmissionInfo) value;
	// if (this.getFirstLast().equals(tmp.getFirstLast())) {
	// same = true;
	// }
	// } catch (Exception e) {
	// }
	// return same;
	// }
	// ===============================================================================================
}
