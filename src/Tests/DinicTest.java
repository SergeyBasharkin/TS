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
        File file= new File("docs/DinicTest.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PrintWriter pw=null;
        //TODO это(3 следующие строчки) для проталкивания предпотока
        String [] a={"docs/500.txt","docs/1000.txt","docs/10000.txt","docs/25000.txt","docs/50000.txt","docs/75000.txt","docs/100000.txt","docs/120000.txt","docs/250000.txt","docs/500000.txt"};
        try {
            pw=new PrintWriter(new FileOutputStream(file,true));
            //TODO вставьте свой алгоритм
            for (String p:a) {
                long s = System.currentTimeMillis();
                long maxFlow = dinic(p,Integer.parseInt(p.substring(5,p.length()-4)));
                //TODO путь к файлу с графом
                long e = System.currentTimeMillis();
                pw.write("file: " + p + ", time: " + maxFlow + "ms, maxFlow: " + maxFlow + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            pw.flush();
            pw.close();
        }
    }
    public static long dinic(String p,int size) {
        MaxFlowDinic maxFlowDinic=new MaxFlowDinic();
        String path=p;
        List<MyGenerator.MyEdge> genGraph= ReadGrath.readEdges(path);
        List<MaxFlowDinic.Edge>[] graph = maxFlowDinic.createGraph(genGraph.size());
        for (MyGenerator.MyEdge edge:genGraph){
            maxFlowDinic.addEdge(graph,edge.getFrom(),edge.getTo(),edge.getCap());
        }
        long s=System.currentTimeMillis();
        int maxFlow=maxFlowDinic.maxFlow(graph,0,size);
        long e=System.currentTimeMillis();
        long result=e-s;
        //TODO опять же в генерации надо дабовить сток и исток
        return result;
    }
public static long pushRelabel(String p, int size ) {
        NewPushRelabel g = new NewPushRelabel();
        List<MyGenerator.MyEdge> genGraph= ReadGrath.readEdges(p);
        for (MyGenerator.MyEdge edge:genGraph){
            g.addEdge(edge.getFrom(),edge.getTo(),edge.getCap());
        }
        long s=System.currentTimeMillis();
        long maxFlow=g.getMinCutValue(0,size);
        System.out.println(maxFlow);
        long e=System.currentTimeMillis();
        long result=e-s;
        return result ;

    }
}
