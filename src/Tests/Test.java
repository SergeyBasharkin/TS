package tests;

import ReaderAndWriter.ReadGrath;
import algorithms.*;
import generatedGrath.MyGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 12.05.2016.
 */
public class Test {
    public static void main(String[] args) throws IOException {


        File file=new File("docs/test.txt");
        if (!file.exists()) file.createNewFile();
        FileWriter fileWriter=new FileWriter(file,true);
        Integer[] p = {500000};
        for (Integer size : p) {
            long allFord=0;
            long allDinic=0;
            long allEd=0;
            long allPush=0;
            for (int i = 0; i < 5; i++) {
                MaxFlowFordFulkerson maxFlowFordFulkerson = new MaxFlowFordFulkerson();
                MaxFlowDinic maxFlowDinic = new MaxFlowDinic();
                MaxFlowEdmondsKarp edmondsKarp = new MaxFlowEdmondsKarp();
                NewPushRelabel newPushRelabel=new NewPushRelabel();
                ArrayList<MyGenerator.MyEdge> genGraph =MyGenerator.generate2(0,size);
                List<MaxFlowDinic.Edge>[] graph = maxFlowDinic.createGraph(genGraph.size());
                List<Edge>[] graphEd = edmondsKarp.createGraph(genGraph.size());
                for (MyGenerator.MyEdge edge:genGraph){
                    maxFlowDinic.addEdge(graph,edge.getFrom(),edge.getTo(),edge.getCap());
                    newPushRelabel.addEdge(edge.getFrom(),edge.getTo(),edge.getCap());
                    edmondsKarp.addEdge(graphEd,edge.getFrom(),edge.getTo(),edge.getCap());
                }

               // int[][] capa=MyGenerator.convertEdgeToCap(genGraph,size);

                long sFord=System.currentTimeMillis();
               // maxFlowFordFulkerson.maxFlow(capa,0,size);
                long resultFrod=System.currentTimeMillis()-sFord;
                long sDinic=System.currentTimeMillis();
                maxFlowDinic.maxFlow(graph,0,size);
                long resultDinic=System.currentTimeMillis()-sDinic;
                long sEdmonds=System.currentTimeMillis();
                edmondsKarp.maxFlow(graphEd,0,size);
                long resultEdmonds=System.currentTimeMillis()-sEdmonds;
                long sNewPush=System.currentTimeMillis();
                newPushRelabel.getMinCutValue(0,size);
                long resultPush=System.currentTimeMillis()-sNewPush;
                newPushRelabel=null;
                maxFlowDinic=null;
                edmondsKarp=null;

                allFord+=resultFrod;
                allDinic+=resultDinic;
                allEd+=resultEdmonds;
                allPush+=resultPush;
                System.out.println("done:"+i+" Ford:"+resultFrod+" Dinic:"+resultDinic+" Ed:"+resultEdmonds+" Push:"+resultPush);
            }
            long resultFord=allFord/10;
            long resultDinic=allDinic/10;
            long resultEd=allEd/10;
            long resultPush=allPush/10;

            System.out.println("done:"+size);

            fileWriter.write("Ford:"+resultFord+"; Dinic:"+resultDinic+"; Ed:"+resultEd+"; Push:"+resultPush+"\n");
        }
        fileWriter.close();
    }
}
