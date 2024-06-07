import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CabBooking {

    /*
    Functionalities to include:
        1. add new rider
        2. add new cab
        3. get current cab rider
        4. get cab availability
        5.
     */

    public class Cab{
        Map<Integer, String> listOfDrivers = new HashMap<>();
        Map<Integer, Boolean> cabAvailability = new HashMap<>();
        private int cabId;
        private String driverName;
        Cab(int id, String driverName){
            this.cabId = cabId;
            this.driverName = driverName;
        }

        public int getCabId(){
            return this.cabId;
        }

        public String getDriverName(int cabId){
            return listOfDrivers.get(cabId);
        }

        public void setCabId(int cabId){
            listOfDrivers.put(cabId, " ");
        }

        public void setDriverName(int cabId, String driverName){
            listOfDrivers.put(cabId, driverName);
        }

        public boolean getAvailability(int cabId){
            return cabAvailability.get(cabId);
        }

        public void setAvailability(int cabId, boolean isAvailable){
             cabAvailability.put(cabId, isAvailable);
        }
    }

    public class Rider {
        private int RId;
        private String Rname;

        Rider(int RId, String Rname){
            this.RId = RId;
            this.Rname = Rname;
        }

        public int getRId(){
            return this.RId;
        }

        public String getRiderName(){
            return this.Rname;
        }

        public void setRId(int RId){
            this.RId = RId;
        }

        public void setRname(String Rname){
            this.Rname = Rname;
        }

    }


    public class Trip {

        private double[] source;
        private double[] destination;
        private double distance;
        private double pricePerMile;

        public Trip(double[] source, double[] destination) {
            this.source = new double[2];
            this.destination = new double[2];
        }

        public double getDistance(){
            this.distance = Math.sqrt(Math.pow(destination[1]-source[1] , 2)+Math.pow(destination[0] - source[0] , 2));
            return distance;
        }

        public double getTripAmount(){
            return pricePerMile*getDistance();
        }
    }

    public class CabBookingSystem{


        Map<Integer, Cab> cabsById;
        Map<Integer, Rider> ridersById;

        Map<Rider, Cab> mapRiderToCab;

        Map<Rider, Trip> riderCompletedTrips;

        public CabBookingSystem() {
            this.cabsById = new HashMap<>();;
            this.ridersById = new HashMap<>();
            this.mapRiderToCab = new HashMap<>();
            this.riderCompletedTrips = new HashMap<>();
        }

        public void addNewCab(Cab cab){
            cabsById.put(cab.getCabId(), cab);
        }

        public void addNewRider(Rider rider){
            ridersById.put(rider.getRId(), rider);
        }

        public List<Cab> getAvailableCabs(){
            List<Cab> list = new ArrayList<>();
            for(Cab cab : cabsById.values()){
                if(cab.getAvailability(cab.getCabId()))
                    list.add(cab);
            }
            return list;
        }

        public void updateCabAvailability(Cab cab, boolean availability){
            if(!cabsById.containsKey(cab.getCabId()))
                System.out.println("Cab not found");
            cabsById.get(cab.getCabId()).setAvailability(cab.getCabId(), availability);
        }

        public void assignCabToRider(Rider rider, Cab cab) {
            if (cabsById.get(rider.getRId()).getAvailability(cab.getCabId())) {
                mapRiderToCab.put(rider, cab);
                cabsById.get(cab.getCabId()).setAvailability(cab.getCabId(), false);
            }
        }

        public void bookCab(Rider rider){
            if(!ridersById.containsKey(rider.getRId())){
                System.out.println("Rider does not exist");
                return;
            }

            List<Cab> cabs = getAvailableCabs();
            if(cabs.isEmpty()) {
                System.out.println("No Cabs Available");
                return;
            }
            Cab availableCab = cabs.get(0);
            assignCabToRider(rider, availableCab);
            System.out.println("Cab : "+ availableCab.getCabId() + "is assigned to :" + rider.getRId());
        }

        public void endUserTrip(Rider rider, Cab cab, Trip trip){
            cabsById.get(cab.getCabId()).setAvailability(cab.getCabId(), true);
            mapRiderToCab.remove(rider);
            riderCompletedTrips.put(rider, trip);
        }

        public void getTripPrice(Trip trip){
            trip.getTripAmount();
        }

        public Map<Rider, Trip> getRiderPastTrips(Rider rider){
            return riderCompletedTrips;
        }
    }

}
