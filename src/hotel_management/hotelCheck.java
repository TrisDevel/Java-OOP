package hotel_management;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class hotelCheck {
    static List<Map<String, String>> hotels = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static File createFile() {
        File file = new File("E:\\codes\\file.dat");

        try {
            if (file.createNewFile()) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            System.out.println("Error creating file");
        }

        return file;
    }

    public static void saveHotelsToFile() {
        File file = new File("E:\\codes\\file.dat");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(hotels);
        } catch (IOException e) {
            System.out.println("Error saving to file");
        }
    }

    // ADD NEW HOTEL
    public static void addHotel() {

        Map<String, String> hotel = new LinkedHashMap<>(); // Use LinkedHashMap to maintain key order

        String id = getString("Please enter Hotel ID: ", "Hotel ID cannot be empty.");

        String name = getString("Please enter Hotel name: ", "Hotel name cannot be empty.");

        int room = getInt("Please enter the number of available rooms: ", 0);

        String address = getString("Please enter Hotel address: ", "Hotel address cannot be empty.");

        int phone = getInt("Please enter the phone number: ", 0);

        int rating = getInt("Please enter the rating: ", 0);

        hotel.put("Hotel_id", id);
        hotel.put("Hotel_name", name);
        hotel.put("Hotel_room_available", String.valueOf(room));
        hotel.put("Hotel_Address", address);
        hotel.put("Hotel_Phone", String.valueOf(phone));
        hotel.put("Hotel_Rating", String.valueOf(rating));

        hotels.add(hotel);
        String choice = getString("Do you want save this hotel to file? yes/no ", "Choice cannot be empty.");
        if (choice.equals("yes")) {
            saveHotelsToFile();
            System.out.println("Hotel is added to the file");
        } else {
            System.out.println("Hotel is not added to the file");
        }
    }

    // CHECK EXITS HOTEL
    public static boolean checkExistingHotel() {
        File file = new File("E:\\codes\\file.dat");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                List<Map<String, String>> hotelsFromFile = (List<Map<String, String>>) ois.readObject();

                if (hotelsFromFile.isEmpty()) {
                    System.out.println("No hotels found in the file.");
                    return false;
                } else {
                    System.out.println("Hotels found in the file:");
                    for (Map<String, String> hotel : hotelsFromFile) {
                        System.out.println("Hotel ID: " + hotel.get("Hotel_id"));
                    }
                    return true;
                }

            } catch (Exception e) {
                System.out.println("Error reading from file: ");
                return false;
            }
        } else {
            System.out.println("File does not exist. No hotels found.");
            return false;
        }
    }
    // UPDATE HOTEL INFORMATION
    public static void updateHotelInformation() {
        Scanner sc = new Scanner(System.in);
        String idToUpdate = getString("Please enter Hotel ID to update: ", "Hotel ID cannot be empty.");
    
        boolean hotelExists = false;
    
        for (Map<String, String> hotel : hotels) {
            if (hotel.get("Hotel_id").equals(idToUpdate)) {
                hotelExists = true;
    
                // Display current information
                System.out.println("Current Hotel Information:");
                displayHotelDetails(hotel);
    
                // Input new information
                System.out.println("Enter new information (leave blank if no change):");
                System.out.println("Enter new name");
                String newName = sc.nextLine();
                System.out.println("Enter new room available");
                String newRoomAvailable = sc.nextLine();
                System.out.println("Enter new address");
                String newAddress = sc.nextLine();
                System.out.println("Enter new phone");
                String newPhone = sc.nextLine();
                System.out.println("Enter new rating");
                String newRating = sc.nextLine();
    
                // Update information if new information is not blank
                if (!newName.isEmpty()) {
                    hotel.put("Hotel_name", newName);
                }
                if (!newRoomAvailable.isEmpty()) {
                    hotel.put("Hotel_room_available", newRoomAvailable);
                }
                if (!newAddress.isEmpty()) {
                    hotel.put("Hotel_Address", newAddress);
                }
                if (!newPhone.isEmpty()) {
                    hotel.put("Hotel_Phone", newPhone);
                }
                if (!newRating.isEmpty()) {
                    hotel.put("Hotel_Rating", newRating);
                }
    
                System.out.println("Hotel information updated successfully.");
                saveHotelsToFile(); 
            }
        }
    
        if (!hotelExists) {
            System.out.println("Hotel with ID " + idToUpdate + " does not exist.");
        }
    }
    
    // DELETE HOTEL
    public static void deleteHotel() {
        String idToDelete = getString("Please enter Hotel ID to delete: ", "Hotel ID cannot be empty.");

        boolean hotelDeleted = hotels.removeIf(hotel -> hotel.get("Hotel_id").equals(idToDelete));

        if (hotelDeleted) {
            System.out.println("Hotel deleted successfully.");
            saveHotelsToFile();
        } else {
            System.out.println("Hotel with ID " + idToDelete + " not found.");
        }
    }
    // SEARCH HOTEL BY ID
    public static void searchHotel_byID(){
        String idToSearch = getString("Please enter Hotel ID to search: ", "Hotel ID cannot be empty.");

        File file = new File("E:\\codes\\file.dat");
    
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                List<Map<String, String>> hotelsFromFile = (List<Map<String, String>>) ois.readObject();
    
                boolean found = false;
    
                for (Map<String, String> hotel : hotelsFromFile) {
                    if (hotel.get("Hotel_id").equals(idToSearch)) {
                        found = true;
                        System.out.println("Hotel found:");
                        displayHotelDetails(hotel);
                        break; 
                    }
                }
    
                if (!found) {
                    System.out.println("Hotel with ID " + idToSearch + " not found in the file.");
                }
    
            } catch (Exception e) {
                System.out.println("Error reading from file");
            }
        } else {
            System.out.println("File does not exist. No hotels found.");
        }
    }
    // SEARCH BY NAME
    public static void searchHotel_byName(){
        String nameToSearch = getString("Please enter Hotel name to search: ", "Hotel name cannot be empty.");

        File file = new File("E:\\codes\\file.dat");
    
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                List<Map<String, String>> hotelsFromFile = (List<Map<String, String>>) ois.readObject();
    
                boolean found = false;
    
                for (Map<String, String> hotel : hotelsFromFile) {
                    if (hotel.get("Hotel_name").equals(nameToSearch)) {
                        found = true;
                        System.out.println("Hotel found:");
                        displayHotelDetails(hotel);
                        break; 
                    }
                }
    
                if (!found) {
                    System.out.println("Hotel with ID " + nameToSearch + " not found in the file.");
                }
    
            } catch (Exception e) {
                System.out.println("Error reading from file");
            }
        } else {
            System.out.println("File does not exist. No hotels found.");
        }
    }

    public static void displayHotelList() {
        File file = new File("E:\\codes\\file.dat");
    
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                List<Map<String, String>> hotelsFromFile = (List<Map<String, String>>) ois.readObject();
    
                if (hotelsFromFile.isEmpty()) {
                    System.out.println("No hotels found in the file.");
                } else {
                    // Sort the hotels based on Hotel_Name in descending order
                    hotelsFromFile.sort(Comparator.comparing(hotel -> hotel.get("Hotel_name"), Comparator.reverseOrder()));
    
                    // Display the sorted list of hotels
                    System.out.println("Hotel List (Descending by Hotel_Name):");
                    displayHotelList(hotelsFromFile);
                }
    
            } catch (Exception e) {
                System.out.println("Error reading from file");
            }
        } else {
            System.out.println("File does not exist. No hotels found.");
        }
    }
    
    // Helper method to display a list of hotels
    public static void displayHotelList(List<Map<String, String>> hotelList) {
        System.out.printf("%-15s%-30s%-20s%-30s%-15s%-10s%n",
                "Hotel ID", "Hotel Name", "Available Rooms", "Hotel Address", "Phone Number", "Rating");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
    
        for (Map<String, String> hotel : hotelList) {
            System.out.printf("%-15s%-30s%-20s%-30s%-15s%-10s%n",
                    hotel.get("Hotel_id"),
                    hotel.get("Hotel_name"),
                    hotel.get("Hotel_room_available"),
                    hotel.get("Hotel_Address"),
                    hotel.get("Hotel_Phone"),
                    hotel.get("Hotel_Rating"));
        }
    }

    public static String getString(String msg1, String msg2) {
        boolean check = true;
        String result = "";
        do {
            System.out.print(msg1);
            result = sc.nextLine().trim();
            if (result.isEmpty()) {
                System.out.println(msg2);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static int getInt(String msg, int min) {
        boolean check = true;
        int number = 0;
        do {
            try {
                System.out.print(msg);
                String inputNumber = sc.nextLine().trim();

                if (inputNumber.isEmpty()) {
                    System.out.println("Input cannot be empty.");
                    check = true;
                } else {
                    number = Integer.parseInt(inputNumber);

                    if (number < min) {
                        System.out.println("Must be larger than " + min);
                        check = true;
                    } else if (inputNumber.length() > 10) {
                        System.out.println("Length must be less than or equal to 10! Please try again.");
                        check = true;
                    } else {
                        check = false;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Please try another number!");
            }
        } while (check || number < min);
        return number;
    }

    public static void displayHotelDetails(Map<String, String> hotel){
    System.out.println("Hotel ID: " + hotel.get("Hotel_id"));
    System.out.println("Hotel Name: " + hotel.get("Hotel_name"));
    System.out.println("Available Rooms: " + hotel.get("Hotel_room_available"));
    System.out.println("Hotel Address: " + hotel.get("Hotel_Address"));
    System.out.println("Phone Number: " + hotel.get("Hotel_Phone"));
    System.out.println("Rating: " + hotel.get("Hotel_Rating"));
    }
}
