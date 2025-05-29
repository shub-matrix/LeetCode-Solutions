class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        final int[] targets1 = computeTargets(edges1);
        final int offset = findMax(computeTargets(edges2));
        for (int i = 0; i < targets1.length; i++) {
            targets1[i] += offset;
        }
        return targets1;
    }

    static final int findMax(int[] values) {
        int maximum = 0;
        for (int val : values) {
            maximum = Math.max(maximum, val);
        }
        return maximum;
    }

    static int[] computeTargets(int[][] connections) {
        final int nodeCount = connections.length + 1;
        final int[] edgeCount = new int[nodeCount];
        final int[] xorLink = new int[nodeCount];
        final int[] result = new int[nodeCount];
        Arrays.fill(result, 1);
        final int[] stack = new int[nodeCount];
        for (int[] pair : connections) {
            final int u = pair[0];
            final int v = pair[1];
            edgeCount[u]++;
            edgeCount[v]++;
            xorLink[u] ^= v;
            xorLink[v] ^= u;
        }

        int stackLength = 0;
        for (int i = 0; i < nodeCount; i++) {
            if (edgeCount[i] == 1) {
                stack[stackLength++] = i;
            }
        }

        for (int i = 0; i < connections.length; i++) {
            final int current = stack[i];
            final int parent = xorLink[current];
            xorLink[parent] ^= current;
            result[parent] -= result[current];
            if (--edgeCount[parent] == 1) {
                stack[stackLength++] = parent;
            }
        }

        final int central = stack[nodeCount - 1];
        result[central] = (nodeCount + result[central]) / 2;
        for (int i = nodeCount - 2; i >= 0; i--) {
            final int current = stack[i];
            result[current] = nodeCount - result[xorLink[current]];
        }

        return result;
    }
}