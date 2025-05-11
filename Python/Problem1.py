class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        if len(nums1) > len(nums2):
            nums1, nums2 = nums2, nums1

        a, b = len(nums1), len(nums2)
        s = 0
        e = a
        while s <=e:
            pa = (s+e) // 2 
            pb = (a + b + 1) // 2 - pa

            max_la = float('-inf') if pa == 0 else nums1[pa - 1]
            max_lb = float('-inf') if pb == 0 else nums2[pb - 1] 
            min_ra = float('inf') if pa == a else nums1[pa]
            min_rb = float('inf') if pb == b else nums2[pb]

            if max_la <= min_rb and max_lb <= min_ra:
                d1 = max(max_la, max_lb)
                d2 = min(min_ra, min_rb)
                if (len(nums1) + len(nums2)) % 2 == 0:
                    return (d1+d2) / 2
                return d1
            elif max_la > min_rb:
                e = pa - 1
            elif max_lb > min_ra:
                s = pa + 1
