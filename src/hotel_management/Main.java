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
            System.out.println("------------------Menu-------------------");
            System.out.println("| 0. Load hotel from file                |");
            System.out.println("| 1. Add new hotel.                      |");
            System.out.println("| 2. Check exist hotel.                  |");
            System.out.println("| 3. Update information.                 |");
            System.out.println("| 4. Delete hotel.                       |");
            System.out.println("| 5. Search hotel ( By address or name). |");
            System.out.println("| 6. Display hotel.                      |");
            System.out.println("| 7. Save hotel from map to file.        |");
            System.out.println("| 8. Exit program                        |");
            System.out.println("-----------------------------------------\n");
            System.out.println("Please enter your choice!");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 0: 
                    hotelCheck.loadHotelsFromFile(); 
                    break;
                case 1:
                    do {
                        hotelCheck.addHotel();
                        System.out.println("Hotel create successfully.");
                        System.out.println("1. Continue create hotel \n" + "2. Go back to menu "); 
                        int x = sc.nextInt();
                        if (x != 1) {
                            break;                    
                        }                     
                    } while (true);
                    
                    break;
                case 2:
                       do {
                        hotelCheck.checkExistingHotel(); 
                        System.out.println("Do you want to go back menu (yes/no)");
                        String x = sc.nextLine().trim().toLowerCase();
                        if (x.equals("yes")) {
                            break;
                        }  
                    } while (true);
                        break;
                case 3: 
                        hotelCheck.updateHotelInformation(); 
                        break;
                case 4: 
                        hotelCheck.deleteHotel(); 
                        break;
                case 5: 
                    System.out.println("1. Search by Hotel address \n" + "2. Search by Hotel ID");
                    int x = 0;
                    boolean validChoice = false;
                    
                    do {
                        System.out.print("Enter your choice: ");
                        x = sc.nextInt();
                        sc.nextLine();  
                        
                        if (x == 1) {
                            hotelCheck.searchHotel_byID();
                            validChoice = true;
                        } else if (x == 2) {
                            hotelCheck.searchHotel_byAddr();
                            validChoice = true;
                        } else {
                            System.out.println("Invalid choice. Please enter 1 or 2.");
                        }
                    } while (!validChoice);
                    
                    break;
                        
                case 6: 
                    hotelCheck.displayHotelListFromFile(); 
                    break;

                case 7:
                    hotelCheck.saveHotelToFilefromMap();
                    
                    
                    break;
                case 8: 
                    System.out.println("Do you want exit program. Press any button to exit!");
                    sc.nextLine();
                    System.exit(0);    
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        }
    }

}