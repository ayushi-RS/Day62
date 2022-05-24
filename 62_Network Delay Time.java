class Solution {
   private Map<Integer, List<Pair<Integer, Integer>>> graph = new HashMap<>();

    public int networkDelayTime(int[][] times, int n, int k) {
        int[] minTimes = new int[n + 1];
        Arrays.fill(minTimes, Integer.MAX_VALUE);
        PriorityQueue<int[]> minTravelTimes = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        buildGraph(times);

        minTravelTimes.offer(new int[]{k, 0});
        minTimes[k] = 0;
        while (!minTravelTimes.isEmpty()) {
            int[] curNode = minTravelTimes.poll();

            int curNodeId = curNode[0];
            int curTravelTime = curNode[1];

            if (minTimes[curNodeId] >= curTravelTime) {
                if (graph.containsKey(curNodeId)) {
                    for (Pair<Integer, Integer> neighborNode : graph.get(curNodeId)) {
                        int nextNodeId = neighborNode.getKey();
                        int nextTravelTime = neighborNode.getValue();

                        if (minTimes[nextNodeId] > curTravelTime + nextTravelTime) {
                            minTimes[nextNodeId] = curTravelTime + nextTravelTime;
                            minTravelTimes.offer(new int[]{nextNodeId, minTimes[nextNodeId]});
                        }
                    }
                }
            }
        }

        int time = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            time = Math.max(time, minTimes[i]);
        }

        return time == Integer.MAX_VALUE ? -1 : time;
    }

    private void buildGraph(int[][] times) {
        for (int[] time : times) {
            graph.computeIfAbsent(time[0], __ -> new ArrayList<>()).add(new Pair<>(time[1], time[2]));
        }
    }
}