
package pachauri_CSCI201L_Assignment1;

import com.google.gson.annotations.SerializedName;

/**
 * @author Nilay Pachauri
 *
 */
public class Event {

    @SerializedName("Title")
    private String title;
    @SerializedName("Time")
    private String time;
    @SerializedName("Date")
    private Date date;
    
    /**
     * Event Constructor with No Parameters
     */
    public Event()	{
    	
    }
    /**
     * Event Constructor with Parameters
     * @param title the title of the event
     * @param time the time the event occurs
     * @param date the date the event occurs
     */
    public Event(String title, String time, Date date)	{
    	this.title = title;
    	this.time = time;
    	this.date = date;
    }
    
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return (title + ", " + time + ", " + date);
	}

    

}
