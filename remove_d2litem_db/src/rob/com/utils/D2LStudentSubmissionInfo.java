package rob.com.utils;

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
public class D2LStudentSubmissionInfo {
	private String firstName;
	private String lastName;
	private String firstLast;
	private long db;
	private long d2lItem;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		syncFirstLast();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		syncFirstLast();
	}

	public long getDb() {
		return db;
	}

	public void setDb(long db) {
		this.db = db;
	}

	public long getD2lItem() {
		return d2lItem;
	}

	public void setD2lItem(long d2lItem) {
		this.d2lItem = d2lItem;
	}

	private void syncFirstLast() {
		this.firstLast = this.firstName + "_" + this.lastName;
	}
//==============================================================================
// Method         : equals
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
//    --> Created OCT-22-2013 (rh)
//    --> Updated MMM-DD-YYYY (fl)
//
// =============================================================================
	boolean equals(String value) {
		boolean same = false;
		if (value.equals(this.firstLast)) {
			same = true;
		}
		return same;
	}
}
