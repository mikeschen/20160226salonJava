import org.sql2o.*;
import java.util.List;

public class Client {
	private int id;
	private String clientname;
	private int stylistId;

	public Client (String clientname, int stylistId) {
		this.clientname = clientname;
		this.stylistId = stylistId;
	}

	public int getId() {
    	return id;
	}

	public String getName() {
		return clientname;
	}

	public int getStylistId() {
		return stylistId;
	}

	public static Client find(int id){
	  try(Connection con = DB.sql2o.open()){
	  String sql = "SELECT * FROM clients where id=:id";
	  Client client = con.createQuery(sql)
	    .addParameter("id", id)
	    .executeAndFetchFirst(Client.class);
	  return client;
	  }
	}

	@Override
	public boolean equals(Object otherClient){
	    if (!(otherClient instanceof Client)) {
	    	return false;
	    } else {
	    	Client newClient = (Client) otherClient;
	    	return this.getName().equals(newClient.getName()) && this.getId() == newClient.getId() && this.getStylistId() == newClient.getStylistId();
	    }
	}

	//CREATE
	public void save() {
    try (Connection con = DB.sql2o.open()) {
  		String sql = "INSERT INTO Clients(clientname, stylistId) VALUES (:clientname, :stylistId)";
  		this.id = (int) con.createQuery(sql, true)
    	.addParameter("clientname", this.clientname)
    	.addParameter("stylistId", this.stylistId)
    	.executeUpdate()
    	.getKey();
    }
  }

	//READ
	public static List<Client> all() {
  	String sql = "SELECT id, clientname, stylistid FROM clients";
  	try(Connection con = DB.sql2o.open()) {
    	return con.createQuery(sql).executeAndFetch(Client.class);
   	}	
	}
	//UPDATE
  public void update(String newClientName) {
  this.clientname = newClientName;
  String sql = "UPDATE clients SET clientname = :clientname WHERE id = :id";
  try(Connection con = DB.sql2o.open()) {
    con.createQuery(sql)
      .addParameter("clientname", newClientName)
      .addParameter("id", id)
      .executeUpdate();
    }
	}

	//DELETE
  public static void delete(int id) {
    String sql = "DELETE FROM clients WHERE id= :id;";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
}
