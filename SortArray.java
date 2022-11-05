import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class SortArray {

    // region sort logic
    static void sortRecurse(int[] arr, int l, int r) {
        if (l < r) {
            int pivotIndex = getPivotIndex(arr, l, r);
            sortRecurse(arr, l, pivotIndex - 1);
            sortRecurse(arr, pivotIndex + 1, r);
        }

    } 

    private static int getPivotIndex(int[] arr, int l, int r) {
        int pivotVal = arr[r];

        int currPivotIndex = l - 1;
        
        for (int i = l; i <= r-1; i++) {
            if (arr[i] <  pivotVal) { 
                currPivotIndex ++;
                swapVal(arr, currPivotIndex, i);
            }

        }
        swapVal(arr, currPivotIndex+1, r);
        return currPivotIndex+1;
    }

    static void swapVal(int[] arr, int i, int j)
    {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    // endregion sort logic

    // region input/output
    static void generateInputFile(int n, String fileName) {
        try {
            int min = 0;
            int max = 2000000;
            File fout = new File(fileName);
            FileOutputStream fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (int i = 1; i <= n; i++) {
                String val = (int) ((Math.random() * (max - min)) + min) + "";
                bw.write(val);
                bw.newLine();
            }
            bw.close();
          } catch (FileNotFoundException e){
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }

    }

    static int[] readInput(int n, String fileName) throws NumberFormatException, IOException {
        Path path = Paths.get(fileName);
        int[] arr = new int[n];
        Charset charset = Charset.forName("UTF-8");
        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
          String line = null;
          int i = 0;
          while ((line = reader.readLine()) != null) {
            arr[i] = Integer.parseInt(line);
            i++;
            //System.out.println(line);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
        return arr;
    }

    static void generateOutput(int[] arr, String fileName) throws IOException {
        deleteFileIfExists(fileName);
        BufferedWriter bw =  new BufferedWriter(new FileWriter(fileName));
        for (int i = 0; i < arr.length; i++) {
            bw.write(Integer.toString(arr[i]));
            bw.newLine();
        }
        bw.flush();  
        bw.close();  
    }

    static void deleteFileIfExists(String fileName) {
        Path path = Paths.get(fileName);
        try {
            Files.deleteIfExists(path);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    // endregion input/output

    public static void main(String[] args) throws NumberFormatException, IOException {
        //int[] arr = { 10, 100, 150, 7, 8, 6, 1, 11 };
       
        int n = 1000000;
        String fileIn = "in.txt";
        String fileOut = "out.txt";

        generateInputFile(1000000, fileIn);
        System.out.println("File with random numbers in range from 0 to 2000000: " + fileIn + " created");
        int[] arr = readInput(n, fileIn);
        System.out.println("Read into array from file: " + fileIn + " completed");
        sortRecurse(arr, 0, n - 1);
        generateOutput(arr, fileOut);
        System.out.println("Sorted array write to file: " + fileOut + " completed");

    }
}