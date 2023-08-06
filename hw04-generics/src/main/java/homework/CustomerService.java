package homework;

import java.util.AbstractMap;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    TreeMap<Customer, String> customerStringTreeMap = new TreeMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> entry = customerStringTreeMap.firstEntry();
        return this.getCustomerEntry(entry);

    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> entry = customerStringTreeMap.higherEntry(customer);
        return this.getCustomerEntry(entry);
    }

    public void add(Customer customer, String data) {
        customerStringTreeMap.put(customer, data);
    }

    private Map.Entry<Customer, String> getCustomerEntry(Map.Entry<Customer, String> entry) {
        if(entry != null) {
            Customer entryCustomer = entry.getKey();
            return new AbstractMap.SimpleEntry<>(new Customer(entryCustomer.getId(),
                    entryCustomer.getName(),
                    entryCustomer.getScores()),
                    entry.getValue());
        }
        return  null;
    }
}
