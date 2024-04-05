
package incomeCategories;


public class Category1 {
    private int id;
    private String name, description;

    public Category1() {
    }

   

    public int getId() {
        return id;
    }

    public Category1(int id,String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public Category1(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    
}
