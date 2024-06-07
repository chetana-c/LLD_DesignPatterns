import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {

    /*
    car, bike, truck
    parkvehicle : vehicleId,
    getavailablespaces :

     */
    public class Car{
        int id;
        String name;
        Car(int id) {
            this.id = id;
        }

        public int getId(){
            return this.id;
        }

        public void setId(int id){
            this.id = id;
        }
    }

    public class ParkingSlot{
        int slotId;
        int capacity;
        Map<Integer, Boolean> slotAvailability;
        ParkingSlot(int slotId){
            this.slotId = slotId;
            this.slotAvailability = new HashMap<>();
            for(int i=1; i<=capacity; i++)
                slotAvailability.put(i, true);
        }

        public int getSlotId(){
            return this.slotId;
        }

        public void setSlotId(int slotId){
            this.slotId = slotId;
        }

        public boolean getSlotAvailability(int slotId){
            return slotAvailability.get(slotId);
        }

        public void setSlotAvailability(int slotId, boolean availability) {
            slotAvailability.put(slotId, availability);
        }
    }

    public class ParkingSystem{

        Map<Car, ParkingSlot> mapCarToSlot;
        HashMap<Integer, ParkingSlot> slotsById;
        List<Integer> listOfParkingSlots;
        ParkingSystem(){
            this.mapCarToSlot = new HashMap<>();
            this.slotsById = new HashMap<>();
            this.listOfParkingSlots = new ArrayList<>();
        }

        public void parkCar(Car car, ParkingSlot slot){
            if(slotsById.get(slot.getSlotId()).getSlotAvailability(slot.getSlotId()) && !mapCarToSlot.containsKey(car)){
                mapCarToSlot.put(car, slot);
                slotsById.get(slot.getSlotId()).setSlotAvailability(slot.getSlotId(), false);
            }
        }

        public List<Integer> getAllAvailableSlots(){
            List<Integer> list = new ArrayList<>();
            for(ParkingSlot slot: slotsById.values()){
                if(slot.getSlotAvailability(slot.getSlotId()))
                    list.add(slot.getSlotId());
            }
            return list;
        }

        public void removeCarFromSlot(Car car, ParkingSlot slot){
            if(!mapCarToSlot.containsKey(car))
                System.out.println("Car not found");
            // removing the car from the existing slot
            if(mapCarToSlot.get(car.getId()).equals(slot)) {
                mapCarToSlot.remove(car);
                // making the slot available again
                slotsById.get(slot.getSlotId()).setSlotAvailability(slot.getSlotId(), true);
            }
        }

        public void moveCar(Car car, ParkingSlot oldSlot, ParkingSlot newSlot){
            if(!mapCarToSlot.containsKey(car))
                System.out.println("Car Not Found");

            if(oldSlot.equals(newSlot) && mapCarToSlot.get(car).equals(oldSlot) && slotsById.get(newSlot).getSlotAvailability(newSlot.getSlotId())) {
                mapCarToSlot.put(car, newSlot);
                slotsById.get(oldSlot).setSlotAvailability(oldSlot.getSlotId(), true);
                slotsById.get(newSlot).setSlotAvailability(newSlot.getSlotId(), false);
            }
        }

    }
}
