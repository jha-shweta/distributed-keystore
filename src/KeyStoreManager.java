import models.Attribute;
import models.DataNode;
import models.DataStore;
import service.DataService;
import service.impl.DataServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by saurabh.jha on 05/02/17.
 */
public class KeyStoreManager {
    private static DataService dataService = null;
    private DataStore dataStore;
    public KeyStoreManager(){
        dataStore = new DataStore();
    }

    public static DataService getService(){
        if (dataService == null){
            synchronized (KeyStoreManager.class){
                if (dataService == null)
                    dataService = new DataServiceImpl();
            }
        }
        return dataService;
    }

    public static void main(String[] args) throws IOException {
        KeyStoreManager manager = new KeyStoreManager();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("enter no of attributes");
        int N = Integer.parseInt(br.readLine());
        System.out.println("enter attributes");
        for (int i = 0; i < N; i++){
            String s = br.readLine();
            String attibuteName = s.split(" ")[0];
            String attributeType = s.split(" ")[1];
            manager.dataStore.getAttributes().put(attibuteName, new Attribute(attibuteName, attributeType));
        }
//        for (String key : manager.dataStore.getAttributes().keySet()) {
//            System.out.println("key " + key + " value : " + manager.dataStore.getAttributes().get(key).getName() + ", " +  manager.dataStore.getAttributes().get(key).getType());
//        }

        System.out.println("add total no of kyes to insert");
        int totalKeys = Integer.parseInt(br.readLine());
        for (int i = 0; i < totalKeys; i++){
            String s = br.readLine();
            String key = s.split(" ")[0];
            String name = s.split(" ")[1];
            String value = s.split(" ")[2];
            Attribute attribute = manager.dataStore.getAttributes().get(name);
            if (attribute == null){
                System.out.println("attribute not found");
                continue;
            }
            DataNode node = new DataNode(attribute, value);
            manager.getService().insert(manager.dataStore, key, node);
        }

        while (true){
            System.out.println("enter key to fetch");
            String key = br.readLine();
            LinkedList<DataNode> nodes = manager.getService().fetchData(manager.dataStore, key);
            ListIterator<DataNode> iterator = nodes.listIterator();
            while (iterator.hasNext()){
                DataNode node = iterator.next();
                System.out.println("attribute name :" + node.getAttributeType().getName() + ", value :" + node.getValue());
            }
        }
    }
}
