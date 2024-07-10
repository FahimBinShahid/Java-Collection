import java.time.LocalDate;
import java.util.*;

public class Store {

        private static Random random = new Random();
        private Map<String, InventoryItem> intventory;
        private  NavigableSet<Cart> carts= new TreeSet<>(Comparator.comparing(Cart::getId));
        private Map<Category, Map<String, InventoryItem>> aislenInventory;

    public static void main(String[] args) {

        Store store = new Store();
        store.stockStore();
        store.listInventory();

        store.stockAisles();
        store.listProductByCategories();
        store.manageStoreCarts();
        store.listProductByCategories(false,true);
        store.carts.forEach(System.out::println);
        store.listProductByCategories(false,true);
        store.carts.forEach(System.out::println);
    }

    private void manageStoreCarts() {

        Cart cart1 = new Cart(Cart.CartType.PHYSICAL, 1);
        carts.add(cart1);

        InventoryItem item = aislenInventory.get(Category.PRODUCE).get("apple");
        cart1.addItem(item,6);
        cart1.addItem(aislenInventory.get(Category.PRODUCE).get("pear"), 5);
        cart1.addItem(aislenInventory.get(Category.BEVERAGE).get("coffee"), 5);
        System.out.println(cart1);

        cart1.removeItem(aislenInventory.get(Category.BEVERAGE).get("coffee"),1);
        System.out.println(cart1);

        Cart cart2 = new Cart(Cart.CartType.VIRTUAL,1);
        carts.add(cart2);
        cart2.addItem(intventory.get("L103"), 20);
        cart2.addItem(intventory.get("B100"),37);
        System.out.println(cart2);

        Cart cart3 = new Cart(Cart.CartType.VIRTUAL,0);
        carts.add(cart3);
        cart3.addItem(intventory.get("R777"), 998);
        System.out.println(cart3);
        if(!checkOutCart(cart3)){
            System.out.println("Something went wrong, could not check out");
        }
        Cart cart4 = new Cart(Cart.CartType.PHYSICAL, 0);
        carts.add(cart4);
        cart4.addItem(aislenInventory.get(Category.BEVERAGE).get("tea"),1);
        System.out.println(cart4);

    }

    private  boolean checkOutCart(Cart cart) {
        for(var cartItem : cart.getProducts().entrySet()){
            var item = intventory.get(cartItem.getKey());
            int qty = cartItem.getValue();
            if(!item.sellItem(qty)) return false;
        }
        cart.printSalesSlip(intventory);
        carts.remove(cart);
        return true;
    }

    private void abandonCarts() {
        int dayOfYear = LocalDate.now().getDayOfYear();
        Cart lastCart = null;
        for(Cart cart : carts) {
            if(cart.getCartDate().getDayOfYear() == dayOfYear) {
                break;
            }
            lastCart = cart;
        }

        var oldCarts = carts.headSet(lastCart,true);
        Cart abandonedCart = null;
        while((abandonedCart = oldCarts.pollFirst()) != null){
            for(String sku : abandonedCart.getProducts().keySet()) {
                InventoryItem item = intventory.get(sku);
                item.releaseItem(abandonedCart.getProducts().get(sku));

            }

        }

    }
    private void listProductByCategories() {

        listProductByCategories(true,false);

    }
    private void listProductByCategories(boolean includeHeader, boolean includeDetail) {

        aislenInventory.keySet().forEach(k -> {
           if(includeHeader) System.out.println("---------\n" + k + "\n-------------");
            if(!includeDetail) {
                aislenInventory.get(k).keySet().forEach(System.out::println);
            } else {
                aislenInventory.get(k).values().forEach(System.out::println);
            }
        });

    }

    private void stockStore() {
        intventory = new HashMap<>();
        List<Product> products = new ArrayList<>(List.of(new Product("A100","apple","local",Category.PRODUCE),
                new Product("B100","banana","local",Category.PRODUCE),
                new Product("P100","pear","local",Category.PRODUCE),
                new Product("L103","lemon","local",Category.PRODUCE),
                new Product("M201","milk","farm",Category.DAIRY),
                new Product("Y001","yogurt","farm",Category.DAIRY),
                new Product("C333","cheese","farm",Category.DAIRY),
                new Product("R777","rice chex","Nabisco",Category.CEREAL),
                new Product("G111","granola","Nat Valley",Category.CEREAL),
                new Product("BB11","ground beef","butcher",Category.MEAT),
                new Product("CC11","chicken","butcher",Category.MEAT),
                new Product("BC11","bacon","butcher",Category.MEAT),
                new Product("BC77","coke","coca cola",Category.BEVERAGE),
                new Product("BC88","coffee","value",Category.BEVERAGE),
                new Product("BC99","tea","herbal",Category.BEVERAGE)));

        products.forEach(p -> intventory.put(p.sku(), new InventoryItem(p,
                random.nextDouble(0,1.25),1000, 5)));
    }
    private void stockAisles() {
        aislenInventory = new EnumMap<>(Category.class);
        for(InventoryItem item : intventory.values()) {
            Category aisle = item.getProduct().category();

            Map<String, InventoryItem> productMap = aislenInventory.get(aisle);
            if(productMap == null) {
                productMap = new TreeMap<>();
            }
            productMap.put(item.getProduct().name(),item);
            aislenInventory.putIfAbsent(aisle, productMap);
        }


    }

    private void listInventory() {
        System.out.println("-------------------------------");
        intventory.values().forEach(System.out::println);
    }



}
