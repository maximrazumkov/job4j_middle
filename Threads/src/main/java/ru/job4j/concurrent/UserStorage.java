package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Map;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> users;

    public UserStorage(Map<Integer, User> users) {
        this.users = users;
    }

    public synchronized boolean add(User user) {
        boolean result = false;
        if (!users.containsKey(user.id)) {
            users.put(user.id, user);
            result = true;
        }
        return result;
    }

    public synchronized boolean update(User user) {
        boolean result = false;
        if (users.containsKey(user.id)) {
            users.put(user.id, user);
            result = true;
        }
        return result;
    }

    public synchronized boolean delete(User user) {
        boolean result = false;
        if (users.containsKey(user.id)) {
            users.remove(user.id, user);
            result = true;
        }
        return result;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        if (users.containsKey(fromId) && users.containsKey(toId)) {
            User fromUser = users.get(fromId);
            User toUser = users.get(fromId);
            if (fromUser.amount >= amount) {
                fromUser.amount -= amount;
                toUser.amount += amount;
            }
        }
        return result;
    }
}
