package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Isa", "Isa", "isa@mail.ru", new Car("Mazda",2)));
      userService.add(new User("Cusco", "Custo", "cust@mail.ru", new Car("Toyota",1)));
      userService.add(new User("Warto", "Warto", "warto@mail.ru", new Car("Hyundai",45)));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar().getModel() + " " + user.getCar().getSeries());
         System.out.println();
      }

      System.out.println("\n------------------\n\n");
      User myUser = userService.getUserByCar("Mazda", 2);
      System.out.println(myUser.getFirstName() + " " + myUser.getLastName());

      context.close();
   }
}
