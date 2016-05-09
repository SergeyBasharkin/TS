package Tests;

import ReaderAndWriter.ReadGrath;
import algorithms.MaxFlowDinic;
import algorithms.PushRelabel;
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

        //TODO файл для вывода тестов
        File file= new File("docs/PushRelabelTest.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PrintWriter pw=null;
        //TODO это(3 следующие строчки) для проталкивания предпотока
        String path="docs/8000.txt";
        ArrayList<MyGenerator.MyEdge> genGraph= ReadGrath.readEdges(path);
        int cap[][] =MyGenerator.convertEdgeToCap(genGraph,8000);
        try {
            pw=new PrintWriter(new FileOutputStream(file,true));
            //TODO вставьте свой алгоритм
            long s =System.currentTimeMillis();
            int maxFlow=pushRelabel(cap);
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
        String path="docs/4000.txt";
        List<MyGenerator.MyEdge> genGraph= ReadGrath.readEdges(path);
        List<MaxFlowDinic.Edge>[] graph = maxFlowDinic.createGraph(genGraph.size());
        for (MyGenerator.MyEdge edge:genGraph){
            maxFlowDinic.addEdge(graph,edge.getFrom(),edge.getTo(),edge.getCap());
        }
        //TODO опять же в генерации надо дабовить сток и исток
        return maxFlowDinic.maxFlow(graph,0,4000);
    }
    public static int pushRelabel(int[][] cap) {
//        String path="docs/4000.txt";
//        ArrayList<MyGenerator.MyEdge> genGraph= ReadGrath.readEdges(path);
//        int cap[][] =MyGenerator.convertEdgeToCap(genGraph,4000);
        PushRelabel pushRelabel=new PushRelabel(cap);
        return pushRelabel.maxFlow(0,cap.length-1);
    }
}
