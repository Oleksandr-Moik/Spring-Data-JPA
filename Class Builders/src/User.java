
public class User {

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    
    public Integer getId() {
        return id;
    }
    public Integer getAge() {
        return age;
    }
    public String  getFirstName() {
        return firstName;
    }
    public String  getLastName() {
        return lastName;
    }
    
    public User() {
        
    }
    
    private User(UserBuilder builder) {
        this.id=builder.id;
        this.firstName=builder.firstName;
        this.lastName=builder.lastName;
        this.age=builder.age;
    }
    
    public UserBuilder builder() {
        return new User.UserBuilder();
    }
    
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
        
        public User build() {
            return new User(this);
        }
    }
}
