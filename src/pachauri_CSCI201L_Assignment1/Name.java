
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
     * Name Constructor with No Parameter
     */
    public Name()	{
    	
    }
	/**
	 * Name Constructor with Parameters
	 * @param fname
	 * @param lname
	 */
	public Name(String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}
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
	/**
	 * Checks the equality between two name objects
	 * @param name Name object to compare
	 * @return the equality between all the parameter fields of the two names
	 */
	public boolean equals(Name name) {
		return this.getFname().equals(name.getFname()) && this.getLname().equals(name.getLname());
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return (this.lname + ", " + this.fname);
	}

}
