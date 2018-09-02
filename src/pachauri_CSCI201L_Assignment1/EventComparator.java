/**
 * 
 */
package pachauri_CSCI201L_Assignment1;

import java.time.Month;
import java.util.Comparator;

/**
 * @author Nilay Pachauri
 *
 */
public class EventComparator implements Comparator<Event> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Event e1, Event e2) {
		if (e1.getDate().getYear() == e2.getDate().getYear())
			if (Month.valueOf(e1.getDate().getMonth()).compareTo(Month.valueOf(e2.getDate().getMonth())) == 0)
				return e1.getDate().getDay() - e2.getDate().getDay();
			else
				return Month.valueOf(e1.getDate().getMonth()).compareTo(Month.valueOf(e2.getDate().getMonth()));
		else
			return e2.getDate().getYear() - e2.getDate().getYear();
	}
}
