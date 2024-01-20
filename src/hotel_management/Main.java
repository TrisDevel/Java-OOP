package hotel_management;

import java.util.*;

public class Main {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        hotelCheck.createFile();
        while (true) {
            System.out.println("Please enter your choice!");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    do {
                        hotelCheck.addHotel();
                        System.out.println("Hotel added successfully.");
                        System.out.println("1. Continue create hotel \n" + "2. Go back to menu "); 
                        int x = sc.nextInt();
                        if (x != 1) {
                            break;                    
                        }                     
                    } while (true);
                    break;
                    case 2: 
                        hotelCheck.checkExistingHotel(); 
                        break;
                    case 3: 
                        hotelCheck.updateHotelInformation(); 
                        break;
                    case 4: 
                        hotelCheck.deleteHotel(); 
                        break;
                    case 5: 
                        System.out.println("1. Search by Hotel ID \n" + "2. Search by Hotel name" );
                        int x = sc.nextInt();
                        do {
                            if (x == 1) {
                                hotelCheck.searchHotel_byID();
                            } else if (x == 2){
                                hotelCheck.searchHotel_byName();
                            }
                            if (x != 1 || x != 2){
                                break;
                            }
                        } while (x == 1 || x ==2);
                        break;
                        
                    case 6: 
                    hotelCheck.displayHotelList(); 
                    break;
                    case 7: 
                    System.out.println("Do you want exit program. Press any button to exit!");
                    sc.nextLine();
                    System.exit(0);    
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        }
    }

}