function lengthOfLongestSubstring(s: String): Number {

    let maxSubstring: number = 0;
    let pointer: number = 0;
    let seenChars: { [key: string]: number } = {};

    for (let i = 0; i < s.length; i++) {
        const char = s[i];

        if (char in seenChars && seenChars[char] >= pointer) {
            pointer = seenChars[char] + 1;
        }

        seenChars[char] = i;
        maxSubstring = Math.max(maxSubstring, i - pointer + 1);
    }

    return maxSubstring;
}