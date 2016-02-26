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
	public void equals_returnsTrueIfNamesAndCuisineIdAretheSame() {
		Client firstClient = new Client("Killer Burger", 2);
		Client secondClient = new Client("Killer Burger", 2);
    assertTrue(firstClient.equals(secondClient));
  }
}