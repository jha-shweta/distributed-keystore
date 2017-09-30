package models;

/**
 * Created by saurabh.jha on 05/02/17.
 */
public class Attribute<T> {
    private final String name;
    private final T type;

    public Attribute(String name, T type){
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public T getType() {
        return type;
    }
}
