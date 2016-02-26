import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class CuisineTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Cuisine.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Cuisine firstCuisine = new Cuisine("American");
    Cuisine secondCuisine = new Cuisine("American");
    assertTrue(firstCuisine.equals(secondCuisine));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Cuisine myCuisine = new Cuisine("Household chores");
    myCuisine.save();
    assertTrue(Cuisine.all().get(0).equals(myCuisine));
  }

  @Test
  public void find_findCuisineInDatabase_true() {
    Cuisine myCuisine = new Cuisine("Household chores");
    myCuisine.save();
    Cuisine savedCuisine = Cuisine.find(myCuisine.getId());
    assertTrue(myCuisine.equals(savedCuisine));
  }

  @Test
  public void getRestaurants_retrievesAllRestaurantsFromDatabase_restaurantslist() {
    Cuisine myCuisine = new Cuisine("American");
    myCuisine.save();
    Restaurant firstRestaurant = new Restaurant("Killer Burger", myCuisine.getId());
    firstRestaurant.save();
    Restaurant secondRestaurant = new Restaurant("Los Pollos", myCuisine.getId());
    secondRestaurant.save();
    Restaurant[] restaurants = new Restaurant[] { firstRestaurant, secondRestaurant };
    assertTrue(myCuisine.getRestaurants().containsAll(Arrays.asList(restaurants)));
  }
}
