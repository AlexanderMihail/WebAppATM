import org.example.atm.Database;
import org.example.atm.User;
import org.junit.Assert;
import java.util.Base64;

import static org.junit.Assert.*;

public class DatabaseTest {
    String cardAndPin1 = "123456:0000";
    String cardAndPin2 = "234567:1111";
    String cardAndPin3 = "000000:0000";

    @org.junit.Test
    public void findUser() {
        String token1= Base64.getEncoder().encodeToString(cardAndPin1.getBytes());
        assertEquals("User must be John", Database.findUser(token1).name,"John");
        String token2= Base64.getEncoder().encodeToString(cardAndPin2.getBytes());
        assertEquals("User must be Jane", Database.findUser(token2).name,"Jane");
        String token3= Base64.getEncoder().encodeToString(cardAndPin3.getBytes());
        assertNotEquals("User should not exist", Database.findUser(token1), null);
    }

    @org.junit.Test
    public void alterBalance() {
        String token1= Base64.getEncoder().encodeToString(cardAndPin1.getBytes());
        int sum = 100;
        User user = Database.findUser(token1);
        user.adjustBalance(sum);
        assertEquals( "John's balance should be " + sum, sum, user.getBalance());
    }

    public static void main(String[] args)
    {
        DatabaseTest dbt = new DatabaseTest();
        dbt.findUser();
        dbt.alterBalance();
    }
}