//import java.util.*;
//
//
// class Book {
//    private String title;
//
//    public Book(String title){
//        this.title = title;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
////    public List<String> getContent(){
////        List<String> content = new ArrayList<>();
////        for(String line: getLines(title)){
////            String[] words = line.split(" ");
////            for(String word: words)
////                content.add(word);
////        }
////        return content;
////    }
//}
//
// class BookStore {
//    private List<Book> books;
//    public BookStore(){
//        this.books = new ArrayList<>();
//    }
//
//    public void addBook(Book book){
//        this.books.add(book);
//    }
//
//    public List<Book> getBooks(){
//        return this.books;
//    }
//
//}
//
//interface  CharacterNameRepository {
//    Set<String> getCharacterNames();
//}
//
//// in case if we want to have another class that pulls data from database
//// we can have another class which implements the interface
//// then probably we can use factory design pattern  for both inorder to
//// decide which to use at runtime
//class CharacterNameRepositoryImpl implements CharacterNameRepository{
//    private Set<String> characterNames;
//    public CharacterNameRepositoryImpl(Set<String> characterNames){
//        this.characterNames = characterNames;
//    }
//
//    public Set<String> getCharacterNames(){
//        return this.characterNames;
//    }
//}
//
//interface CharacterNameCounter{
//    Map<String, Integer> countCharacterNames(BookStore bookStore);
//}
//
//
//class CharacterNameCounterImpl implements CharacterNameCounter{
//    private CharacterNameRepository characterNameRepository;
//    public CharacterNameCounterImpl(CharacterNameRepository characterNameRepository){
//        this.characterNameRepository = characterNameRepository;
//    }
////    public Map<String, Integer> countCharacterNames(BookStore bookStore){
////        Set<String> characterNamesSet = characterNameRepository.getCharacterNames();
////        Map<String, Integer> nameCount = new HashMap<>();
////        for(Book book : bookStore.getBooks()){
////            for(String word : book.getContent()){
////                if(characterNamesSet.contains(word)){
////                    nameCount.put(word, nameCount.getOrDefault(word, 0)+1);
////                }
////            }
////        }
////        return nameCount;
////    }
//}
//
//
//public class MainBook {
//    public static void main(String[] args) {
//        Set<String> characterNames = new HashSet<>();
//        characterNames.add("Harry Potter");
//        characterNames.add("Spider-Man");
//        characterNames.add("Hermione Granger");
//        characterNames.add("Ron Weasley");
//
//        // Initialize the repository with character names
//        CharacterNameRepository characterNameRepository = new CharacterNameRepositoryImpl(characterNames);
//
//        // Initialize the BookStore and add books
//        BookStore bookStore = new BookStore();
//        Book book1 = new Book("Book 1");
//        Book book2 = new Book("Book 2");
//        bookStore.addBook(book1);
//        bookStore.addBook(book2);
//
//        // Initialize the CharacterNameCounter with the repository
//        CharacterNameCounter characterNameCounter = new CharacterNameCounterImpl(characterNameRepository);
//        Map<String, Integer> nameCount = characterNameCounter.countCharacterNames(bookStore);
//
//        // Print the results
//        for (Map.Entry<String, Integer> entry : nameCount.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
//    }
//}
