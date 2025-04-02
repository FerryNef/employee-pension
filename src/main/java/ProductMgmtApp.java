import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ProductMgmtApp {

    public static void main(String[] args) throws Exception {
        Product[] products = new Product[] {
                new Product(3128874119L, "Banana", LocalDate.parse("2023-01-24"), 124, 0.55),
                new Product(2927458265L, "Apple", LocalDate.parse("2022-12-09"), 18, 1.09),
                new Product(9189927460L, "Carrot", LocalDate.parse("2023-03-31"), 89, 2.99)
        };

        printProducts(products);
    }

    public static void printProducts(Product[] products) throws Exception {
        List<Product> sorted = Arrays.stream(products)
                .sorted(Comparator.comparing(Product::getUnitPrice).reversed())
                .collect(Collectors.toList());

        System.out.println("JSON Output:");
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(sorted));

        System.out.println("\nXML Output:");
        System.out.println(new XmlMapper().writerWithDefaultPrettyPrinter().writeValueAsString(sorted));

        System.out.println("\nCSV Output:");
        System.out.println("productId,name,dateSupplied,quantityInStock,unitPrice");
        for (Product p : sorted) {
            System.out.printf("%d,%s,%s,%d,%.2f\n", p.getProductId(), p.getName(), p.getDateSupplied(), p.getQuantityInStock(), p.getUnitPrice());
        }
    }
}
