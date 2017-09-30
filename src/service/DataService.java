package service;

import models.Attribute;
import models.DataNode;
import models.DataStore;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by saurabh.jha on 05/02/17.
 */
public interface DataService {
    public boolean insert(DataStore dataStore, String key, DataNode node);
    public void delete(DataStore dataStore, String key);
    public LinkedList<DataNode> fetchData(DataStore dataStore, String key);
    public List<String> scan(DataStore dataStore, DataNode node);
    public boolean update(DataStore dataStore, String key, Attribute attribute, DataNode updateNode);
}
