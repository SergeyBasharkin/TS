package ReaderAndWriter;

import generatedGrath.MyGenerator;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Admin on 09.05.2016.
 */
public class WriteGrath {
    public static void main(String[] args) {
        File file= new File("docs/5000.txt");
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
            ArrayList<MyGenerator.MyEdge> edges=MyGenerator.generate2(0,5000);
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
