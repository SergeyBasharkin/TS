package algorithms;

import generatedGrath.MyGenerator;

import java.util.ArrayList;

/**
 * Created by Admin on 09.05.2016.
 */
public class MaxFlowFordFulkerson {
    public static int maxFlow(ArrayList<MyGenerator.MyEdge> graph, int s,int t){
        for (int flow=0;;){
            int df= findPath(graph,new boolean[graph.size()],s,t,Integer.MAX_VALUE);
            if (df == 0)
                return flow;
            flow += df;
        }
    }
    static int findPath(ArrayList<MyGenerator.MyEdge> graph,boolean[] vis, int u,int t,int f){
        if (u==t) return f;
        vis[u]=true;
        MyGenerator.MyEdge edge;
        MyGenerator.MyEdge edge2 = null;
        for (int v = 0; v <vis.length ; v++) {
            if (!vis[v]&&((edge=contains(graph,u,v))!=null&&edge.getCap()>0)){
               int df=findPath(graph,vis,v,t,Math.min(f,edge.getCap()));
                edge=contains(graph,u,v);
                if (df>0){
                    graph.get(graph.indexOf(edge)).setCap(edge.getCap()-df);
                    if ((edge2=contains(graph,v,u))!=null)  graph.get(graph.indexOf(edge2)).setCap(edge2.getCap()+df);
                    graph.add(new MyGenerator.MyEdge(edge.getTo(),edge.getFrom(),df));
                    return df;
                }
            }
        }
        return 0;
    }
    private static MyGenerator.MyEdge contains(ArrayList<MyGenerator.MyEdge> graph, int u, int v){
        for (MyGenerator.MyEdge edge: graph){
            if(edge.getFrom()==u&&edge.getTo()==v){
                return edge;
            }
        }
        return null;
    }


    // Usage example
    public static void main(String[] args) {
        ArrayList<MyGenerator.MyEdge> graph= new ArrayList<>();
        graph.add(new MyGenerator.MyEdge(0,1,3));
        graph.add(new MyGenerator.MyEdge(0,2,2));
        graph.add(new MyGenerator.MyEdge(1,2,2));
       // int[][] capacity = { { 0, 3, 2 }, { 0, 0, 2 }, { 0, 0, 0 } };
        System.out.println(4 == maxFlow(graph, 0, 2));
    }
}