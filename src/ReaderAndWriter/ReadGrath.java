package ReaderAndWriter;

import generatedGrath.MyGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Admin on 09.05.2016.
 */
public class ReadGrath {
    public static ArrayList<MyGenerator.MyEdge> readEdges(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Scanner reader;
        ArrayList<MyGenerator.MyEdge> edges = new ArrayList<>();
        try {
            reader = new Scanner(file);

            while (reader.hasNext()) {
                String buff = reader.nextLine();
                String[] edge = buff.split(" ");
                edges.add(new MyGenerator.MyEdge(Integer.parseInt(edge[0])
                        , Integer.parseInt(edge[1])
                        , Integer.parseInt(edge[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return edges;
    }
}
