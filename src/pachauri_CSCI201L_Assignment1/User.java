
package pachauri_CSCI201L_Assignment1;

import java.util.List;
import com.google.gson.annotations.SerializedName;

/**
 * @author Nilay Pachauri
 *
 */
public class User {

    @SerializedName("Name")
    private Name name;
    @SerializedName("Events")
    private List<Event> events = null;
    /**
     * User Constructor with No Parameters
     */
    public User()	{
    	
    }
	/**
	 * User Constructor with Parameters
	 * @param name
	 * @param events
	 */
	public User(Name name, List<Event> events) {
		super();
		this.name = name;
		this.events = events;
	}
	/**
	 * @return the name
	 */
	public Name getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(Name name) {
		this.name = name;
	}
	/**
	 * @return the events
	 */
	public List<Event> getEvents() {
		return events;
	}
	/**
	 * @param events the events to set
	 */
	public void setEvents(List<Event> events) {
		this.events = events;
	}
}
