package api;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException(String category) {
        super("No such category (" + category + ")");
    }


}
