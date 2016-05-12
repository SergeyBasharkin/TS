package generatedGrath;

import com.sun.javafx.geom.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by sergey on 02.05.16.
 */
public class MyGenerator {
    public static void main(String[] args) {
        ArrayList<MyEdge> edges=generate2(0,5);
        for (MyEdge edge:edges){
            System.out.println("from:"+edge.from+" to:"+edge.to+" cap:"+edge.cap);
        }
        int[][] capa=convertEdgeToCap(edges,5);
        for (int i = 0; i <6 ; i++) {
            for (int j = 0; j <6 ; j++) {
                System.out.print(capa[i][j]+" ");
            }
            System.out.println();
        }
    }
    public ArrayList<MyEdge> generateList(){
        return generate2(0,60000);
    }
    public static class MyEdge {
        private int from;
        private int to;
        private int cap;
        private boolean vis;

        public boolean isVis() {
            return vis;
        }

        public void setVis(boolean vis) {
            this.vis = vis;
        }

        public int f;

        public MyEdge(int from, int to, int cap)  {
            this.from = from;
            this.to = to;
            this.cap = cap;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getCap() {
            return cap;
        }

        public void setCap(int cap) {
            this.cap = cap;
        }
    }


    //TODO в генераторе надо указывать исток и сток.
    public static ArrayList<MyEdge> generate2(int s,int t){
        Random random=new Random();
        ArrayList<MyEdge> edges=new ArrayList<>();
        int from;
        int to;
        int c;
        for (int i=s;i<t;i++){
            from=i;
            to=random.nextInt(t-i+1)+i;
            while (to==from){
                to=random.nextInt(t-i+1)+i;
            }
            c=random.nextInt(10000)+10000;
            edges.add(new MyEdge(from,to,c));
        }
        boolean vseOk;
        for (int i = s+1; i <t ; i++) {
            vseOk=false;
            for (MyEdge edge:edges) {
                if (edge.getTo()==i){
                    vseOk=true;
                }
            }
            if(!vseOk){
                from=random.nextInt(i);
                to=i;

                c=random.nextInt(10000)+10000;
                edges.add(new MyEdge(from,to,c));
            }
        }
        return edges;
    }
    public static int[][] convertEdgeToCap(ArrayList<MyEdge> edges,int t){
        int[][] capa=new int[t+1][t+1];
        for (int i = 0; i <t+1 ; i++) {
            for (int j = 0; j <t+1 ; j++) {
                capa[i][j]=0;
            }
        }
        for (MyEdge edge:edges) {
            capa[edge.from][edge.to]=edge.cap;
        }
        return capa;
    }

}
