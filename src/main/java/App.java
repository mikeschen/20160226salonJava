import java.util.Map;
import java.util.HashMap;
import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String type = request.queryParams("type");
      Cuisine newCuisine = new Cuisine(type);
      newCuisine.save();
      model.put("cuisines", Cuisine.all());
      model.put("cuisine", newCuisine);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cuisines/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("cuisine", Cuisine.find(Integer.parseInt(request.params(":id"))));
      model.put("restaurants", Restaurant.find(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/cuisines.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/cuisines", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      int cuisineId = Integer.parseInt(request.queryParams("cuisineId"));
      Restaurant newRestaurant = new Restaurant(name, cuisineId);
      newRestaurant.save();
      model.put("cuisine", Cuisine.find(cuisineId));
      model.put("restaurant", newRestaurant);
      model.put("template", "templates/cuisines.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/delete/cuisine/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.queryParams("cuisineId"));
      Cuisine.delete(id);
      model.put("cuisines", Cuisine.all());
      // model.put("restaurants", Restaurant.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/delete/restaurant/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int cuisineId = Integer.parseInt(request.queryParams("cuisineId"));
      int id = Integer.parseInt(request.params(":id"));
      Restaurant.delete(id);
      model.put("cuisine", Cuisine.find(cuisineId));
      model.put("cuisines", Cuisine.all());
      model.put("restaurants", Restaurant.all());
      model.put("template", "templates/cuisines.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/update/restaurant/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int restaurantId = Integer.parseInt(request.queryParams("restaurantId"));
      Restaurant currentRestaurant = Restaurant.find(restaurantId);
      model.put("restaurant", currentRestaurant);
      model.put("template", "templates/restaurant-update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/update/restaurant/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int restaurantId = Integer.parseInt(request.queryParams("restaurantId"));
      String restaurantName = request.queryParams("restaurantName");
      Restaurant myRestaurant = Restaurant.find(restaurantId);
      myRestaurant.update(restaurantName);
      model.put("cuisines", Cuisine.all());
      model.put("restaurants", Restaurant.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
