
package pachauri_CSCI201L_Assignment1;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Nilay Pachauri
 *
 */
public class User {

    @SerializedName("Name")
    @Expose
    private Name name;
    @SerializedName("Events")
    @Expose
    private List<Event> events = null;
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
