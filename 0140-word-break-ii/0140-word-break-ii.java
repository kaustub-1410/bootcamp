class Solution {

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        Map<String, List<String>> memo = new HashMap<>();

        return dfs(s, set, memo);
    }

    private List<String> dfs(String s, Set<String> set,
                             Map<String, List<String>> memo) {

        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        List<String> result = new ArrayList<>();

        if (s.length() == 0) {
            result.add("");
            return result;
        }

        for (String word : set) {
            if (s.startsWith(word)) {

                List<String> suffixWays =
                        dfs(s.substring(word.length()), set, memo);

                for (String suffix : suffixWays) {
                    if (suffix.isEmpty()) {
                        result.add(word);
                    } else {
                        result.add(word + " " + suffix);
                    }
                }
            }
        }

        memo.put(s, result);
        return result;
    }
}