package Tests;

import ReaderAndWriter.ReadGrath;
import algorithms.MaxFlowDinic;
import generatedGrath.MyGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 09.05.2016.
 */
public class DinicTest {
    public static void main(String[] args) {
        long s =System.currentTimeMillis();
        File file= new File("docs/DinicTest.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        MaxFlowDinic maxFlowDinic=new MaxFlowDinic();
        PrintWriter pw=null;
        try {
            pw=new PrintWriter(new FileOutputStream(file,true));
            String path="docs/5000.txt";
            List<MyGenerator.MyEdge> genGraph= ReadGrath.readEdges(path);
            List<MaxFlowDinic.Edge>[] graph = maxFlowDinic.createGraph(genGraph.size());
            for (MyGenerator.MyEdge edge:genGraph){
                maxFlowDinic.addEdge(graph,edge.getFrom(),edge.getTo(),edge.getCap());
            }
            long e =System.currentTimeMillis();
            pw.write("file: "+path+", time: "+(e-s)+"ms, maxFlow: "+maxFlowDinic.maxFlow(graph,0,5000)+"\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            pw.flush();
            pw.close();
        }
    }
}
