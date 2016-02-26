import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
  	assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Stylist myStylist = new Stylist("Floyd");
    myStylist.save();
    assertTrue(Stylist.all().get(0).equals(myStylist));
  }

  @Test
  public void find_findStylistsInDatabase_true() {
    Stylist myStylist = new Stylist("Floyd");
    myStylist.save();
    Stylist savedStylist = Stylist.find(myStylist.getId());
    assertTrue(myStylist.equals(savedStylist));
  }

  @Test
  public void getClients_retrievesAllClientsFromDatabase_clientlist() {
    Stylist myStylist = new Stylist("Floyd");
    myStylist.save();
    Client firstClient = new Client("Jill", myStylist.getId());
    firstClient.save();
    Client secondClient = new Client("John", myStylist.getId());
    secondClient.save();
    Client[] Clients = new Client[] { firstClient, secondClient };
    assertTrue(myStylist.getClients().containsAll(Arrays.asList(Clients)));
  }

	@Test
  public void delete_deletesClientFromDB_true() {
    Stylist myStylist = new Stylist("Floyd");
    myStylist.save();
    Client myClient = new Client("Jill", myStylist.getId());
    myClient.save();
    assertTrue(Client.all().contains(myClient));
    myClient.delete(myClient.getId());
    assertFalse(Client.all().contains(myClient));
  }

  @Test
  public void update_updateClientFromDB_true() {
    Stylist myStylist = new Stylist("Floyd");
    myStylist.save();
    Client myClient = new Client("Jill", myStylist.getId());
    myClient.save();
    myClient.update("Jane");
    assertEquals(myClient.getName(),"Jane");
  }
}