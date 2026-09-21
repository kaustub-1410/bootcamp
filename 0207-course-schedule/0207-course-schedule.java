class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        
        int[] indegree = new int[numCourses];
        
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            
            graph[prereq].add(course);
            indegree[course]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int completed = 0;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            completed++;
            
            for (int next : graph[curr]) {
                indegree[next]--;
                
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        return completed == numCourses;
    }
}