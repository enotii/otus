package homework;


import java.util.Stack;

public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    Stack<Customer> customers = new Stack<>();

    public void add(Customer customer) {
        customers.push(new Customer(customer.getId(),customer.getName(),customer.getScores()));
    }

    public Customer take() {
        return customers.pop();
        //return null; // это "заглушка, чтобы скомилировать"
    }
}
