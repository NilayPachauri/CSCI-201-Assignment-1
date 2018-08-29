
package pachauri_CSCI201L_Assignment1;

import java.util.List;
import com.google.gson.annotations.SerializedName;

/**
 * @author Nilay Pachauri
 *
 */
public class Calendar {

    @SerializedName("Users")
    private List<User> users = null;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
