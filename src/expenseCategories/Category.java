
package expenseCategories;

public class Category {
    private int id;
    private String name, description;

    public Category() {
    }

   

    public int getId() {
        return id;
    }

    public Category(int id,String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public Category(int id,String name) {
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
