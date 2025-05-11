class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length){
            int[] x = nums1;
            nums1 = nums2;
            nums2 = x;
        }

        int a = nums1.length;
        int b = nums2.length;
        int s = 0;
        int e = a;

        while(s<=e){
            int pa = (s+e) / 2;
            int pb = (a + b + 1) / 2 - pa;

            int max_la = (pa == 0) ? Integer.MIN_VALUE : nums1[pa - 1];
            int max_lb = (pb == 0) ? Integer.MIN_VALUE : nums2[pb - 1];
            int min_ra = (pa == a) ? Integer.MAX_VALUE : nums1[pa];
            int min_rb = (pb == b) ? Integer.MAX_VALUE : nums2[pb];

            if (max_la <= min_rb && max_lb <= min_ra){
                double d1 = Math.max(max_la, max_lb);
                double d2 = Math.min(min_ra, min_rb);
                if((nums1.length+nums2.length) % 2 == 0){
                    return (d1+d2)/2;
                }
                return d1;

            }
                
            else if (max_la > min_rb){
                e = pa - 1;
            }

            else if (max_lb > min_ra){
                s = pa + 1;
            }
        }
        throw new IllegalArgumentException("Dummy throw");
    }
}
