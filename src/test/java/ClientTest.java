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

  @Test
  public void save_savesStylistIdIntoDB_true() {
    Stylist myStylist = new Stylist("Floyd");
    myStylist.save();
    Client myClient = new Client("Jill", myStylist.getId());
    myClient.save();
    Client savedClient = Client.find(myClient.getId());
    assertEquals(savedClient.getStylistId(), myStylist.getId());
  }

  @Test
  public void delete_deletesStylistFromDB_true() {
    Stylist myStylist = new Stylist("Floyd");
    myStylist.save();
    assertTrue(Stylist.all().contains(myStylist));
    myStylist.delete(myStylist.getId());
    assertFalse(Stylist.all().contains(myStylist));
  }

  @Test
  public void update_updateStylistFromDB_true() {
    Stylist myStylist = new Stylist("Floyd");
    myStylist.save();
    myStylist.update("Lloyd");
    assertEquals(myStylist.getStylistName(),"Lloyd");
  }
}