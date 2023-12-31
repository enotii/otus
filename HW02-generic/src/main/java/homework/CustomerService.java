package homework;


import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import static java.util.Objects.nonNull;

public class CustomerService {
    NavigableMap<Customer, String> map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));
    private Map.Entry<Customer, String> entry = null;
//todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        return getImmutableCopy(map.firstEntry());
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        //return null; // это "заглушка, чтобы скомилировать"
    }


    public Map.Entry<Customer, String> getNext(Customer customer) {
        return getImmutableCopy(map.higherEntry(customer));
        //return null; // это "заглушка, чтобы скомилировать"
    }

    private Map.Entry<Customer, String> getImmutableCopy(Map.Entry<Customer, String> customerStringEntry) {
        return nonNull(customerStringEntry) ? Map.entry(new Customer(customerStringEntry.getKey()), customerStringEntry.getValue()) : null;
    }

    public void add(Customer customer, String data) {
        map.put(new Customer(customer.getId(), customer.getName(), customer.getScores()), data);
    }
}
