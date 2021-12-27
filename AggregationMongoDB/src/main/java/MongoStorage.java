import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Field;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.eq;

public class MongoStorage {

  private static MongoCollection<Document> listShop;
  private static MongoCollection<Document> listProduct;
  private static MongoDatabase database;

  public void init() {
    MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
    database = mongoClient.getDatabase("StoreManagement");

    listShop = database.getCollection("Shops");
    listProduct = database.getCollection("Products");
  }

  public void addShop(String nameShop) {
    Document shop = new Document();
    shop.put("name", nameShop);
    shop.put("Products", new ArrayList<String>());
    listShop.insertOne(shop);
    System.out.println("Shop " + nameShop + " has been added in list");
  }

  public void addProduct(String nameProduct, String priceProduct) {
    Document product = new Document();
    product.put("name", nameProduct);
    int price = Integer.parseInt(priceProduct);
    product.put("price", price);

    listProduct.insertOne(product);
    System.out.println("Product " + nameProduct + " " + priceProduct + " has been added in list");
  }

  public void addProductInShop(String nameProduct, String nameShop) {
    Document update = new Document("Products", nameProduct);
    Document push = new Document("$push", update);
    listShop.findOneAndUpdate(eq("name", nameShop), push);
  }

  public void showStatisticShop() {

    List<Bson> params =
        Arrays.asList(
            lookup("Products", "Products", "name", "Result"),
            addFields(
                new Field(
                    "MaxPricedGood",
                    Document.parse(
                        "{$filter: {input: '$Result', as: 'res', cond: {'$eq' : ['$$res.price', {'$max': '$Result.price'}]}}}")),
                new Field(
                    "MinPricedGood",
                    Document.parse(
                        "{$filter: {input: '$Result', as: 'res', cond: {'$eq' : ['$$res.price', {'$min': '$Result.price'}]}}}")),
                new Field(
                    "CheapGoods",
                    Document.parse(
                        "{$filter: {input: '$Result', as: 'res', cond: {'$lt' : ['$$res.price', 100]}}}"))),
            project(
                new Document()
                    .append("Name", "$name")
                    .append("Count", new Document("$size", "$Products"))
                    .append("AvgPrice", new Document("$avg", "$Result.price"))
                    .append("MaxPricedGoodName", new Document("$first", "$MaxPricedGood.name"))
                    .append("MinPricedGoodName", new Document("$first", "$MinPricedGood.name"))
                    .append("CheapGoods", new Document("$size", "$CheapGoods"))));

    for (Document doc : listShop.aggregate(params)) {
      System.out.printf("Магазин %s:\n", doc.getString("Name"));
      System.out.printf("%d единицы товаров\n", doc.getInteger("Count"));
      System.out.printf("Средняя цена товара - %.2f рублей\n", doc.getDouble("AvgPrice"));
      System.out.printf("Самый дорогой товар - %s\n", doc.getString("MaxPricedGoodName"));
      System.out.printf("Самый дешёвый товар - %s\n", doc.getString("MinPricedGoodName"));
      System.out.printf(
          "Количество товаров дешевле 100 рублей - %d\n\n", doc.getInteger("CheapGoods"));
    }
  }
}
