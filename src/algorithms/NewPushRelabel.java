package algorithms;
import ReaderAndWriter.ReadGrath;
import generatedGrath.MyGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewPushRelabel<T> extends CutGraph<T> {
    /**
     * The internal nodes to simplify graph construction
     */
    Map<Object, N> nodes = new HashMap<>();
    /**
     * Global edge counter
     */
    private int edges = 0;

    /**
     * The computer
     */
    private CutGraphImpl hipri;


    public long getMinCutValue(T source, T target) {
        if (source != this.source || target != this.target || cutValue == -1) {
            this.source = source;
            this.target = target;
            mincut(source, target);
        }
        return cutValue;
    }


    public List<T> getMinCut(T source, T target) {
        if (source != this.source || target != this.target || cutValue == -1) {
            this.source = source;
            this.target = target;
            mincut(source, target);
        }
        return cut;
    }

    /**
     * Internal: does the mincut execution
     *
     * @param source the source
     * @param sink the sink
     */
    void mincut(Object source, Object sink) {
        if(hipri == null){
            hipri = new CutGraphImpl(nodes.size(), edges);
            /*
            Remap to internal structure
             */
            for (Map.Entry<Object, N> entry : nodes.entrySet()) {
                N node = entry.getValue();
                Object name = entry.getKey();
                node.node = hipri.createNode(name, node.edges.size() + node.revEdges.size());
            }

            for (N node : nodes.values()) {
                for (E edge : node.edges) {
                    hipri.addEdge(node.node, edge.target.node, edge.cap);
                }
            }
        }

        CutGraphImpl.Node s = nodes.get(source).node;
        CutGraphImpl.Node t = nodes.get(sink).node;

        this.cut = (List<T>) hipri.mincut(s, t, false);
        this.cutValue = hipri.getValue();
    }

    public Map<Object, Object> getNodes() {
        return new HashMap<Object, Object>(nodes);
    }


    public void addNode(T source) {
        if(hipri != null) throw new RuntimeException("A computation was already started. You can not add new nodes or edges !");
        if (!nodes.containsKey(source)) {
            N node = new N();
            nodes.put(source, node);
        }
    }

    public void addEdge(T source, T target, long capacity) {
        // add node checks if the nodes are already contained
        addNode(source);
        addNode(target);

        /*
         * Add edge
         */
        E e = new E(nodes.get(target), capacity);
        nodes.get(source).edges.add(e);
        /*
         * Add reverse edge with capacity 0
         */
        E reverse = new E(nodes.get(source), 0);
        nodes.get(target).revEdges.add(reverse);

        e.reverseEdge = reverse;
        reverse.reverseEdge = e;
        edges +=2;
    }

    public void printGraph(){
        System.out.println("##### Start Printing CutGraph #####");
        Map<N,Object> reverseMap = new HashMap<N, Object>(nodes.size());
        for (Map.Entry<Object,N> node : nodes.entrySet()) {
            reverseMap.put(node.getValue(),node.getKey());
        }
        for (N node : reverseMap.keySet()) {
            for (E edge : node.edges) {
                System.out.println(reverseMap.get(node)+"---"+edge.cap+"--->"+reverseMap.get(edge.target));
            }
        };
        System.out.println("##### Printing CutGraph DONE! #####");
    }

    public Map<Object,List<Object>> getCharacterEdges(Map<Object,Object> reverseMap){
        System.out.println("##### Start Printing CutGraph #####");
        Map<Object,List<Object>> edges = new HashMap<Object, List<Object>>();
        for (Object node : reverseMap.keySet()) {
            for (E edge : ((N)node).edges) {
                List target = new ArrayList(2);
                target.add(reverseMap.get(edge.target));
                target.add(edge.cap);
                edges.put(reverseMap.get(node),target);
            }
        }
        return  edges;
    }

    /**
     * Internal builder representation for Nodes
     */
    private class N {
        private CutGraphImpl.Node node;
        private List<E> edges = new ArrayList<E>();
        private List<E> revEdges = new ArrayList<E>();
    }

    /**
     * Internal representation for edges
     */
    private class E {
        long cap;
        N target;
        E reverseEdge;

        public E(N target, long capacity) {
            this.target = target;
            this.cap = capacity;
        }
    }

    public static void main(String[] args) {
        NewPushRelabel g = new NewPushRelabel();

        List<MyGenerator.MyEdge> genGraph= ReadGrath.readEdges("docs/500.txt");
        for (MyGenerator.MyEdge edge:genGraph){
            g.addEdge(edge.getFrom(),edge.getTo(),edge.getCap());
        }


        System.out.println(g.getMinCutValue(0,500));
        System.out.println(g.getMinCut(0,500));
    }
}