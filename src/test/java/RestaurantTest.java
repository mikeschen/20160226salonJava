import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class RestaurantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAndCuisineIdAretheSame() {
    Restaurant firstRestaurant = new Restaurant("Killer Burger", 2);
    Restaurant secondRestaurant = new Restaurant("Killer Burger", 2);
    assertTrue(firstRestaurant.equals(secondRestaurant));
  }

  @Test
  public void save_returnTrueIfDescriptionsAretheSame() {
    Restaurant myRestaurant = new Restaurant("Killer Burger", 2);
    myRestaurant.save();
    assertTrue(Restaurant.all().get(0).equals(myRestaurant));
  }

  @Test
  public void save_assignsIdToObject() {
    Restaurant myRestaurant = new Restaurant("Killer Burger", 2);
    myRestaurant.save();
    Restaurant savedRestaurant = Restaurant.all().get(0);
    assertEquals(myRestaurant.getId(), savedRestaurant.getId());
  }

  @Test
  public void find_findsRestaurantinDatabase_true() {
    Restaurant myRestaurant = new Restaurant("Killer Burger", 1);
    myRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(myRestaurant.getId());
    assertTrue(myRestaurant.equals(savedRestaurant));
  }

  @Test
  public void save_savesCuisineIdIntoDB_true() {
    Cuisine myCuisine = new Cuisine("American");
    myCuisine.save();
    Restaurant myRestaurant = new Restaurant("Killer Burger", myCuisine.getId());
    myRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(myRestaurant.getId());
    assertEquals(savedRestaurant.getCuisineId(), myCuisine.getId());
  }

  @Test
  public void delete_deletesRestaurantFromDB_true() {
    Cuisine myCuisine = new Cuisine("Household chores");
    myCuisine.save();
    Restaurant myRestaurant = new Restaurant("Killer Burger", myCuisine.getId());
    myRestaurant.save();
    assertTrue(Restaurant.all().contains(myRestaurant));
    myRestaurant.delete(myRestaurant.getId());
    assertFalse(Restaurant.all().contains(myRestaurant));
  }

  @Test
  public void update_updateRestaurantFromDB_true() {
    Cuisine myCuisine = new Cuisine("Household chores");
    myCuisine.save();
    Restaurant myRestaurant = new Restaurant("Killer Burger", myCuisine.getId());
    myRestaurant.save();
    myRestaurant.update("Los Pollos");
    assertEquals(myRestaurant.getName(),"Los Pollos");
  }
}
