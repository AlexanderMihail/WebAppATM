package org.example.atm;

import java.util.Base64;
import java.util.Set;

public class User {
    public String name;
    public Account account;
    String pin;
    User(String name, Account account, String pin) { this.name = name; this.account = account; this.pin = pin; }
    public int getBalance() { return account.getBalance(); }
    public int adjustBalance(int delta) { account.setBalance( account.getBalance() + delta); return account.getBalance(); }
}
