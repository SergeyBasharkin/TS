/**
 * Created by adelzamalutdinov on 02.05.16.
 */
public class Edge {
    private int s;
    private int t;
    private int rev;
    private int cap;
    private int f;

    public Edge(int s, int t, int rev, int cap) {
        this.s = s;
        this.t = t;
        this.rev = rev;
        this.cap = cap;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getRev() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev = rev;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }
}
