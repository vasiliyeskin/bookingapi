package isr.ek0.orderapi.service;

import isr.ek0.orderapi.model.Restaurant;
import isr.ek0.orderapi.model.User;

public interface UserService {
    User get(String email);

    void addRestaurant(String loggedUserEmail, Restaurant restaurant);
}
