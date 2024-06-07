import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExcelDesign {

    Map<Integer, Map<Integer, String>> data = new HashMap<>();
    List<ExcelDesign> spreadsheets = new ArrayList<>();
    private List<String> columnNames;
    int rows;
    int cols;
    ExcelDesign(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.columnNames = new ArrayList<>();
        this.data = new HashMap<>();
        this.spreadsheets = new ArrayList<>();
        spreadsheets.add(this);
    }

    public void setValue(int row, int col, String value){
        Map<Integer, String> columnMap = data.get(row);
        if(columnMap == null){
            columnMap = new HashMap<>();
            data.put(row, columnMap);
        }
        columnMap.put(col, value);
    }

    public String getValue(int row, int col){
        Map<Integer , String> columnMap = data.get(row);
        if(columnMap != null){
            return columnMap.get(col);
        }
        return null;
    }

    public void setColumnNames(List<String> columnNames){
        this.columnNames =  columnNames;
    }

    public List<String> getColumnNames(){
        return columnNames;
    }

    public void addColumn(int index, String colName){
        // adding the col to the existing list of cols
        columnNames.add(colName);
        for(int i=0; i<rows; i++){
            // adding empty rows for that column
            data.get(i).put(index, " ");
        }

        // incrementing the number of cols
        cols++;
    }

    public void addRow(int index) {
        // Add a new empty row to the data array
        Map<Integer, String> newRow = new HashMap<>();
        for (int i = 0; i < cols; i++) {
            newRow.put(i , " ");
        }
        data.put(index, newRow);

        // Increment the number of rows
        rows++;
    }

    public void deleteColumn(int index, String colName){
        // removing the column from the existing list of cols
        columnNames.remove(colName);
        for(int i=0; i<rows; i++){
            data.get(i).remove(index);
        }

        cols--;
    }

    public int getSum(int colindex){
        int sum =0;
        for(int i = 0; i<rows; i++){
            sum += Integer.parseInt(data.get(i).get(colindex));
        }
        return sum;
    }

    public int getAverage(int colindex){
        int sum =0;
        int count = 0;
        for(int i = 0; i<rows; i++){
            sum += Integer.parseInt(data.get(i).get(colindex));
            count++;
        }
        return sum/count;
    }

    public void sort(int colIndex){
     int[] arr = new int[rows];
     for(int i=0; i<rows; i++){
         arr[i] = Integer.parseInt(data.get(i).get(colIndex));
     }
     quicksort(arr, 0, rows-1);
    }

    public void quicksort(int[] arr, int low, int high){
        if(low < high){
            int pivot = partition(arr, low, high);
            quicksort(arr, low, pivot);
            quicksort(arr, pivot+1, high);
        }
    }

    public int partition(int[] arr, int low, int high){
        int pivot = arr[low];
        int i = low;
        int j = high;
        while(i < j){
            while(arr[i] < pivot)
                i++;
            while(arr[i] > pivot)
                j--;
            if(i<j)
                swap(arr, i, j);
        }
        swap(arr, low, j);
        return j;
    }

    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void deleteRow(int index){
        data.remove(index);
        rows--;
    }

    public Map<Integer, Map<Integer, String>> filtering(int colIndex, String value){
        Map<Integer, Map<Integer, String>> newData = new HashMap<>();
        for(Map.Entry<Integer, Map<Integer, String>> set : data.entrySet()){
            if(set.getValue().get(colIndex).equals(value))
                newData.put(set.getKey(), set.getValue());
        }
        return newData;
    }
    public List<ExcelDesign> getSpreadsheets(){
        return spreadsheets;
    }

    public void addSpreadsheets(ExcelDesign spreadsheet){
        spreadsheets.add(spreadsheet);
    }


    public void printSpreadsheet() {
        // Print the column names
        System.out.print("\t");
        for (String columnName : columnNames) {
            System.out.print(columnName + "\t");
        }
        System.out.println();

        // Print the data
        for (int i = 0; i < rows; i++) {
            System.out.print((i+1) + "\t");
            for (int j = 0; j < cols; j++) {
                System.out.print(data.get(i).get(j) + "\t");
            }
            System.out.println();
        }
    }

}
