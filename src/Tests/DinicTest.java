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
        //TODO файл для вывода тестов
        File file= new File("docs/DinicTest.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PrintWriter pw=null;
        String path="docs/5000.txt";
        try {
            pw=new PrintWriter(new FileOutputStream(file,true));
            //TODO вставьте свой алгоритм
            int maxFlow=dinic();
            //TODO путь к файлу с графом
            long e =System.currentTimeMillis();
            pw.write("file: "+path+", time: "+(e-s)+"ms, maxFlow: "+maxFlow+"\n");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            pw.flush();
            pw.close();
        }
    }
    public static int dinic() {
        MaxFlowDinic maxFlowDinic=new MaxFlowDinic();
        String path="docs/5000.txt";
        List<MyGenerator.MyEdge> genGraph= ReadGrath.readEdges(path);
        List<MaxFlowDinic.Edge>[] graph = maxFlowDinic.createGraph(genGraph.size());
        for (MyGenerator.MyEdge edge:genGraph){
            maxFlowDinic.addEdge(graph,edge.getFrom(),edge.getTo(),edge.getCap());
        }
        long e =System.currentTimeMillis();
        //TODO опять же в генерации надо дабовить сток и исток
        return maxFlowDinic.maxFlow(graph,0,5000);
    }
    public static int pushRelabel() {
        return 0;
    }
}
