class WordDictionary {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord;
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode curr = root;

        for (char c : word.toCharArray()) {
            int idx = c - 'a';

            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }

            curr = curr.children[idx];
        }

        curr.isWord = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int pos, TrieNode node) {
        if (node == null) {
            return false;
        }

        if (pos == word.length()) {
            return node.isWord;
        }

        char ch = word.charAt(pos);

        if (ch == '.') {
            for (TrieNode child : node.children) {
                if (child != null && dfs(word, pos + 1, child)) {
                    return true;
                }
            }
            return false;
        }

        return dfs(word, pos + 1, node.children[ch - 'a']);
    }
}