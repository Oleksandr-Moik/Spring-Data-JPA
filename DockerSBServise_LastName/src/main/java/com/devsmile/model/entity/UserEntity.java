package com.devsmile.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
//@Builder
@Table(name="UserEntity")
@ToString
public class UserEntity {

 	@Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String firstName;
    private String lastName;
    private Integer age;

    public UserEntity() {
        super();
    }
    
    public UserEntity(UserBuilder builder) {
        this.id=builder.id;
        this.firstName=builder.firstName;
        this.lastName=builder.lastName;
        this.age=builder.age;
    }
    
//    public UserEntity(Integer id, String firstName, String lastName, Integer age) {
//        super();
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.age = age;
//    }
    
    public UserBuilder builder() {
        return new UserEntity.UserBuilder();
    }
    
    @ToString
    public static class UserBuilder{
        
        private Integer id;
        private String firstName;
        private String lastName;
        private Integer age;
        
        public UserBuilder() {
            
        }
        
        public UserBuilder id(Integer id) {
            this.id = id;
            return this;
        }
        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public UserBuilder age(Integer age) {
            this.age = age;
            return this;
        }
        
        public UserEntity build() {
            return new UserEntity(this);
        }
    }
    
}