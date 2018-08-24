
package pachauri_CSCI201L_Assignment1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Nilay Pachauri
 *
 */
/**
 * @author Nilay Pachauri
 *
 */
public class Date {

    @SerializedName("Month")
    @Expose
    private String month;
    @SerializedName("Day")
    @Expose
    private Integer day;
    @SerializedName("Year")
    @Expose
    private Integer year;
    
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
    

}
