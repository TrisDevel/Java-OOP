package hotel_management;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class hotelCheck {
    static List<Map<String, String>> hotelsMap = new ArrayList<>();

    private static File file = new File("file.dat");
    private static Scanner sc = new Scanner(System.in);

    // ADD NEW HOTEL
    public static void addHotel() {

        Map<String, String> hotel = new LinkedHashMap<>();

        String id = validCheck.getString("Please enter Hotel ID: ", "Hotel ID cannot be empty.");

        String name = validCheck.getString("Please enter Hotel name: ", "Hotel name cannot be empty.");

        int room = validCheck.getInt("Please enter the number of available rooms: ", 0);

        String address = validCheck.getString("Please enter Hotel address: ", "Hotel address cannot be empty.");

        int phone = validCheck.getInt("Please enter the phone number: ", 0);

        int rating = validCheck.getInt("Please enter the rating: ", 0);

        hotel.put("Hotel_id", id);
        hotel.put("Hotel_name", name);
        hotel.put("Hotel_room_available", String.valueOf(room));
        hotel.put("Hotel_Address", address);
        hotel.put("Hotel_Phone", String.valueOf(phone));
        hotel.put("Hotel_Rating", String.valueOf(rating));

        hotelsMap.add(hotel);
    }

    // CHECK EXITS HOTEL
    public static boolean checkExistingHotel() {
        List<Map<String, String>> hotelsFromFile = (List<Map<String, String>>) hotelsMap;

        if (hotelsFromFile.isEmpty()) {
            System.out.println("No hotels found in the file.");
            return false;
        } else {
            Map<String, String> firstHotel = hotelsFromFile.get(0);
            System.out.println("First Hotel ID: " + firstHotel.get("Hotel_id"));
            return true;
        }
    }

    // UPDATE HOTEL INFORMATION
    public static void updateHotelInformation() {
        Scanner sc = new Scanner(System.in);
        String idToUpdate = validCheck.getString("Please enter Hotel ID to update: ", "Hotel ID cannot be empty.");

        boolean hotelExists = false;

        for (Map<String, String> hotel : hotelsMap) {
            if (hotel.get("Hotel_id").equals(idToUpdate)) {
                hotelExists = true;

                System.out.println("Current Hotel Information:");
                displayHotelDetails(hotel);

                System.out.println("Enter new information (leave blank if no change).");

                String newID = validCheck.getStringUpdate("Enter new hotel ID: ");

                String newName = validCheck.getStringUpdate("Enter new hotel name: ");

                int newRoomAvailable = validCheck.getIntUpdate("Enter new the number of available rooms: ", 0);

                String newAddress = validCheck.getStringUpdate("Enter new hotel address: ");

                int newPhone = validCheck.getIntUpdate("Enter new phone of hotel: ", 0);

                int newRating = validCheck.getIntUpdate("Enter new rating of hotel: ", 0);

                if (!newName.isEmpty()) {
                    hotel.put("Hotel_id", newID);
                }

                if (!newName.isEmpty()) {
                    hotel.put("Hotel_name", newName);
                }
                if (!String.valueOf(newRoomAvailable).isEmpty()) {
                    hotel.put("Hotel_room_available", String.valueOf(newRoomAvailable));
                }
                if (!newAddress.isEmpty()) {
                    hotel.put("Hotel_Address", newAddress);
                }
                if (!String.valueOf(newPhone).isEmpty()) {
                    hotel.put("Hotel_Phone",String.valueOf(newPhone));
                }
                if (!String.valueOf(newRating).isEmpty()) {
                    hotel.put("Hotel_Rating", String.valueOf(newRating));
                }

                System.out.println("Hotel information updated successfully.");
                System.out.println("Hotel Information after updated");
                displayHotelDetails(hotel);
            } else {
                System.out.println("Hotel does not exist");
            }
        }

        if (!hotelExists) {
            System.out.println("Hotel with ID " + idToUpdate + " does not exist.");
        }
        sc.close();
    }

    // DELETE HOTEL
    public static void deleteHotel() {
        String idToDelete = validCheck.getString("Please enter Hotel ID to delete: ", "Hotel ID cannot be empty.");
        System.out.println("Do you ready to delete hotel?" + " (Press READY to delete)");
        String confirm = sc.nextLine().trim().toLowerCase();
        boolean check = false;
        if (confirm.equals("ready")) {
            boolean hotelDeleted = hotelsMap.removeIf(hotel -> hotel.get("Hotel_id").equals(idToDelete));
            if (hotelDeleted) {
                check = true;
            }
        } else {
            System.out.println("Cancelled delete hotel!");
        }
        if (check) {
            System.out.println("Hotel deleted successfully.");
        } else {
            System.out.println("Hotel with ID " + idToDelete + " not found.");
        }
    }

    // SEARCH HOTEL BY ADDRESS
    public static void searchHotel_byAddr() {
        String addrToSearch = validCheck.getString("Please enter Hotel ID to search: ", "Hotel ID cannot be empty.");

        List<Map<String, String>> hotelsFromFile = (List<Map<String, String>>) hotelsMap;

        boolean found = false;

        for (Map<String, String> hotel : hotelsFromFile) {
            if (hotel.get("Hotel_Address").equals(addrToSearch)) {
                found = true;
                System.out.println("Hotel found:");
                displayHotelDetails(hotel);
                break;
            }
        }

        if (!found) {
            System.out.println("Hotel with ID " + addrToSearch + " not found in the file.");
        }
    }

    // SEARCH BY NAME
    public static void searchHotel_byID() {
        String idToSearch = validCheck.getString("Please enter Hotel ID to search: ", "Hotel ID cannot be empty.");
        List<Map<String, String>> hotelsFromFile = (List<Map<String, String>>) hotelsMap;

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
    }

    public static void displayHotelList(List<Map<String, String>> hotelList) {
        if (hotelList.isEmpty()) {
            System.out.println("No hotels found.");
        } else {
            System.out.printf("%-15s%-30s%-20s%-70s%-20s%-10s%n",
                    "Hotel ID", "Hotel Name", "Available Rooms", "Hotel Address", "Phone Number", "Rating");
            System.out.println(
                    "--------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (Map<String, String> hotel : hotelList) {
                System.out.printf("%-15s%-30s%-20s%-70s%-20s%-10s%n",
                        hotel.get("Hotel_id"),
                        hotel.get("Hotel_name"),
                        hotel.get("Hotel_room_available"),
                        hotel.get("Hotel_Address"),
                        hotel.get("Hotel_Phone"),
                        hotel.get("Hotel_Rating"));
            }
        }
    }

    public static void displayHotelListFromFile() {
        List<Map<String, String>> hotelsFromFile = (List<Map<String, String>>) hotelsMap;

        if (hotelsFromFile.isEmpty()) {
            System.out.println("No hotels found!.");
        } else {
            hotelsFromFile.sort(Comparator.comparing(hotel -> hotel.get("Hotel_name"), Comparator.reverseOrder()));
            System.out.println("Hotel List (Descending by Hotel_Name)\n");
            displayHotelList(hotelsFromFile);
            System.out.println("Here are!");
        }
    }

    public static void displayHotelDetails(Map<String, String> hotel) {
        System.out.println("Hotel ID: " + hotel.get("Hotel_id"));
        System.out.println("Hotel Name: " + hotel.get("Hotel_name"));
        System.out.println("Available Rooms: " + hotel.get("Hotel_room_available"));
        System.out.println("Hotel Address: " + hotel.get("Hotel_Address"));
        System.out.println("Phone Number: " + hotel.get("Hotel_Phone"));
        System.out.println("Rating: " + hotel.get("Hotel_Rating") + "Star");
    }

    public static void loadHotelsFromFile() {
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                hotelsMap = (List<Map<String, String>>) ois.readObject();
                System.out.println("Load hotel successfully!");
            } catch (Exception e) {
                System.out.println("Error reading from file");
            }
        } else {
            System.out.println("File does not exist. No hotels found.");
        }
    }

    public static File createFile() {
        File file = new File("file.dat");

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

    public static void saveHotelToFilefromMap() {
        File file = new File("file.dat");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(hotelsMap);
            System.out.println("Hotel is added to the file");
        } catch (IOException e) {
            System.out.println("Error saving hotel to file");
        }
    }
}
