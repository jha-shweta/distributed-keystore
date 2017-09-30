package models;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by saurabh.jha on 05/02/17.
 */
public class DataStore {
    private HashMap<String, LinkedList<DataNode>> data = new HashMap<>();
    private HashMap<String, Attribute> attributes = new HashMap<>();

    public HashMap<String, LinkedList<DataNode>> getData() {
        return data;
    }

    public HashMap<String, Attribute> getAttributes() {
        return attributes;
    }
}
