package Behavioural.StatePattern;

//# requirements:
//        # 1. different locker sizes - like small, medium and large
//        # 2. locker is assigned to customer based on package size
//        # 3. returns should be supported
//        # 4. when order is delivered or to be returned an OTP should be sent to customer
//        # 5. there's also a delivery guy and almost the same rules apply to him
//        # 6. if customer doesnt pick up within x amount of days, refund will be initiated
//        # 7. one locker location has multiple lockers
//        # 8. customer can choose which location to pickup from / drop at
//        # 9. locker should have states like closed / open

/*
Users should be able to use a code to open a locker and pick up a package
        Delivery guy should be able to find an "optimal" locker for a package
*/

import java.util.*;
// State interface

enum Size{
    SMALL, MEDIUM, LARGE
}

class Package{
    private String packageID;
    private Size packageSize;
    public Package(String packageID, Size packageSize){
        this.packageID = packageID;
        this.packageSize = packageSize;
    }
    public String getPackageID(){
        return  this.packageID;
    }
}
class LockerPackage extends Package{
    private String code;
    private String lockerID;
    public LockerPackage(String packageID, Size packageSize, String code , String lockerID){
        super(packageID, packageSize);
        this.code = code;
        this.lockerID = lockerID;
    }

    private boolean isValidCode(String code) {
        return this.code.equals(code);
    }
}
interface LockerState {
    void bookLocker(Locker locker);
    void openLockerForDelivery(Locker locker);
    void addPackage(Locker locker, LockerPackage lockerPackage);
    void collectPackage(Locker locker, String otpCode, long currentTime);
}

// Concrete States
class AvailableState implements LockerState {
    public void bookLocker(Locker locker) {
        locker.setState(new BookedState());
    }
    public void openLockerForDelivery(Locker locker) {
        throw new IllegalStateException("Locker is not booked yet.");
    }
    public void addPackage(Locker locker, LockerPackage lockerPackage) {
        throw new IllegalStateException("Locker must be opened for package placement.");
    }
    public void collectPackage(Locker locker, String otpCode, long currentTime) {
        throw new IllegalStateException("No package available for collection.");
    }
}

class BookedState implements LockerState {
    public void bookLocker(Locker locker) {
        throw new IllegalStateException("Locker is already booked.");
    }
    public void openLockerForDelivery(Locker locker) {
        locker.setState(new OpenState());
    }
    public void addPackage(Locker locker, LockerPackage lockerPackage) {
        throw new IllegalStateException("Locker must be opened first.");
    }
    public void collectPackage(Locker locker, String otpCode, long currentTime) {
        throw new IllegalStateException("No package to collect.");
    }
}

class OpenState implements LockerState {
    public void bookLocker(Locker locker) {
        throw new IllegalStateException("Locker is already in use.");
    }
    public void openLockerForDelivery(Locker locker) {
        throw new IllegalStateException("Locker is already open.");
    }
    public void addPackage(Locker locker, LockerPackage lockerPackage) {
        locker.setCurrPackage(lockerPackage);
        locker.setState(new ClosedState());
    }
    public void collectPackage(Locker locker, String otpCode, long currentTime) {
        throw new IllegalStateException("Locker is empty.");
    }
}

class ClosedState implements LockerState {
    public void bookLocker(Locker locker) {
        throw new IllegalStateException("Locker is already in use.");
    }
    public void openLockerForDelivery(Locker locker) {
        throw new IllegalStateException("Locker cannot be opened.");
    }
    public void addPackage(Locker locker, LockerPackage lockerPackage) {
        throw new IllegalStateException("Package already placed.");
    }
    public void collectPackage(Locker locker, String otpCode, long currentTime) {
        locker.setCurrPackage(null);
        locker.setState(new AvailableState());
    }
}

class Locker {
    private final String lockerId;
    private final String locationId;
    private LockerState lockerState;
    private LockerPackage currPackage;

    public Locker(String lockerId, String locationId) {
        this.lockerId = lockerId;
        this.locationId = locationId;
        this.lockerState = new AvailableState();
    }

    public void setState(LockerState lockerState) {
        this.lockerState = lockerState;
    }

    public void setCurrPackage(LockerPackage currPackage) {
        this.currPackage = currPackage;
    }

    public void bookLocker() {
        lockerState.bookLocker(this);
    }

    public void openLockerForDelivery() {
        lockerState.openLockerForDelivery(this);
    }

    public void addPackage(LockerPackage lockerPackage) {
        lockerState.addPackage(this, lockerPackage);
    }

    public void collectPackage(String otpCode, long currentTime) {
        lockerState.collectPackage(this, otpCode, currentTime);
    }
}

class LockerService {
    private static LockerService instance;
    private Map<String, String> locations;

    private LockerService() {
        locations = new HashMap<>();
    }

    public static synchronized LockerService getInstance() {
        if (instance == null) {
            instance = new LockerService();
        }
        return instance;
    }

    public void addLocation(String id, String city) {
        locations.put(id, city);
    }

    public Map<String, String> getLocations() {
        return locations;
    }
}

public class Main_AmazonLocker {
    public static void main(String[] args) {
        LockerService lockerService = LockerService.getInstance();
        lockerService.addLocation("loc1", "Austin");

        Locker locker = new Locker("L1", "loc1");
        System.out.println("Booking locker...");
        locker.bookLocker();

        System.out.println("Opening locker for delivery...");
        locker.openLockerForDelivery();
        Size size = Size.MEDIUM;
        LockerPackage p = new LockerPackage("123", size, "asdfef2", "789");
        System.out.println("Adding package...");
        locker.addPackage(p);

        System.out.println("Collecting package...");
        locker.collectPackage("1234", System.currentTimeMillis());
    }
}