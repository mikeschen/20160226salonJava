import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

	@Rule
	public DatabaseRule database = new DatabaseRule();

	@Test
	public void all_emptyAtFirst() {
	assertEquals(Client.all().size(), 0);
	}

	@Test
	public void equals_returnsTrueIfNamesAndStylistIdAretheSame() {
		Client firstClient = new Client("Jill", 2);
		Client secondClient = new Client("Jill", 2);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_returnTrueIfNamesAretheSame() {
    Client myClient = new Client("Jill", 2);
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

  @Test
  public void save_assignsIdToObject() {
    Client myClient = new Client("Jill", 2);
    myClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(myClient.getId(), savedClient.getId());
  }

  @Test
  public void find_findsClientinDatabase_true() {
    Client myClient = new Client("Jill", 1);
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertTrue(myClient.equals(savedClient));
  }

  // @Test
  // public void save_savesCuisineIdIntoDB_true() {
  //   Cuisine myCuisine = new Cuisine("American");
  //   myCuisine.save();
  //   Client myClient = new Client("Killer Burger", myCuisine.getId());
  //   myClient.save();
  //   Client savedClient = Client.find(myClient.getId());
  //   assertEquals(savedClient.getCuisineId(), myCuisine.getId());
  // }
}