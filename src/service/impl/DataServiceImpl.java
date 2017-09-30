package service.impl;

import models.Attribute;
import models.DataNode;
import models.DataStore;
import service.DataService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by saurabh.jha on 05/02/17.
 */
public class DataServiceImpl implements DataService {


    @Override
    public boolean insert(DataStore dataStore, String key, DataNode node) {
        Attribute dataAttribute = node.getAttributeType();
        Attribute existingAttribute = dataStore.getAttributes().get(dataAttribute.getName());
        if (existingAttribute == null || !dataAttribute.getType().equals(existingAttribute.getType()))
            return false;
        if (dataStore.getData().get(key) == null){
            LinkedList<DataNode> linkedList = new LinkedList<>();
            dataStore.getData().put(key, linkedList);
        }
        dataStore.getData().get(key).add(node);
        return true;
    }

    @Override
    public void delete(DataStore dataStore, String key) {
        dataStore.getData().remove(key);
    }

    @Override
    public LinkedList<DataNode> fetchData(DataStore dataStore, String key) {
        return dataStore.getData().get(key);
    }



    @Override
    public List<String> scan(DataStore dataStore, DataNode node) {
        List<String> keys = new ArrayList<>();
        for (String key : dataStore.getData().keySet()){
            ListIterator<DataNode> iterator = dataStore.getData().get(key).listIterator();
            while (iterator.hasNext()){
                DataNode dataNode = iterator.next();
                if (dataNode.getValue().equals(node.getValue()) && dataNode.getAttributeType().equals(node.getAttributeType())){
                    keys.add(key);
                }
            }
        }
        return keys;
    }

    @Override
    public boolean update(DataStore dataStore, String key, Attribute attribute, DataNode updatedNode) {
        ListIterator<DataNode> listIterator = dataStore.getData().get(key).listIterator();
        while (listIterator.hasNext()){
            DataNode dataNode = listIterator.next();
            if (dataNode.getAttributeType().equals(attribute) && updatedNode.getAttributeType().equals(attribute)){
                dataNode.setValue(updatedNode.getValue());
                return true;
            }
        }
        return false;
    }
}
