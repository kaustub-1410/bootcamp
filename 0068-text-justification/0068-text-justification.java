class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < words.length) {

            int j = i;
            int lineLength = 0;

            // Take as many words as possible
            while (j < words.length &&
                   lineLength + words[j].length() + (j - i) <= maxWidth) {
                lineLength += words[j].length();
                j++;
            }

            int numWords = j - i;
            int spacesNeeded = maxWidth - lineLength;

            StringBuilder line = new StringBuilder();

            // Last line or single-word line
            if (j == words.length || numWords == 1) {

                for (int k = i; k < j; k++) {
                    line.append(words[k]);

                    if (k < j - 1) {
                        line.append(' ');
                        spacesNeeded--;
                    }
                }

                while (spacesNeeded-- > 0) {
                    line.append(' ');
                }
            }
            else {

                int gaps = numWords - 1;

                int evenSpaces = spacesNeeded / gaps;
                int extraSpaces = spacesNeeded % gaps;

                for (int k = i; k < j; k++) {
                    line.append(words[k]);

                    if (k < j - 1) {

                        int spaces = evenSpaces;

                        if (extraSpaces > 0) {
                            spaces++;
                            extraSpaces--;
                        }

                        while (spaces-- > 0) {
                            line.append(' ');
                        }
                    }
                }
            }

            result.add(line.toString());
            i = j;
        }

        return result;
    }
}