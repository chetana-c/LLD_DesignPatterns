/*
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class File{
        private final int id;
        private final String name;

        private Folder parentFolder;
        public File(int id, String name){
            this.id = id;
            this.name = name;
            this.parentFolder = parentFolder;
        }

        public Folder getParentFolder(){
            return parentFolder;
        }

        void setParentFolder(Folder parentFolder){
            this.parentFolder = parentFolder;
        }
         int getId(){
            return id;
        }

         String getName(){
            return name;
        }
    }

    class Folder extends File{
        private List<File> files;
        private Map<String, Integer> filesByName;
        private int dashboards;
        private int worksheets;
        public Folder(int id, String name) {
            super(id, name);
            this.files = new ArrayList<File>();
        }

         void addFile(File file){
            if(file instanceof Worksheet)  worksheets++;
            if(file instanceof Dashboard) dashboards++;
            files.add(file);
            file.setParentFolder(this);
            filesByName.put(file.getName(), file.getId());

        }

        public int getCountOfDashboardsinFolder(){
            return dashboards;
        }

        public int getCountOfWorksheetsinFolder(){
            return worksheets;
        }
        List<File> getFiles(){
            return files;
        }

        public int getFilesId(String Name){
            return filesByName.get(Name);
        }

    }

    class Dashboard extends File{

        public Dashboard(int id, String name) {
            super(id, name);
        }
    }

    class Worksheet extends File{
        public Worksheet(int id, String name) {
            super(id, name);
        }
    }

     class CustomException extends Exception {
        public CustomException(String message, Throwable cause) {
            super(message, cause);
        }
    }


    public class FileSystem {
        private Folder root;
        private HashMap<Integer, File> filesById;
        private int numDashboards;
        private  int  numWorksheets;
        private int id;

        public FileSystem() {
            this.root = new Folder(0, "MyDocuments");
            this.numDashboards = 0;
            this.numWorksheets = 0;
            this.filesById = new HashMap<>();
            this.id = 0;
            filesById.put(0, root);
        }

        public int getTotalDashboards(){
            return numDashboards;
        }

        public int getTotalWorksheets(){
            return numWorksheets;
        }

        public void addNewFile(String fileName, String fileType, int folderId) throws CustomException {
            try {
                File file = null;
                switch (fileType) {
                    case "folder":
                        file = new Folder(generateId(), fileName);
                        break;
                    case "dashboard":
                        file = new Dashboard(generateId(), fileName);
                        numDashboards++;
                        break;
                    // if there is any other file type other than worksheet, it should ideally throw an exception
                    // which is not handled here, the switch case cna be changed to a for loop
                    default:
                        file = new Worksheet(generateId(), fileName);
                        numWorksheets++;
                        break;
                }

                Folder parent = (Folder) filesById.get(folderId);

                parent.addFile(file);
                filesById.put(file.getId(), file);
                return;
            }
            catch(Exception e){
                throw new CustomException("Invalid fileType : ", e);
            }
        }



        int generateId(){
            id++;
            return id;
        }
      */
/*  // using lock
        private Lock lock = new ReentrantLock();
        int generateId(){
         lock.lock();
        try {
            id++;
            return id;
        } finally {
            lock.unlock();
        }
        }

        // using synchronised method - it ensures that only one thread can execute the method at a time
        public synchronised generateId(){
        id++;
        return id;
        }*//*





        public int getFileId(String fileName,  int folderId) throws CustomException{
            try {
                Folder parent = (Folder) filesById.get(folderId);

                return parent.getFilesId(fileName);
//            for (File file : parent.getFiles()){
//                if(file.getName().equals(fileName)){
//                    return file.getId();
//                }
//            }
//            return 0;
            }
            catch(Exception e){
                throw new CustomException("Invalid file name: ", e);
            }
        }

        public void moveFile(int fileId, int folderId) throws CustomException{
            try {
                File file = filesById.get(fileId);
                Folder newParent = (Folder) filesById.get(folderId);
                Folder oldParent = (Folder) getParentId(fileId);

                oldParent.getFiles().remove(file);
                newParent.addFile(file);
                return;
            }
            catch(Exception e){
                throw new CustomException("Invalid file id : ", e );
            }
        }

        File getParentId(int fileId){
            File file = filesById.get(fileId);

            for(File parent : filesById.values()){
                if(parent instanceof Folder && ((Folder) parent).getFiles().contains(file))
                    return parent;
            }
            return null;

//            Folder parentFolder = file.getParentFolder();
//            if(parentFolder == null)
//                return null
//             else
//                return parentFolder;


        }

        String[] getFiles(int folderId) throws CustomException{
            try {
                Folder folder = (Folder) filesById.get(folderId);
                List<File> files = folder.getFiles();
                String[] listOfFiles = new String[folder.getFiles().size()];
                int i = 0;
                for (File file : files) {
                    listOfFiles[i] = file.getName();
                    i++;
                }

                return listOfFiles;
            }
            catch(Exception e){
                throw new CustomException("Invalid folder id" , e);
            }
        }

        void PrintFiles(){
            print((Folder) filesById.get(0));
        }

        void print(Folder folder){
            for(File file : folder.getFiles()){
                System.out.println(file.getName());

                if(file instanceof Folder){
                    System.out.println((Folder) file);
                }
            }
        }
    }

    /// implement caching
*/
/*

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

// ...

// Create a Caffeine cache with a maximum size of 1000 entries and a 10-minute expiry
Cache<String, File> fileCache = Caffeine.newBuilder()
  .maximumSize(1000)
  .expireAfterWrite(10, TimeUnit.MINUTES)
  .build();

// Update the getFileById method to check the cache before looking up the file in the file system:
public File getFileById(String id) {
    File file = fileCache.getIfPresent(id);
    if (file == null) {
        // File not found in cache, look it up in the file system
        file = findFileById(id);
        if (file != null) {
            // Add the file to the cache
            fileCache.put(id, file);
        }
    }
    return file;
}

//Update the addFile method to add the new file to the cache:

public void addFile(File file, String parentId) {
    // Add the file to the file system
    addFileToFileSystem(file, parentId);

    // Add the file to the cache
    fileCache.put(file.getId(), file);
}

 *//*



*/
