/**
 * 
 */
package pachauri_CSCI201L_Assignment1;

import java.util.List;

/**
 * @author Nilay Pachauri
 *
 */
public class User {

	private Name name;
	private List <Event> events = null;
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
	public List <Event> getEvents() {
		return events;
	}
	/**
	 * @param events the events to set
	 */
	public void setEvents(List <Event> events) {
		this.events = events;
	}
}
