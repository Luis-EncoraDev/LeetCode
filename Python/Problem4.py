class Solution(object):

    def isMatch(self, text, pattern):

        text_length = len(text)
        pattern_length = len(pattern)
        dp = [[False] * (pattern_length + 1) for _ in range(text_length + 1)]
        d  p[0][0] = True

        for pattern_idx in range(1, pattern_length + 1):
            if pattern[pattern_idx - 1] == '*':
                dp[0][pattern_idx] = dp[0][pattern_idx - 2]

        for text_idx in range(1, text_length + 1):

            for pattern_idx in range(1, pattern_length + 1):
                current_text_char = text[text_idx - 1]
                current_pattern_char = pattern[pattern_idx - 1]

                if current_pattern_char == '.' or current_pattern_char == current_text_char:
                    dp[text_idx][pattern_idx] = dp[text_idx - 1][pattern_idx - 1]

                elif current_pattern_char == '*':
                    dp[text_idx][pattern_idx] = dp[text_idx][pattern_idx - 2]
                    preceding_pattern_char = pattern[pattern_idx - 2]

                    if preceding_pattern_char == '.' or preceding_pattern_char == current_text_char:
                        dp[text_idx][pattern_idx] = dp[text_idx][pattern_idx] or dp[text_idx - 1][pattern_idx]
                        
        return dp[text_length][pattern_length]