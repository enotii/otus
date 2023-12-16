package homework;


import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import static java.util.Objects.nonNull;

public class CustomerService {
    TreeMap<Customer, String> map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));
    private Map.Entry<Customer, String> entry = null;
//todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> entry = map.firstEntry();
        return nonNull(entry) ? Map.entry(new Customer(entry.getKey()), entry.getValue()) : null;
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        //return null; // это "заглушка, чтобы скомилировать"
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> entry = map.higherEntry(customer);
        return nonNull(entry) ? Map.entry(new Customer(entry.getKey()), entry.getValue()) : null;
        //return null; // это "заглушка, чтобы скомилировать"
    }

    public void add(Customer customer, String data) {
        map.put(new Customer(customer.getId(), customer.getName(), customer.getScores()), data);
    }
}
