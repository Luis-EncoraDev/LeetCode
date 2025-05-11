class Solution {
    public boolean isMatch(String s, String p) {
        int textLength = s.length();
        int patternLength = p.length();
        boolean[][] dp = new boolean[textLength + 1][patternLength + 1];
        dp[0][0] = true;
        for (int j = 1; j <= patternLength; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        for (int i = 1; i <= textLength; i++) {
            for (int j = 1; j <= patternLength; j++) {
                char currentPatternChar = p.charAt(j - 1);
                char currentTextChar = s.charAt(i - 1);
                if (currentPatternChar == '.' || currentPatternChar == currentTextChar) {
                    dp[i][j] = dp[i - 1][j - 1];
                } 
                else if (currentPatternChar == '*') {
                    dp[i][j] = dp[i][j - 2];
                    char precedingPatternChar = p.charAt(j - 2);
                    if (precedingPatternChar == '.' || precedingPatternChar == currentTextChar) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }
        return dp[textLength][patternLength];
    }
}
