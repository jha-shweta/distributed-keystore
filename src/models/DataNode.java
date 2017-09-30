package models;

/**
 * Created by saurabh.jha on 05/02/17.
 */
public class DataNode<T> {
    private final Attribute<T> attributeType;
    private T value;

    public DataNode(Attribute<T> attributeType, T value){
        this.attributeType = attributeType;
        this.value = value;
    }

    public Attribute<T> getAttributeType() {
        return attributeType;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
