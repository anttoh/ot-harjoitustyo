package mazegame.domain;

import java.util.HashSet;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UserTest {

    @Test
    public void gettersWork() {
        User user = new User("username", "password");

        assertEquals(0, user.getId());
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());

        user = new User(12345, "username", "password");

        assertEquals(12345, user.getId());
    }

    @Test
    public void equalsAndHashCodeWork() {
        User user1 = new User("username", "password");
        User user2 = new User("username", "password");
        User user3 = new User("differentusername", "password");
        User user4 = new User("username", "differentpassword");

        assertEquals(true, user1.equals(user1));
        assertEquals(true, user1.equals(user2));
        assertEquals(false, user1.equals(null));
        assertEquals(false, user1.equals(user3));
        assertEquals(false, user1.equals(user4));
        assertEquals(false, user1.equals(new Object()));

        HashSet<User> set1 = new HashSet();
        set1.add(user1);
        set1.add(user3);
        HashSet<User> set2 = new HashSet();
        set2.add(user3);
        set2.add(user2);

        assertEquals(true, set1.hashCode() == set2.hashCode());
    }
}
