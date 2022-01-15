
/**
 * Автор: https://github.com/VlasovM
 * В данной задаче сделаем более сложные запросы нереляционной базы данных MongoDB. А именно - агрегацию.
 * Более подробная информация по решению задачки:
 * https://vk.com/@javlasov-agregaciya-dannyh-mondodb
 */

public class AggregationMongoDB {

    public static void main(String[] args) {
        MongoStorage mongo = new MongoStorage();
        mongo.init();
        System.out.println("COMMANDS: \n add_shop name | add_product name price | expose_product name product name shop | " +
                "show_collections | show_elements name collection \n | clear_collection name collection" +
                "| show_statistic \n");

        while(true) {
            String inputString = UserInput.getLine();
            String[] fragmentsInputString = inputString.split("\s");
            switch (fragmentsInputString[0]) {
                case "add_shop" -> mongo.addShop(fragmentsInputString[1]);
                case "add_product" -> mongo.addProduct(fragmentsInputString[1], fragmentsInputString[2]);
                case "expose_product" -> mongo.addProductInShop(fragmentsInputString[1], fragmentsInputString[2]);
                case "show_statistic" -> mongo.showStatisticShop();
                default -> System.out.println("Incorrect command");
            }
        }
    }
}
