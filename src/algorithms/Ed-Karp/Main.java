import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


/**
 * Created by adelzamalutdinov on 02.05.16.
 */
public class Main {
    public static void main(String[] args) {
        File file = new File("KarpTest.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PrintWriter pw = null;
        int p[] = {500, 1000, 5000, 10000, 25000, 50000, 75000, 100000, 120000, 250000, 500000};
        try {
            pw = new PrintWriter(new FileOutputStream(file, true));
            for (int a : p) {
                pw.write(a + ": " + testAlg(a) + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            pw.flush();
            pw.close();
        }
    }

    public static long testAlg(int num) {
        MaxFlowEdmondsKarp flow = new MaxFlowEdmondsKarp();
        List<Edge>[] graph = flow.createGraph(num + 1);
        File file = new File(num + ".txt");
        if (!file.exists()) {
            try {
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Scanner reader;
        try {
            reader = new Scanner(file);

            while (reader.hasNext()) {
                String buff = reader.nextLine();
                String[] edge = buff.split(" ");
                flow.addEdge(graph, Integer.parseInt(edge[0])
                        , Integer.parseInt(edge[1])
                        , Integer.parseInt(edge[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis();
        long flowVal = flow.maxFlow(graph, 0, num);
        time = System.currentTimeMillis() - time;
        return time;
    }
}
