package org.ad.engine.services;

import org.ad.engine.models.User;
import org.ad.engine.models.enums.Gender;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {

    private final Map<String, User> userMap = new ConcurrentHashMap<>();

    public String addUser(String name, Gender gender, Integer age, String city) {
        String userId = IdGenerator.generate();
        User user = new User(userId, name, age, gender, city);
        userMap.put(userId, user);
        return userId;
    }

    public void updateUserAttributes(User updateRequest) {
        if (updateRequest.getId() == null) {
            throw new IllegalArgumentException("User id cannot be null in updateUser request");
        }
        validateUserId(updateRequest.getId());

        User user = userMap.get(updateRequest.getId());
        updateAge(updateRequest, user);
        updateCity(updateRequest, user);

    }

    public User getUserById(String userId) {
        validateUserId(userId);
        return userMap.get(userId);
    }

    private void validateUserId(String userId) {
        if (!userMap.containsKey(userId)) {
            throw new IllegalArgumentException("user not exists");
        }
    }

    private void updateAge(User updateRequest, User user) {
        if (user.getAge() != null) {
            user.setAge(updateRequest.getAge());
        }
    }

    private void updateCity(User updateRequest, User user) {
        if (user.getCity() != null) {
            user.setCity(updateRequest.getCity());
        }
    }

}
