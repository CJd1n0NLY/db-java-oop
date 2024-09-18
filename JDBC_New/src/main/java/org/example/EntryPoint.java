package org.example;

public class EntryPoint {
    public static void main(String[] args) {
        UserDaoImplementation dao = new UserDaoImplementation();
//
//        for(User user : dao.getAllUsers()) {
//            System.out.println(user.getId());
//            System.out.println(user.getFirstname());
//            System.out.println(user.getLastname());
//            System.out.println(user.getEmail());
//
//            System.out.println("----------------");
//        }
//
//        --get user by email
//        User user = dao.getByEmail("jlloyd@gmail.com");
//        System.out.println(user.getId());
//        System.out.println(user.getFirstname());
//        System.out.println(user.getLastname());
//        System.out.println(user.getEmail());

//        --get user by id
//        User user = dao.getById(1);
//        System.out.println(user.getId());
//        System.out.println(user.getFirstname());
//        System.out.println(user.getLastname());
//        System.out.println(user.getEmail());
//
//        --inserting data--
//
//        User user = new User();
//        user.setFirstname("Johny");
//        user.setLastname("Bravo");
//        user.setEmail("jbravo@gmail.com");
//
//        boolean result = dao.create(user);
//
//        if (result) {
//            System.out.println("New User is created successfully");
//        }
//        else{
//            System.out.println("Failed to create new User");
//        }

//        --deleting data--
//        User user = new User();
//        user.setId(6);
//
//        boolean result = dao.delete(6);
//
//        if (result) {
//            System.out.println("User with ID : " + user.getId() + " : is deleted successfully.");
//        } else {
//            System.out.println("Failed to delete user with ID : " + user.getId());
//        }

//        --updating data--
        User user = new User();
        user.setId(8);
        user.setFirstname("Sarah");
        user.setLastname("Potato");
        user.setEmail("spotato@yahoo.com");

        boolean updated = dao.update(user);
        if (updated){
            System.out.println("User ID " + user.getId() + " is updated Successfully ");
        }
        else {
            System.out.println("Failed to update user ID : " + user.getId());
        }

    }
}
