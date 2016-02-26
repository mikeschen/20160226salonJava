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

}