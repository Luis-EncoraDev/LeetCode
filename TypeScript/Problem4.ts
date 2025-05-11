function isMatch(text: string, pattern: string): boolean {
const dp: boolean[][] = Array.from({ length: text.length + 1 }, () => 
        new Array(pattern.length + 1).fill(false));
    dp[0][0] = true;
    for (let j = 1; j <= pattern.length; j++) {
        if (pattern[j - 1] === '*') {
            dp[0][j] = dp[0][j - 2];
        }
    }
    for (let i = 1; i <= text.length; i++) {
        for (let j = 1; j <= pattern.length; j++) {
            if (pattern[j - 1] === '.' || pattern[j - 1] === text[i - 1]) {
                dp[i][j] = dp[i - 1][j - 1];
            } 
            else if (pattern[j - 1] === '*') {
                dp[i][j] = dp[i][j - 2];
                if (pattern[j - 2] === '.' || pattern[j - 2] === text[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j];
                }
            }
        }
    }
    return dp[text.length][pattern.length];
};