package isr.ek0.bookingapi.web.admin;

import isr.ek0.bookingapi.AuthorizedUser;
import isr.ek0.bookingapi.model.Meal;
import isr.ek0.bookingapi.model.Restaurant;
import isr.ek0.bookingapi.service.RestaurantService;
import isr.ek0.bookingapi.web.user.RestaurantController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/admin/restaurants/{restaurantName}/meals")
public class AdminMealController {

    @Autowired
    private RestaurantService service;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> saveMeal(@Valid @RequestBody Meal meal, @PathVariable String restaurantName) {
        String loggedAdminEmail = AuthorizedUser.mail();
        log.info("admin {} is saving {} for restaurant {}", loggedAdminEmail, meal, restaurantName);
        Restaurant restaurant = service.saveMeal(loggedAdminEmail, restaurantName, meal);
        restaurant.add(linkTo(RestaurantController.class).slash(restaurant.getName()).withSelfRel(),
                linkTo(AdminRestaurantController.class).withRel("ownRestaurants"));
        return ResponseEntity.status(CREATED).body(restaurant);
    }

    @DeleteMapping(params = {"description"})
    public void deleteMeal(@PathVariable String restaurantName, @RequestParam String description) {
        String loggedAdminEmail = AuthorizedUser.mail();
        log.info("admin {} is deleting {} for restaurant {}", loggedAdminEmail, description, restaurantName);
        service.deleteMeal(loggedAdminEmail, restaurantName, description);
    }

    @DeleteMapping
    public void deleteMeals(@PathVariable String restaurantName) {
        String loggedAdminEmail = AuthorizedUser.mail();
        log.info("admin {} is deleting all meals for restaurant {}", loggedAdminEmail, restaurantName);
        service.deleteAllMeals(loggedAdminEmail, restaurantName);
    }
}
