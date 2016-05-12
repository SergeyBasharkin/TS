package algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adelzamalutdinov on 02.05.16.
 */
public class MaxFlowEdmondsKarp {

        public List<Edge>[] createGraph(int nodes) {
            List<Edge>[] graph = new List[nodes];
            for (int i = 0; i < nodes; i++)
                graph[i] = new ArrayList<>();
            return graph;
        }

        public void addEdge(List<Edge>[] graph, int s, int t, int cap) {
            graph[s].add(new Edge(s, t, graph[t].size(), cap));
            graph[t].add(new Edge(t, s, graph[s].size() - 1, 0));
        }

        public int maxFlow(List<Edge>[] graph, int s, int t) {
            int flow = 0;
            int[] q = new int[graph.length];
            while (true) {
                int qt = 0;
                q[qt++] = s;
                Edge[] pred = new Edge[graph.length];
                for (int qh = 0; qh < qt && pred[t] == null; qh++) {
                    int cur = q[qh];
                    for (Edge e : graph[cur]) {
                        if (pred[e.getT()] == null && e.getCap() > e.getF()) {
                            pred[e.getT()] = e;
                            q[qt++] = e.getT();
                        }
                    }
                }
                if (pred[t] == null)
                    break;
                int df = Integer.MAX_VALUE;
                for (int u = t; u != s; u = pred[u].getS())
                    df = Math.min(df, pred[u].getCap() - pred[u].getF());
                for (int u = t; u != s; u = pred[u].getS()) {
                    pred[u].setF(pred[u].getF()+df);
                    graph[pred[u].getT()].get(pred[u].getRev()).setF(graph[pred[u].getT()].get(pred[u].getRev()).getF()-df);
                }
                flow += df;
            }
            return flow;
        }
}
