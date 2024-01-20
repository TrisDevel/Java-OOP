package hotel_management;


public class hotel {
    String hotel_id, hotel_name, hotel_address;
    int hotel_room, hotel_phone, hotel_rating;


    public hotel(String hotel_id, String hotel_name, String hotel_address, int hotel_room, int hotel_phone, int hotel_rating) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.hotel_address = hotel_address;
        this.hotel_room = hotel_room;
        this.hotel_phone = hotel_phone;
        this.hotel_rating = hotel_rating;
    }


    public String getHotel_id() {
        return this.hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return this.hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_address() {
        return this.hotel_address;
    }

    public void setHotel_address(String hotel_address) {
        this.hotel_address = hotel_address;
    }

    public int getHotel_room() {
        return this.hotel_room;
    }

    public void setHotel_room(int hotel_room) {
        this.hotel_room = hotel_room;
    }

    public int getHotel_phone() {
        return this.hotel_phone;
    }

    public void setHotel_phone(int hotel_phone) {
        this.hotel_phone = hotel_phone;
    }

    public int getHotel_rating() {
        return this.hotel_rating;
    }

    public void setHotel_rating(int hotel_rating) {
        this.hotel_rating = hotel_rating;
    }
    
}
