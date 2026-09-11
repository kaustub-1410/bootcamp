class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();

        if (!dict.contains(endWord)) {
            return result;
        }

        Map<String, List<String>> parents = new HashMap<>();
        Map<String, Integer> level = new HashMap<>();

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        level.put(beginWord, 0);

        int wordLen = beginWord.length();

        while (!queue.isEmpty()) {
            String word = queue.poll();
            int currLevel = level.get(word);

            char[] arr = word.toCharArray();

            for (int i = 0; i < wordLen; i++) {
                char original = arr[i];

                for (char ch = 'a'; ch <= 'z'; ch++) {
                    arr[i] = ch;

                    String next = new String(arr);

                    if (!dict.contains(next)) {
                        continue;
                    }

                    if (!level.containsKey(next)) {
                        level.put(next, currLevel + 1);
                        queue.offer(next);
                        parents.put(next, new ArrayList<>());
                        parents.get(next).add(word);
                    } else if (level.get(next) == currLevel + 1) {
                        parents.get(next).add(word);
                    }
                }

                arr[i] = original;
            }
        }

        if (!level.containsKey(endWord)) {
            return result;
        }

        List<String> path = new ArrayList<>();
        path.add(endWord);

        dfs(endWord, beginWord, parents, path, result);

        return result;
    }

    private void dfs(String word,
                     String beginWord,
                     Map<String, List<String>> parents,
                     List<String> path,
                     List<List<String>> result) {

        if (word.equals(beginWord)) {
            List<String> temp = new ArrayList<>(path);
            Collections.reverse(temp);
            result.add(temp);
            return;
        }

        if (!parents.containsKey(word)) {
            return;
        }

        for (String parent : parents.get(word)) {
            path.add(parent);
            dfs(parent, beginWord, parents, path, result);
            path.remove(path.size() - 1);
        }
    }
}