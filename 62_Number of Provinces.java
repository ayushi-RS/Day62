class Solution {
	int cities = 0;
	public int findCircleNum(int[][] isConnected) {
		int n = isConnected.length;
		boolean[] visited = new boolean[n];
		for(int i=0;i<n;i++){
			if(!visited[i]){
				dfs(isConnected,i,n,visited);
				cities++;
			}
		}
		return cities;
	}

	void dfs(int[][] mat,int i,int n,boolean[] visited){
		if(visited[i]) return;
		visited[i] = true;
		for(int j=0;j<n;j++){
			if(mat[i][j]==1 && i!=j) dfs(mat,j,n,visited);
		}
	}

}