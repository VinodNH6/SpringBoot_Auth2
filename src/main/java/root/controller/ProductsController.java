package root.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class ProductsController 
{
   @RequestMapping(value = "/products")
   public String getProductName() {
      return "Hello from Vin!!";   
   }
}