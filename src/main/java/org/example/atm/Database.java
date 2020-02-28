package org.example.atm;

import java.util.Arrays;
import java.util.Base64;

public class Database {
    static Account[] accounts = new Account[]{
        new Account("123456"),
        new Account("234567"),
    };
    static User[] users = new User[]{
        new User("John", accounts[0], "0000"),
        new User("Jane", accounts[1], "1111"),
    };
    public static User findUser(String token) {
        String[] decodedStrings = new String(Base64.getDecoder().decode(token)).split(":");
        String card = decodedStrings[0];
        String pin = decodedStrings[1];
        return Arrays.stream(users).filter(x -> card.equals(x.account.cardNumber) && pin.equals(x.pin)).findFirst().orElse(null);
    }
    public static int alterBalance(String token, int value)
    {
        return findUser(token).adjustBalance(value);
    }
}
