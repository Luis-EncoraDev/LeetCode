class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        max_sub = 0
        pointer = 0
        seen_chars = {}
        for index in range(len(s)):
            if s[index] in seen_chars and seen_chars[s[index]] >= pointer:
                pointer = seen_chars[s[index]] + 1
            seen_chars[s[index]] = index
            max_sub = max(max_sub, index - pointer + 1)
        return max_sub
