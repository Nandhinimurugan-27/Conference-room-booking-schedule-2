import java.util.ArrayList;
import java.util.Scanner;

class Room {
    private String roomName;
    private int capacity;
    private boolean isBooked;

    public Room(String roomName, int capacity) {
        this.roomName = roomName;
        this.capacity = capacity;
        this.isBooked = false;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookRoom() {
        isBooked = true;
    }

    public void releaseRoom() {
        isBooked = false;
    }

    public void displayRoomDetails() {
        System.out.println("Room Name: " + roomName + ", Capacity: " + capacity + ", Status: " + (isBooked ? "Booked" : "Available"));
    }
}

class BookingManager {
    private ArrayList<Room> rooms;

    public BookingManager() {
        rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void showRooms() {
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
        } else {
            for (Room room : rooms) {
                room.displayRoomDetails();
            }
        }
    }

    public void bookRoom(String roomName) {
        for (Room room : rooms) {
            if (room.getRoomName().equalsIgnoreCase(roomName)) {
                if (!room.isBooked()) {
                    room.bookRoom();
                    System.out.println("Room '" + roomName + "' booked successfully!");
                } else {
                    System.out.println("Room '" + roomName + "' is already booked!");
                }
                return;
            }
        }
        System.out.println("Room not found!");
    }

    public void cancelBooking(String roomName) {
        for (Room room : rooms) {
            if (room.getRoomName().equalsIgnoreCase(roomName)) {
                if (room.isBooked()) {
                    room.releaseRoom();
                    System.out.println("Booking for room '" + roomName + "' has been cancelled.");
                } else {
                    System.out.println("Room '" + roomName + "' is not booked yet.");
                }
                return;
            }
        }
        System.out.println("Room not found!");
    }
}

public class ConferenceRoomBooking {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookingManager manager = new BookingManager();

        // Adding some sample rooms
        manager.addRoom(new Room("Alpha", 10));
        manager.addRoom(new Room("Beta", 20));
        manager.addRoom(new Room("Gamma", 15));

        int choice;
        do {
            System.out.println("\n--- Conference Room Booking System ---");
            System.out.println("1. Show All Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    manager.showRooms();
                    break;
                case 2:
                    System.out.print("Enter room name to book: ");
                    String bookRoom = sc.nextLine();
                    manager.bookRoom(bookRoom);
                    break;
                case 3:
                    System.out.print("Enter room name to cancel booking: ");
                    String cancelRoom = sc.nextLine();
                    manager.cancelBooking(cancelRoom);
                    break;
                case 4:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}
