/****
 * ================================================================
 * MAIN CLASS - BookMyStayApp
 * ================================================================
 *
 * Use Case 1: Application Entry & Welcome Message
 *
 * Description:
 * This class represents the entry point of the
 * Hotel Booking Management System.
 *
 * At this stage, the application:
 * - Starts execution from the main() method
 * - Displays a welcome message to the user
 * - Confirms that the system has started successfully
 *
 * No business logic, data structures, or user input
 * is implemented in this use case.
 *
 * The goal is to establish a clear and predictable
 * application startup point.
 *
 * @author Developer
 * @version 1.0
 */
public class BookMyStayApp {

    /**
     * Application entry point.
     *
     * This method is the first method executed
     * when the program is launched by the JVM.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        System.out.println("Welcome to the Hotel Booking Management System");
        System.out.println("System initialized successfully.");

    }
}

/**
 * ================================================================
 * ABSTRACT CLASS - Room
 * ================================================================
 *
 * Use Case 2: Basic Room Types & Static Availability
 *
 * @version 2.0
 */
abstract class Room {

    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails(int available) {
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size: " + squareFeet + " sqft");
        System.out.println("Price per night: " + pricePerNight);
        System.out.println("Available: " + available);
        System.out.println();
    }
}

/**
 * CLASS - SingleRoom
 * @version 2.0
 */
class SingleRoom extends Room {

    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

/**
 * CLASS - DoubleRoom
 * @version 2.0
 */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

/**
 * CLASS - SuiteRoom
 * @version 2.0
 */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}

/**
 * ================================================================
 * MAIN CLASS - UseCase2RoomInitialization
 * ================================================================
 *
 * @version 2.0
 */
class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("Hotel Room Initialization\n");

        SingleRoom single = new SingleRoom();
        DoubleRoom dbl = new DoubleRoom();
        SuiteRoom suite = new SuiteRoom();

        System.out.println("Single Room:");
        single.displayRoomDetails(5);

        System.out.println("Double Room:");
        dbl.displayRoomDetails(3);

        System.out.println("Suite Room:");
        suite.displayRoomDetails(2);
    }
}

/**
 * ================================================================
 * CLASS - RoomInventory
 * ================================================================
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * @version 3.0
 */
class RoomInventory {

    private java.util.Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new java.util.HashMap<>();
        initializeInventory();
    }

    private void initializeInventory() {
        roomAvailability.put("Single", 5);
        roomAvailability.put("Double", 3);
        roomAvailability.put("Suite", 2);
    }

    public java.util.Map<String, Integer> getRoomAvailability() {
        return roomAvailability;
    }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }
}

/**
 * ================================================================
 * MAIN CLASS - UseCase3InventorySetup
 * ================================================================
 *
 * @version 3.0
 */
class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("Hotel Room Inventory Status\n");

        RoomInventory inventory = new RoomInventory();

        SingleRoom single = new SingleRoom();
        DoubleRoom dbl = new DoubleRoom();
        SuiteRoom suite = new SuiteRoom();

        System.out.println("Single Room:");
        single.displayRoomDetails(inventory.getRoomAvailability().get("Single"));

        System.out.println("Double Room:");
        dbl.displayRoomDetails(inventory.getRoomAvailability().get("Double"));

        System.out.println("Suite Room:");
        suite.displayRoomDetails(inventory.getRoomAvailability().get("Suite"));
    }
}