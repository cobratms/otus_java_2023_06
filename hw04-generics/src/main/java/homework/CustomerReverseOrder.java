package homework;

import java.util.Stack;

public class CustomerReverseOrder {

    Stack <Customer> customers = new Stack<>();

    public void add(Customer customer) {
        customers.push(customer);
    }

    public Customer take() {
       return customers.pop();
    }
}
