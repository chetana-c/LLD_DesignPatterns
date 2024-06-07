/*
 * Click `Run` in the top left corner to run the command line program. Output will appear in the "Program Output" tab in the right pane.
 */

import java.util.*;

// Creating a base class whose properties such as getId and getName can be used in any class that inherits this base class
class File{
  private final int id;
  private final String name;

  public File(int id, String name){
    this.id = id;
    this.name = name;
  }

  public int getId(){
    return id;
  }

  public String getName(){
    return name;
  }

}

// Creating a Folder class which is inheriting the base class "File" features
// Since given a file can be a folder 
// Adding new properties to this class to add and fetch the files in this folder
class Folder extends File{
  private final List<File> files;

  public Folder(int id, String name){
    super(id, name);
    this.files = new ArrayList<>();
  }

  public void addFile(File file){
    files.add(file);
  }

  public List<File> getFiles(){
    return files;
  }

}

// Creating a Dashboard class which is inheriting the base class "File" features
// Since given a file can be a Documents which can be a Dashboard
class Dashboard extends File{
  public Dashboard(int id, String name){
    super(id, name);
  }
}

// Creating a Worksheet class which inherits the base class "File" features
// Since given a files can be Documents which can be worksheets
class Worksheet extends File{
  public Worksheet(int id, String name){
    super(id, name);
  }
}

class FileSystem {

  private final Folder root;
  private final Map<Integer, File> filesById;
  private int numDashboards;
  private int numWorkSheets;
  private int iD;

    // Initialising the general variables of a file system
    public FileSystem() { 
        this.root = new Folder(0, "MyDocuments");
        this.filesById = new HashMap<>();
        this.numDashboards = 0;
        this.numWorkSheets = 0;
        this.iD = 0;
        filesById.put(0, root);
    }
    
    // Feel free to modify the parameter/return types of these functions
    // as you see fit. Please add comments to indicate your changes with a 
    // brief explanation. We care more about your thought process than your
    // adherence to a rigid structure.
    
    // Method to fetch total number of dashboards in the file system
    int getTotalDashboards() {
      return numDashboards;
    }
  
    // Method to fetch the total number of worksheets in the file system
    int getTotalWorksheets() {
      return numWorkSheets;
    }
    
    // Method to add a new file to the file system which can be a folder or a dashboard or worksheet
    void addNewFile(String fileName, String fileType, int folderId) { 
        File file = null;
        switch(fileType){
          case "folder" : 
            file = new Folder(generateNewId(), fileName);
            break;
          case "dashboard" :
            file = new Dashboard(generateNewId(), fileName);
            numDashboards++;
            break;
          default :
            file = new Worksheet(generateNewId(), fileName);
            numWorkSheets++;
        }

        Folder parent = (Folder)filesById.get(folderId);

        parent.addFile(file);
        filesById.put(file.getId(), file);
      return;
    }
    
    // Method to fetch the unique file id for a folder or a document
    int getFileId(String fileName, int folderId) {
      Folder parent = (Folder)filesById.get(folderId);
      
      for(File file : parent.getFiles()){
        if(file.getName().equals(fileName)){
          return file.getId();
        }
      }
      return 0;
    }
    
    // Method to move an existing file from a source location to a new destination location by
    // detaching the file from the existing parent and adding it to the desired new parent folder
    void moveFile(int fileId, int newFolderId) {
      File file = filesById.get(fileId);

      Folder newParent = (Folder)filesById.get(newFolderId);
      Folder oldParent = (Folder) getParent(fileId);
      oldParent.getFiles().remove(file);
      newParent.addFile(file);
      return;
    }

    // Method to fetch the parent location of a file
    File getParent(int fileId){
      File file = filesById.get(fileId);

      for(File parent : filesById.values()){
        if(parent instanceof Folder && ((Folder)parent).getFiles().contains(file))
        return parent;
      }

      return null;
    }

    // Method to generate a unique file id
    int generateNewId(){
      iD++;
      return iD;
    }

    String[] getFiles(int folderId) {
      Folder folder = (Folder) filesById.get(folderId);
      String[] res = new String[folder.getFiles().size()];
      List<File> filesInFolder = folder.getFiles();
      int i = 0;
      for(File f : filesInFolder){
        res[i] = f.getName();
        i++;
      }

      return res;
    }
    
    // Method to print the file system hierarchy
    void printFiles() {
      System.out.println("Printing the file system now : ");
      System.out.println(root.getName());
      printRecursively((Folder)filesById.get(0), 1);
      return;
    }

    void printRecursively(Folder folder, int level){
      for(File file : folder.getFiles()){
        for(int i = 0; i < level; i++){
          System.out.print("   ");
        }
        System.out.println(file.getName());

        if(file instanceof Folder){
          printRecursively((Folder) file, level + 1);
        }
      }
     
    }
    
}


    
/////////////////////////////////////////////////////////
// YOU DO NOT NEED TO MAKE CHANGES BELOW UNLESS NECESSARY
/////////////////////////////////////////////////////////


/*
 * Note from Coder Pad: To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
class Solution {
  
  private static int askForInteger(Scanner scanner, String question) {
    System.out.println(question);
    try {
      return Integer.parseInt(scanner.nextLine());
    } catch (Exception e) {
      System.out.println("Please enter a valid integer.");
      return askForInteger(scanner, question);
    }
  }
  
  // Please ensure this function compiles and runs before submitting!
  public static void runExample() {
    FileSystem fs = new FileSystem();
    int rootId = fs.getFileId("MyDocuments", 0);
    fs.addNewFile("draft", "folder", rootId);
    fs.addNewFile("complete", "folder", rootId);
    int draftId = fs.getFileId("draft", rootId);
    int completeId = fs.getFileId("complete", rootId);
    fs.addNewFile("foo", "worksheet", draftId);
    fs.addNewFile("bar", "dashboard", completeId);
    int fooId = fs.getFileId("foo", draftId);
    fs.moveFile(fooId, completeId);
    // System.out.println("The root id is : " + rootId);
    // System.out.println("The draft id is : " + draftId);
    // System.out.println("The complete id is : " + completeId);
    System.out.println(String.join(", ", fs.getFiles(rootId)));
    System.out.println(String.join(", ", fs.getFiles(draftId)));
    System.out.println(String.join(", ", fs.getFiles(completeId)));
          
    fs.addNewFile("project", "folder", draftId);
    int projectId = fs.getFileId("project", draftId);
    fs.addNewFile("page1", "worksheet", projectId);
    fs.addNewFile("page2", "worksheet", projectId);
    fs.addNewFile("page3", "worksheet", projectId);
    fs.addNewFile("cover", "dashboard", projectId);
    fs.moveFile(projectId, completeId);
    projectId = fs.getFileId("project", completeId);
    int coverId = fs.getFileId("cover", projectId);
    fs.moveFile(coverId, rootId);
    
    System.out.println(String.join(", ", fs.getFiles(rootId)));
    System.out.println(String.join(", ", fs.getFiles(draftId)));
    System.out.println(String.join(", ", fs.getFiles(completeId)));
    System.out.println(String.join(", ", fs.getFiles(projectId)));

    System.out.println(fs.getTotalDashboards());
    System.out.println(fs.getTotalWorksheets());
    fs.printFiles();
  }

  public static void askQuestion() {
      boolean running = true;
      Scanner scanner = new Scanner(System.in);
      FileSystem fs = new FileSystem();
      int command;
      while (running) {
        command = askForInteger(scanner, "\nEnter an integer to indicate a command: \n[1] getTotalDashboards\n[2] getTotalWorksheets\n[3] addNewFile\n[4] getFileId\n[5] moveFile\n[6] getFiles \n[7] printFiles\n[8] exit\n");
        switch (command) {
          case 1: {
            int totalDashboards = fs.getTotalDashboards();
            System.out.println(String.format("There are %d dashboards in the file system.", totalDashboards));
            break;
          }
          case 2: {
            int totalWorksheets = fs.getTotalWorksheets();
            System.out.println(String.format("There are %d worksheets in the file system.", totalWorksheets));
            break;
          }
          case 3: {
            System.out.println("Enter a new file name:");
            String fileName = scanner.nextLine();
            System.out.println("Enter a file type (worksheet, dashboard, or folder)");
            String fileType = scanner.nextLine();
            int folderId = askForInteger(scanner, "Enter a folder id where you'd like to put this file");
            fs.addNewFile(fileName, fileType, folderId);
            System.out.println(String.format("%s has been added to folder %d", fileName, folderId));
            break;
          }
          case 4: {
            System.out.println("Enter a file name:");
            String fileName = scanner.nextLine();
            int folderId = askForInteger(scanner, "Enter a folder id:");
            int fileId = fs.getFileId(fileName, folderId);
            System.out.println(String.format("%d is file %d", fileName, fileId));
            break;
          }
          case 5: {
            int fileId = askForInteger(scanner, "Enter a file id:");
            int newFileId = askForInteger(scanner, "Enter the folder id where you'd like to move this file.");
            fs.moveFile(fileId, newFileId);
            System.out.println(String.format("Successfully moved file %d to folder %d", fileId, newFileId));
            break;
          }
          case 6: {
            int folderId = askForInteger(scanner, "Enter a folder id:");
            String[] fileNames = fs.getFiles(folderId);
            if (fileNames.length == 0) {
              System.out.println(String.format("There are no files in folder %d", folderId));
            } else {
              System.out.println(String.format("The following files are in folder %d:", folderId));
              for (String fileName: fileNames) {
                System.out.println(String.format("\t%s", fileName));
              }
            }
            break;
          }
          case 7: {
            fs.printFiles();
            break;
          }
          case 8: {
            System.out.println("Exiting program.");
            running = false;
            scanner.close();
            break;
          }
          default:
            System.out.println(String.format("Invalid command: %d. Please try again.\n",command));
        }
     }
  }
  
  // Feel free to modify this main function as you see fit. 
  public static void main(String[] args) {
    System.out.println("runExample() output:");
    runExample();
    System.out.println("askQuestion() output:");
    askQuestion();
  }
}
