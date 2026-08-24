public class ShortestPaths {

    static void ShortestPaths(int v, int[][] cost, int[] dist, int n) {
        boolean[] S = new boolean[n + 1];
        int INF = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            S[i] = false;
            dist[i] = cost[v][i];
        }
        S[v] = true;
        dist[v] = 0;

        for (int num = 2; num <= n; num++) {

            int u = -1;
            int min = INF;
            for (int i = 1; i <= n; i++) {
                if (!S[i] && dist[i] < min) {
                    min = dist[i];
                    u = i;
                }
            }
            if (u == -1) break;

            S[u] = true;

            for (int w = 1; w <= n; w++) {
                if (!S[w] && cost[u][w] != INF && dist[u] != INF) {
                    if (dist[w] > dist[u] + cost[u][w]) {
                        dist[w] = dist[u] + cost[u][w];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int INF = Integer.MAX_VALUE;
        int[][] cost = {
                { 0,   0,   0,   0,   0,   0 },
                { 0,   0,  50,  45,  10, INF },
                { 0, INF,   0,  10,  15, INF },
                { 0, INF, INF,   0, INF,  30 },
                { 0,  20, INF, INF,   0,  15 },
                { 0, INF,  20,  35, INF,   0 }
        };
        int v = 1;
        int[] dist = new int[n + 1];

        ShortestPaths(v, cost, dist, n);

        System.out.println("Source vertex: " + v);
        for (int i = 1; i <= n; i++) {
            if (dist[i] == INF) System.out.println("dist[" + i + "] = INF");
            else System.out.println("dist[" + i + "] = " + dist[i]);
        }
    }
}
