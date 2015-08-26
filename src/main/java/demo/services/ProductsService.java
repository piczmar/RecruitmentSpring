package demo.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class ProductsService {
    // local cache for lately added products, cleaned when all products from database are fetched
    private static List<Product> products = new ArrayList<>();

    @RequestMapping(name = "/products", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<Product> getProducts(@RequestParam(required = false) boolean onlyLast) {

        if (onlyLast) {
            System.out.println("getProducts?onlyLast=true " + Thread.currentThread().getName());
            return products;
        } else {
            System.out.println("getProducts " + Thread.currentThread().getName());
            // get products from database... this cost time
            try {
                Thread.sleep(1000 * 2);// wait 2 sec.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Product> productsFromDatabase = Collections.emptyList(); // normally it would be result of a query
            products.clear();
            return productsFromDatabase;
        }
    }

    @RequestMapping(name = "/products", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public
    @ResponseBody
    Product addProduct(@RequestBody Product product) {
        System.out.println("addProduct " + Thread.currentThread().getName());
        // .. add to database and then add to local cache..
        products.add(product);
        return product;
    }

    public static class Product {

        private String name;
        private BigDecimal price;

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
