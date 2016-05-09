package ReaderAndWriter;

import generatedGrath.MyGenerator;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Admin on 09.05.2016.
 */
public class WriteGrath {
    public  static void main(String[] args) {
        //TODO путь к файлу, куда записывать граф 100 500 1.000 4.000 8.000 12.000 20.000 50.000 100.000 250.000 500.000
        File file= new File("docs/500000.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PrintWriter pw=null;
        try {
           pw=new PrintWriter(new FileOutputStream(file));
            //TODO в генераторе надо указывать исток и сток.
            ArrayList<MyGenerator.MyEdge> edges=MyGenerator.generate2(0,500000);
            for (MyGenerator.MyEdge edge:edges){
                pw.write(edge.getFrom()+" "+edge.getTo()+" "+edge.getCap()+"\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            pw.flush();
            pw.close();
        }
    }
}
