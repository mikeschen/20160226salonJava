import org.sql2o.*;
import java.util.List;

public class Stylist {
	private int stylist_id;
	private String stylistname;

	public Stylist (String stylistname) {
		this.stylistname = stylistname;
	}

	public int getId() {
    return stylist_id;
  }

  public String getStylistName() {
    return stylistname;
  }

	@Override
  public boolean equals(Object otherStylist) {
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getStylistName().equals(newStylist.getStylistName()) &&
        this.getId() == newStylist.getId();
    }
  }

  public static Stylist find(int id) {
  	try(Connection con = DB.sql2o.open()) {
	    String sql = "SELECT * FROM stylists where stylist_id=:id";
	    Stylist Stylist = con.createQuery(sql)
	      .addParameter("id", id)
	      .executeAndFetchFirst(Stylist.class);
    	return Stylist;
  	}
	}

	public List<Client> getClients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where stylistId=:id";
      return con.createQuery(sql)
        .addParameter("id", this.stylist_id)
        .executeAndFetch(Client.class);
    }
  }

  //CREATE
    public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (stylistname) VALUES (:stylistname)";
      this.stylist_id = (int) con.createQuery(sql, true)
        .addParameter("stylistname", this.stylistname)
        .executeUpdate()
        .getKey();
    	}
  	}

  //READ
    public static List<Stylist> all() {
    String sql = "SELECT stylist_id, stylistname FROM stylists ORDER by stylistname ASC";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  //UPDATE
  public void update(String newName) {
  this.stylistname = newName;
  String sql = "UPDATE stylists SET stylistname = :stylistname WHERE stylist_id = :id";
  try(Connection con = DB.sql2o.open()) {
    con.createQuery(sql)
      .addParameter("stylistname", newName)
      .addParameter("id", stylist_id)
      .executeUpdate();
    }
  }
  
  //DELETE
	  public static void delete(int id) {
	  String sql = "DELETE FROM stylists WHERE stylist_id= :id;";
	  try(Connection con = DB.sql2o.open()) {
	    con.createQuery(sql)
	    .addParameter("id", id)
	    .executeUpdate();
	  }
  }
}