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
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String stylistname = request.queryParams("stylistname");
      Stylist newStylist = new Stylist(stylistname);
      newStylist.save();
      model.put("stylists", Stylist.all());
      model.put("stylist", newStylist);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("stylist", Stylist.find(Integer.parseInt(request.params(":id"))));
      model.put("clients", Client.find(Integer.parseInt(request.params(":id"))));
      model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylists", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String clientname = request.queryParams("clientname");
      int stylistId = Integer.parseInt(request.queryParams("stylistId"));
      Client newClient = new Client(clientname, stylistId);
      newClient.save();
      model.put("stylist", Stylist.find(stylistId));
      model.put("client", newClient);
      model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/delete/stylist/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.queryParams("stylistId"));
      Stylist.delete(id);
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/delete/client/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int stylistId = Integer.parseInt(request.queryParams("stylistId"));
      int id = Integer.parseInt(request.params(":id"));
      Client.delete(id);
      model.put("stylist", Stylist.find(stylistId));
      model.put("stylists", Stylist.all());
      model.put("clients", Client.all());
      model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/update/client/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int clientId = Integer.parseInt(request.queryParams("clientId"));
      Client currentClient = Client.find(clientId);
      model.put("client", currentClient);
      model.put("template", "templates/client-update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/update/client/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int clientId = Integer.parseInt(request.queryParams("clientId"));
      String clientName = request.queryParams("clientName");
      Client myClient = Client.find(clientId);
      myClient.update(clientName);
      model.put("stylists", Stylist.all());
      model.put("clients", Client.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/update/stylist/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int stylistId = Integer.parseInt(request.queryParams("stylistId"));
      Stylist currentStylist = Stylist.find(stylistId);
      model.put("stylist", currentStylist);
      model.put("template", "templates/stylist-update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/update/stylist/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int stylistId = Integer.parseInt(request.queryParams("stylistId"));
      String stylistName = request.queryParams("stylistName");
      Stylist myStylist = Stylist.find(stylistId);
      myStylist.update(stylistName);
      model.put("stylists", Stylist.all());
      // model.put("clients", Client.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
