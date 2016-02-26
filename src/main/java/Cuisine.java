import org.sql2o.*;
import java.util.List;

public class Cuisine {
  private int cuisine_id;
  private String type;

  public Cuisine (String type) {
    this.type = type;
  }

  public int getId() {
    return cuisine_id;
  }

  public String getType() {
    return type;
  }

  @Override
  public boolean equals(Object otherCuisine){
    if (!(otherCuisine instanceof Cuisine)) {
      return false;
    } else {
      Cuisine newCuisine = (Cuisine) otherCuisine;
      return this.getType().equals(newCuisine.getType()) &&
        this.getId() == newCuisine.getId();
    }
  }

  public static Cuisine find(int id) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM cuisines where cuisine_id=:id";
    Cuisine Cuisine = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Cuisine.class);
    return Cuisine;
  }
}

public List<Restaurant> getRestaurants() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurants where cuisineId=:id";
      return con.createQuery(sql)
        .addParameter("id", this.cuisine_id)
        .executeAndFetch(Restaurant.class);
    }
  }

  //CREATE
  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO cuisines (type) VALUES (:type)";
      this.cuisine_id = (int) con.createQuery(sql, true)
        .addParameter("type", this.type)
        .executeUpdate()
        .getKey();
    }
  }

  //READ
  public static List<Cuisine> all() {
    String sql = "SELECT cuisine_id, type FROM cuisines ORDER by type ASC";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Cuisine.class);
    }
  }

  //UPDATE
  public void update(String newType) {
    this.type = newType;
    try(Connection con = DB.sql2o.open()) {
      /******************************************************
        Students: TODO: Create sql query and execute update
      *******************************************************/
    }
  }
  //DELETE
  public static void delete(int id) {
    String sql = "DELETE FROM cuisines WHERE cuisine_id= :id;";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
