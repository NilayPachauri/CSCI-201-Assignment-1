
package pachauri_CSCI201L_Assignment1;

import com.google.gson.annotations.SerializedName;

/**
 * @author Nilay Pachauri
 *
 */
public class Date {

    @SerializedName("Month")
    private String month;
    @SerializedName("Day")
    private Integer day;
    @SerializedName("Year")
    private Integer year;
    
    /**
     * Date Constructor with No Parameters
     */
    public Date()	{
    	
    }
	/**
	 * Date Constructor with Parameters
	 * @param month the month
	 * @param day the day
	 * @param year the year
	 */
	public Date(String month, Integer day, Integer year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the day
	 */
	public Integer getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(Integer day) {
		this.day = day;
	}
	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return (month + " " + day + ", " + year);
	}
}
