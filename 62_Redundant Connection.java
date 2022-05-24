class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int [] dSet = new int [1001];
		
		//initializing every node as parent of itself
        for(int i = 0; i < dSet.length; i++){
            dSet[i] = i;
        }
		
        for(int i = 0 ; i < edges.length; i++){
            int u = edges[i][0];
            int v = edges[i][1];
			
			//getting parent of u
            while(u != dSet[u]){
                u = dSet[u];
            }
			
			// getting parent of v
            while(v != dSet[v]){
                v = dSet[v];
            }
			
			//if the same return the extra edge
            if(v == u){
                return edges[i];
            }
			
			// merge the two sets by making the parent of one of them point to the other
            else{
                dSet[v] = u;
            }
        }
        return new int[]{-1, -1};
    }
}