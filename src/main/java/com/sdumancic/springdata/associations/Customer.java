package com.sdumancic.springdata.associations;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy="customer",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PhoneNumber> numbers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PhoneNumber> getNumbers() {
        return numbers;
    }

    public void setNumbers(Set<PhoneNumber> numbers) {
        this.numbers = numbers;
    }

    public void addPhoneNumber(PhoneNumber phoneNumber){
        if (phoneNumber != null) {
            if (numbers == null) {
                numbers = new HashSet<>();
            }
            phoneNumber.setCustomer(this);
            numbers.add(phoneNumber);
        }
    }
}
