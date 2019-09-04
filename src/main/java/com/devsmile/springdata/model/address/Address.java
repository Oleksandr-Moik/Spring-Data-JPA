package com.devsmile.springdata.model.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Address")
@Table(name = "Address")
@Getter
@Setter
@ToString
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    
    public Address() {
        super();
    }
    
    public Address(Integer id, String country, String city) {
        super();
        this.id = id;
        this.country = country;
        this.city = city;
    }
}
