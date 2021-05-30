package lesson5.hw5;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AppData {
    //Реализовать сохранение данных в csv файл;
    //Реализовать загрузку данных из csv файла. Файл читается целиком.
    private String[] header;
    private Integer[][] data;

    public AppData() {
        header = new String[]{"value1", "value2", "value3"};
        data = new Integer[][]{{1, 2, 3}, {1, 3, 5}, {5, 6, 7}};
    }

    public void save(String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(rowToString(header));
            for (Integer[] array : data) {
                bufferedWriter.write(rowToString(array));
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <T> String rowToString(T[] row) {
        String result = "";
        for (int i = 0; i < row.length; i++) {
            if (i < row.length - 1) {
                result = result + row[i] + ";";
            } else {
                result = result + row[i];
            }
        }
        result += "\n";
        return result;
    }

    public void load(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            header = bufferedReader.readLine().split(";");
            String line;
            ArrayList<Integer[]> arrayList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                arrayList.add(stringToIntArray(line));
            }
            data = arrayList.toArray(new Integer[][]{});
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer[] stringToIntArray(String str) {
        String[] stringArray = str.split(";");
        Integer[] intArray = new Integer[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        return intArray;
    }

    public static void main(String[] args) {
        AppData appData = new AppData();
        // appData.save("test.csv");
        appData.load("test.csv");
        System.out.println(Arrays.toString(appData.header));
        System.out.println(Arrays.deepToString(appData.data));
    }
}


