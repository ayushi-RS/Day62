class Solution 
{
    class pair
    {
        String vertex;
        double value;
        private pair(String vertex,double value)
        {
            this.vertex=vertex;
            this.value=value;
        }
    }
    
    
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) 
    {
        Map<String,List<pair>> map=new HashMap<>();
        buildGraph(equations,values,map);           // building the connection between the vertex
        
        double[] ans=new double[queries.size()];
        for(int i=0;i<queries.size();i++)
            ans[i]=dfs(queries.get(i).get(0),queries.get(i).get(1),map,new HashSet<>());
        return ans;
    }
    
    private void buildGraph(List<List<String>> eq, double[] v,Map<String,List<pair>> map)
    {
        for(int i=0;i<eq.size();i++)
        {
            String src=eq.get(i).get(0);
            String des=eq.get(i).get(1);
            
            map.putIfAbsent(src,new ArrayList<>());
            map.putIfAbsent(des,new ArrayList<>());
            
            // a to b and b to c
            
            map.get(src).add(new pair(des,v[i]));       //  val
            map.get(des).add(new pair(src,1/v[i]));     //  1/val for reverse i.e b/a
        }
    }
    
    public double dfs(String src,String des,Map<String,List<pair>> map,Set<String> vis)
    {
        if(map.containsKey(des)==false || map.containsKey(src)==false)
            return -1.0;
        
        if(src.equals(des))  // destination is reached
            return 1.0;
        
        vis.add(src);
        
        for(pair p:map.get(src))
        {
             // escape the visited vertex
            
            if(vis.contains(p.vertex)==false)  
            {
                double val=dfs(p.vertex,des,map,vis);
                if(val!=-1.0)
                    return val * p.value;  
            }
        }
        return -1.0;  
    }
}