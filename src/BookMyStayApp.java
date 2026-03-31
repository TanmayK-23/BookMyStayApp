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

/**
 * ================================================================
 * CLASS - RoomSearchService
 * ================================================================
 *
 * Use Case 4: Room Search & Availability Check
 *
 * Description:
 * Provides read-only search functionality to view available rooms
 * using centralized inventory and room definitions.
 *
 * @version 4.0
 */
class RoomSearchService {

    /**
     * Displays available rooms along with their details and pricing.
     *
     * @param inventory centralized room inventory
     * @param singleRoom single room definition
     * @param doubleRoom double room definition
     * @param suiteRoom suite room definition
     */
    public void searchAvailableRooms(
            RoomInventory inventory,
            Room singleRoom,
            Room doubleRoom,
            Room suiteRoom) {

        java.util.Map<String, Integer> availability = inventory.getRoomAvailability();

        System.out.println("Room Search\n");

        // Single Room
        if (availability.get("Single") != null && availability.get("Single") > 0) {
            System.out.println("Single Room:");
            singleRoom.displayRoomDetails(availability.get("Single"));
        }

        // Double Room
        if (availability.get("Double") != null && availability.get("Double") > 0) {
            System.out.println("Double Room:");
            doubleRoom.displayRoomDetails(availability.get("Double"));
        }

        // Suite Room
        if (availability.get("Suite") != null && availability.get("Suite") > 0) {
            System.out.println("Suite Room:");
            suiteRoom.displayRoomDetails(availability.get("Suite"));
        }
    }
}

/**
 * ================================================================
 * MAIN CLASS - UseCase4RoomSearch
 * ================================================================
 *
 * Use Case 4: Room Search & Availability Check
 *
 * @version 4.0
 */
class UseCase4RoomSearch {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        RoomSearchService service = new RoomSearchService();
        service.searchAvailableRooms(inventory, single, dbl, suite);
    }
}

/**
 * ================================================================
 * CLASS - Reservation
 * ================================================================
 *
 * Use Case 5: Booking Request (FIFO)
 *
 * @version 5.0
 */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }

    public String getRoomType() { return roomType; }
}

/**
 * ================================================================
 * CLASS - BookingRequestQueue
 * ================================================================
 *
 * Use Case 5: Booking Request (FIFO)
 *
 * @version 5.0
 */
class BookingRequestQueue {

    private java.util.Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new java.util.LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
    }

    public Reservation getNextRequest() {
        return requestQueue.poll();
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }
}

/**
 * ================================================================
 * MAIN CLASS - UseCase5BookingRequestQueue
 * ================================================================
 *
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * @version 5.0
 */
class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("Booking Request Queue");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        while (bookingQueue.hasPendingRequests()) {
            Reservation r = bookingQueue.getNextRequest();
            System.out.println("Processing booking for Guest: " + r.getGuestName()
                    + ", Room Type: " + r.getRoomType());
        }
    }
}

/**
 * ================================================================
 * CLASS - RoomAllocationService
 * ================================================================
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * @version 6.0
 */
class RoomAllocationService {

    private java.util.Set<String> allocatedRoomIds;
    private java.util.Map<String, java.util.Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds = new java.util.HashSet<>();
        assignedRoomsByType = new java.util.HashMap<>();
    }

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomType = reservation.getRoomType();
        java.util.Map<String, Integer> availability = inventory.getRoomAvailability();

        if (availability.get(roomType) != null && availability.get(roomType) > 0) {

            String roomId = generateRoomId(roomType);

            allocatedRoomIds.add(roomId);

            assignedRoomsByType
                    .computeIfAbsent(roomType, k -> new java.util.HashSet<>())
                    .add(roomId);

            inventory.updateAvailability(roomType, availability.get(roomType) - 1);

            System.out.println("Booking confirmed for Guest: " + reservation.getGuestName()
                    + ", Room ID: " + roomId);

        } else {
            System.out.println("No rooms available for " + roomType);
        }
    }

    private String generateRoomId(String roomType) {

        int count = assignedRoomsByType.getOrDefault(roomType, new java.util.HashSet<>()).size() + 1;

        return roomType + "-" + count;
    }
}

/**
 * ================================================================
 * MAIN CLASS - UseCase6RoomAllocation
 * ================================================================
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * @version 6.0
 */
class UseCase6RoomAllocation {

    public static void main(String[] args) {

        System.out.println("Room Allocation Processing");

        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Single"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));

        RoomAllocationService service = new RoomAllocationService();

        while (queue.hasPendingRequests()) {
            Reservation r = queue.getNextRequest();
            service.allocateRoom(r, inventory);
        }
    }
}

/**
 * ================================================================
 * CLASS - AddOnService
 * ================================================================
 *
 * Use Case 7: Add-On Service Selection
 *
 * @version 7.0
 */
class AddOnService {

    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() { return serviceName; }

    public double getCost() { return cost; }
}

/**
 * ================================================================
 * CLASS - AddOnServiceManager
 * ================================================================
 *
 * Use Case 7: Add-On Service Selection
 *
 * @version 7.0
 */
class AddOnServiceManager {

    private java.util.Map<String, java.util.List<AddOnService>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new java.util.HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {
        servicesByReservation
                .computeIfAbsent(reservationId, k -> new java.util.ArrayList<>())
                .add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {
        double total = 0.0;

        java.util.List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services != null) {
            for (AddOnService s : services) {
                total += s.getCost();
            }
        }

        return total;
    }
}

/**
 * ================================================================
 * MAIN CLASS - UseCase7AddOnServiceSelection
 * ================================================================
 *
 * Use Case 7: Add-On Service Selection
 *
 * @version 7.0
 */
class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("Add-On Service Selection");

        String reservationId = "Single-1";

        AddOnServiceManager manager = new AddOnServiceManager();

        manager.addService(reservationId, new AddOnService("Breakfast", 500.0));
        manager.addService(reservationId, new AddOnService("Spa", 1000.0));

        double totalCost = manager.calculateTotalServiceCost(reservationId);

        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}


/**
 * ================================================================
 * CLASS - BookingHistory
 * ================================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * @version 8.0
 */
class BookingHistory {

    private java.util.List<Reservation> confirmedReservations;

    public BookingHistory() {
        confirmedReservations = new java.util.ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    public java.util.List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}

/**
 * ================================================================
 * CLASS - BookingReportService
 * ================================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * @version 8.0
 */
class BookingReportService {

    public void generateReport(BookingHistory history) {

        System.out.println("\nBooking History Report");

        for (Reservation r : history.getConfirmedReservations()) {
            System.out.println("Guest: " + r.getGuestName()
                    + ", Room Type: " + r.getRoomType());
        }
    }
}

/**
 * ================================================================
 * MAIN CLASS - UseCase8BookingHistoryReport
 * ================================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * @version 8.0
 */
class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("Booking History and Reporting\n");

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history);
    }
}

/**
 * ================================================================
 * CLASS - InvalidBookingException
 * ================================================================
 *
 * Use Case 9: Error Handling & Validation
 *
 * @version 9.0
 */
class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}

/**
 * ================================================================
 * CLASS - ReservationValidator
 * ================================================================
 *
 * Use Case 9: Error Handling & Validation
 *
 * @version 9.0
 */
class ReservationValidator {

    public void validate(
            String guestName,
            String roomType,
            RoomInventory inventory
    ) throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (roomType == null || roomType.trim().isEmpty()) {
            throw new InvalidBookingException("Room type cannot be empty.");
        }

        if (!inventory.getRoomAvailability().containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        if (inventory.getRoomAvailability().get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available for selected type.");
        }
    }
}

/**
 * ================================================================
 * MAIN CLASS - UseCase9ErrorHandlingValidation
 * ================================================================
 *
 * Use Case 9: Error Handling & Validation
 *
 * @version 9.0
 */
class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        System.out.println("Booking Validation");

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {

            System.out.print("Enter guest name: ");
            String name = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String type = scanner.nextLine();

            validator.validate(name, type, inventory);

            Reservation reservation = new Reservation(name, type);
            bookingQueue.addRequest(reservation);

            System.out.println("Booking request added successfully.");

        } catch (InvalidBookingException e) {

            System.out.println("Booking failed: " + e.getMessage());

        } finally {
            scanner.close();
        }
    }
}

/**
 * ================================================================
 * CLASS - CancellationService
 * ================================================================
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * @version 10.0
 */
class CancellationService {

    private java.util.Stack<String> releasedRoomIds;
    private java.util.Map<String, String> reservationRoomTypeMap;

    public CancellationService() {
        releasedRoomIds = new java.util.Stack<>();
        reservationRoomTypeMap = new java.util.HashMap<>();
    }

    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Invalid reservation ID.");
            return;
        }

        String roomType = reservationRoomTypeMap.get(reservationId);

        releasedRoomIds.push(reservationId);

        java.util.Map<String, Integer> availability = inventory.getRoomAvailability();
        inventory.updateAvailability(roomType, availability.get(roomType) + 1);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }

    public void showRollbackHistory() {

        System.out.println("\nRollback History (Most Recent First):");

        while (!releasedRoomIds.isEmpty()) {
            System.out.println("Released Reservation ID: " + releasedRoomIds.pop());
        }
    }
}

/**
 * ================================================================
 * MAIN CLASS - UseCase10BookingCancellation
 * ================================================================
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * @version 10.0
 */
class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("Booking Cancellation");

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        String reservationId = "Single-1";

        service.registerBooking(reservationId, "Single");

        service.cancelBooking(reservationId, inventory);

        service.showRollbackHistory();

        System.out.println("\nUpdated Single Room Availability: " +
                inventory.getRoomAvailability().get("Single"));
    }
}