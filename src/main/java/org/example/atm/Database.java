package org.example.atm;

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
        for (User user : users)
            if (card.equals(user.account.cardNumber) && user.pin.equals(pin))
                return user;
        return null;
    }
    public static int alterBalance(String token, int value)
    {
        return findUser(token).adjustBalance(value);
    }
}
