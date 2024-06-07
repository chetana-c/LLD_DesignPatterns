import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// assumptions - user first searches for the movie and then its respective theatres

// improvisations - can include seat alphabets along with numbers to the seats

     class MovieReservationSystem{
        HashMap<Integer, ArrayList<Theatre>> movieTheatreList; // storing movie id and its list of theatres
        ArrayList<Movie> moviesList;
        ArrayList<User> userList;
        ArrayList<Theatre> theatreList;


        HashMap<User, HashMap<Movie, HashMap<Theatre, int[]>>> bookedTickets;
        private int movieId;

    public MovieReservationSystem() {
        this.movieTheatreList = new HashMap<>();
        this.moviesList = new ArrayList<>();
        this.userList = new ArrayList<>();
        this.bookedTickets = new HashMap<>();
        this.movieId = movieId;
        this.theatreList = new ArrayList<>();
    }

    public void addNewUser(User user){
            userList.add(user);
        }

        public void addNewMovie(Movie movie){
            moviesList.add(movie);
        }

        public void addTheatreToMovie(int movieId, Theatre theatre) {
            if(movieTheatreList.containsKey(movieId))
                movieTheatreList.get(movieId).add(theatre);
            else {
                ArrayList<Theatre> list = new ArrayList<>();
                list.add(theatre);
                movieTheatreList.put(movieId, list);
            }
        }

        public ArrayList<Movie> getMovieList(){
            return moviesList;
        }

        public ArrayList<Theatre> getTheatreList(int movieId){
            return theatreList;
        }

        public ArrayList<Integer>  getVacantSeats(Theatre theatre){
            return theatre.getVacantSeats();
        }

        public void bookMovieTicket(User user, Movie movie, Theatre theatre, int[] seats){
            if(theatre.getCapacity() <= seats.length)
                System.out.println("No seats left to book : theatre out of capacity");
            else {
                if (!bookedTickets.containsKey(user)) {
                    bookedTickets.put(user, new HashMap<Movie, HashMap<Theatre, int[]>>());
                }

                HashMap<Movie, HashMap<Theatre, int[]>> userBookings = bookedTickets.get(user);

                HashMap<Theatre, int[]> movieBookings = userBookings.get(movie);

                if (movieBookings == null) {
                    movieBookings = new HashMap<>();
                    userBookings.put(movie, movieBookings);
                }

                movieBookings.put(theatre, seats);

                theatre.capacity -= seats.length;
                for(int i : seats)
                    theatre.vacantSeats.removeIf(number -> number.equals(i));

                // bookedTickets.put(user, bookedTickets.get(user).put(movie,));
            }
        }



    public int generateMovieId(){
        movieId++;
        return movieId;
    }
    }

     class User{
        private int uid;
        private String uname;
        User(int uid, String uname){
            this.uid = uid;
            this.uname = uname;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }
    }

     class Movie{
        private int mid;
        private String mtitle;
        private int rating;

        public Movie(int mid, String mtitle, int rating) {
            this.mid = mid;
            this.mtitle = mtitle;
            this.rating = rating;
        }

         public int getMid() {
             return mid;
         }

         public void setMid(int mid) {
             this.mid = mid;
         }

         public String getMtitle() {
            return mtitle;
        }

        public void setMtitle(String mtitle) {
            this.mtitle = mtitle;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

    }

     class Theatre{
        private int tid;
        private String tname;
        public int capacity;

        ArrayList<Integer> vacantSeats;

        public Theatre(int tid, String tname, int capacity) {
            this.tid = tid;
            this.tname = tname;
            this.capacity = capacity;
            this.vacantSeats = new ArrayList<>();
            for (int i = 1; i <= capacity; i++) {
                vacantSeats.add(i);
            }
        }

        public ArrayList<Integer> getVacantSeats(){
            return vacantSeats;
        }


        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }
    }

public class MovieReservationSystemLLD {

    public static void main(String[] args) {
        MovieReservationSystem system = new MovieReservationSystem();
        User user1 = new User(1, "A");
        User user2 = new User(2, "B");
        system.addNewUser(user1);
        system.addNewUser(user2);

        // Add some movies to the system
        Movie movie1 = new Movie(1, "The Matrix", 8);
        Movie movie2 = new Movie(2, "The Shawshank Redemption",  9);


        system.addNewMovie(movie1);
        system.addNewMovie(movie2);

        // Add some cinemas to the system
        Theatre theatre1 = new Theatre(1, "AMC Empire 25", 250);
        Theatre theatre2 = new Theatre(2, "Pacific Theatres at The Grove",  200);
        Theatre theatre3 = new Theatre(3, "Regal Cinema",  300);
        Theatre theatre4 = new Theatre(4, "Star Cinema",  350);
        system.addTheatreToMovie(movie1.getMid(), theatre1);
        system.addTheatreToMovie(movie1.getMid(), theatre2);
        system.addTheatreToMovie(movie2.getMid(), theatre3);
        system.addTheatreToMovie(movie2.getMid(), theatre4);

        ArrayList<Movie> movieList= system.getMovieList(); // gives the list of movies;
        for(Movie movie : movieList){
            System.out.println(movie.getMid() + ". " + movie.getMtitle());
        }

        system.getTheatreList(2);
        for(Theatre theatre : system.movieTheatreList.get(2))
            System.out.println(theatre.getTid() + ". " + theatre.getTname());

        System.out.println("Available seats at " + theatre3.getTname());
        ArrayList<Integer> available_seats = system.getVacantSeats(theatre3);

        for(int seat : available_seats)
            System.out.print( seat + ",");

        int[] seats = {2,3,4};
        system.bookMovieTicket(user1, movie2, theatre3, seats);
        System.out.println("Booked Ticket for user" );
        System.out.println("User: "+user1.getUname());
        System.out.println("Movie: " + movie2.getMtitle());
        System.out.println("Theatre: " +theatre3.getTname());
        System.out.println("Number of tickets booked : "+ seats.length);

        System.out.println("Available seats at " + theatre3.getTname());
        ArrayList<Integer> seats_left = system.getVacantSeats(theatre3);
        for(int seat : seats_left) {
            System.out.print(seat + ", ");
        }

    }
}
