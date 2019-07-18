package ru.geekbrains.java3.lesson3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

public class Main {
    public static final int PAGE_LEN = 1800;
    public static void main(String[] args) {
	// write your code here
//        File test = new File("d:\\text.txt");
//        for (int i = 0; i < 100; i++) {
//            try(FileOutputStream fileOutputStream = new FileOutputStream(test,true)){
//                fileOutputStream.write((i + " Test line" + System.lineSeparator()).getBytes());
//            }catch (FileNotFoundException e){
//                e.printStackTrace();
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }

//        task1("d:\\text.txt");

//        task2("d:\\task2.txt","d:\\1.txt","d:\\2.txt","d:\\3.txt");
//        task1("d:\\task2.txt");

        // 10Mb file
//        File test = new File("d:\\task3.txt");
//        for (int i = 1; i < 10001; i++) {
//            try(FileOutputStream fileOutputStream = new FileOutputStream(test,true)){
//
//                for (int j = 1; j < 31; j++) {
//                    String page = String.format("LINE%3s PAGE%46s","" + j,"" + i) + System.lineSeparator();
//                    fileOutputStream.write(page.getBytes());
//                }
//            }catch (FileNotFoundException e){
//                e.printStackTrace();
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }

        task3("d:\\task3.txt");
    }

    public static void task1(String fileName){
        try (FileInputStream fileInputStream = new FileInputStream(fileName);){
            byte[] array = new byte[50];
            while (fileInputStream.available() > 0){
                int count = fileInputStream.read(array);
                for (int i = 0; i < count; i++) {
                    System.out.printf("" + (char) array[i]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void task2(String outputFile,String...inputFiles){
        ArrayList<FileInputStream> fileInputStreams = new ArrayList<>();
        for (String s :
                inputFiles) {
            try {
                fileInputStreams.add(new FileInputStream(s));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try(FileOutputStream outputStream = new FileOutputStream(outputFile);
            SequenceInputStream allInputStream = new SequenceInputStream(Collections.enumeration(fileInputStreams));
        ) {
            while (true){
                System.out.println(allInputStream.available());
                byte[] array = new byte[128];
                int count = allInputStream.read(array);
                if (count > 0){
                    outputStream.write(array,0,count);
                }else{
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void task3(String fileName){
        try {
            File file = new File(fileName);
            long size = file.length();
            int pages = (int) Math.floor(size / 1.0 / PAGE_LEN);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file,"r");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String result = "";
            do{
                try{
                    System.out.println("Enter page number [1 / " + pages + "]: ");
                    result = reader.readLine();
                    long page = Integer.parseInt(result);
                    if (page > 0 && page <= pages) {
                        long start = System.currentTimeMillis();
                        randomAccessFile.seek((page - 1) * PAGE_LEN);
                        byte[] array = new byte[PAGE_LEN];
                        int count = randomAccessFile.read(array);
                        for (int i = 0; i < count; i++) {
                            System.out.printf("" + (char) array[i]);
                        }
                        System.out.println("\nExecution time: " + (System.currentTimeMillis() - start));
                    }else {
                        System.out.println("Can not execute - try again");
                    }
                }catch (NumberFormatException e){
                    System.out.println("Wrong input ! Should be number value");
                }
            }while (!result.equals(""));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
