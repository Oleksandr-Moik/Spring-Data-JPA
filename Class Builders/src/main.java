
public class main {

    public static void main(String[] args) {

        
        User user1 = new User().builder()
                .age(23)
                .id(1)
                .firstName("Sasha")
                .lastName("Moik")
                .build();
        
        System.out.println(user1.toString());

    }

}
