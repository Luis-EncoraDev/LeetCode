import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int pointer = 0;
        int max_sub = 0;
        HashMap<Character, Integer> seen_chars = new HashMap<>();

        for(int index = 0; index < s.length(); index++){
            char current = s.charAt(index);

            if (seen_chars.containsKey(current) && seen_chars.get(current) >= pointer) {
                pointer = seen_chars.get(current) + 1;
            }

            seen_chars.put(current, index);
            max_sub = Math.max(max_sub, index - pointer + 1);
        }
        return max_sub;
    }
}
