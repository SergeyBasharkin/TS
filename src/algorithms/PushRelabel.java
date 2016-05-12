package algorithms;

/**
 * Created by Rustam on 09.05.2016.
 */
public class PushRelabel {

    private int[][] cap;

    public PushRelabel(int[][] cap) {
        this.cap = cap;
    }

    public int maxFlow(int s, int t) {
        int n = cap.length;
        System.out.println("done");
        int[] h = new int[n];
        h[s] = n-1;
        System.out.println("done");

        int[] maxh = new int[n];
        System.out.println("done");

        int[][] f = new int[n][n];
        int[] e = new int[n];
        System.out.println("done");

        for (int i = 0; i < n; ++i) {
            f[s][i] = cap[s][i];
            f[i][s] = -f[s][i];
            e[i] = cap[s][i];
        }
        System.out.println("done");

        for (int sz = 0; ; ) {
            if (sz == 0) {
                for (int i = 0; i < n; ++i)
                    if (i != s && i != t && e[i] > 0) {
                        if (sz != 0 && h[i] > h[maxh[0]])
                            sz = 0;
                        maxh[sz++] = i;
                    }
            }

            if (sz == 0)
                break;
            while (sz != 0) {
                int i = maxh[sz - 1];
                boolean pushed = false;
                for (int j = 0; j < n && e[i] != 0; ++j) {
                    if (h[i] == h[j] + 1 && cap[i][j] - f[i][j] > 0) {
                        int df = Math.min(cap[i][j] - f[i][j], e[i]);
                        f[i][j] += df;
                        f[j][i] -= df;
                        e[i] -= df;
                        e[j] += df;
                        if (e[i] == 0)
                            --sz;
                        pushed = true;
                    }
                }

                if (!pushed) {
                    h[i] = Integer.MAX_VALUE;
                    for (int j = 0; j < n; ++j)
                        if (h[i] > h[j] + 1 && cap[i][j] - f[i][j] > 0)
                            h[i] = h[j] + 1;
                    if (h[i] > h[maxh[0]]) {
                        sz = 0;
                        break;
                    }
                }
            }
        }
        System.out.println("done");

        int flow = 0;
        for (int i = 0; i < n; i++)
            flow += f[s][i];
        System.out.println("done");

        return flow;
    }

}
