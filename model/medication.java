public class medication implements Cloneable {
    
    private String  id;
    private String  name;
    private String  category;
    private double  price;
    private int     stock;
    private boolean needsPrescription;

    // constructor 
    public medication(String id, String name, String category, double price, int stock, boolean needsPrescription) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.needsPrescription = needsPrescription;
    }

    @Override
    public medication clone() {
        try {
            return (medication) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("No se pudo clonar: " + e.getMessage()); 
        }
    }

    // getters and setters
    public boolean isLowStock()                         { return stock <= 10; }
    public String  getId()                              { return id; }
    public void    setId(String id)                     { this.id = id; }
    public String  getName()                            { return name; }
    public void    setName(String name)                 { this.name = name; }
    public String  getCategory()                        { return category; }
    public void    setCategory(String cat)              { this.category = cat; }
    public double  getPrice()                           { return price; }
    public void    setPrice(double price)               { this.price = price; }
    public int     getStock()                           { return stock; }
    public void    setStock(int stock)                  { this.stock = stock; }
    public boolean isNeedsPrescription()                { return needsPrescription; }
    public void    setNeedsPrescription(boolean np)     { this.needsPrescription = np; }

    @Override
    public String toString() { return name + " (" + category + ")"; }
}
