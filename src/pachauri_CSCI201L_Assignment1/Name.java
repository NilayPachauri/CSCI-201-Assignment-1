
package pachauri_CSCI201L_Assignment1;

import com.google.gson.annotations.SerializedName;

/**
 * @author Nilay Pachauri
 *
 */
public class Name {

    @SerializedName("Fname")
    private String fname;
    @SerializedName("Lname")
    private String lname;
	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}
	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}
	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return (this.lname + ", " + this.fname);
	}

}
