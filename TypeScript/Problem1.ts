function findMedianSortedArrays(nums1: number[], nums2: number[]): number {
    if(nums1.length > nums2.length){
        let x: number[] = nums1
        nums1 = nums2
        nums2 = x
    }

    let a: number = nums1.length
    let b: number = nums2.length
    let s: number = 0
    let e: number = a
    while(s<=e){
        let pa: number = Math.floor((s + e) / 2)
        let pb: number = Math.floor((a + b + 1) / 2 - pa)

        let max_la: number = (pa === 0) ? -Infinity : nums1[pa - 1]
        let max_lb: number = (pb === 0) ? -Infinity : nums2[pb - 1]
        let min_ra: number = (pa === a) ? Infinity : nums1[pa]
        let min_rb: number = (pb === b) ? Infinity : nums2[pb]

        if(max_la <= min_rb && max_lb <= min_ra){
            let d1: number = Math.max(max_la, max_lb)
            let d2: number = Math.min(min_ra, min_rb)

            if((nums1.length + nums2.length) % 2 === 0) {
                return (d1+d2) / 2
            }
            return d1
        }
        else if(max_la > min_rb){
            e = pa - 1
        }
        else if(max_lb > min_ra){
            s = pa + 1
        }
    }
    return 0.00

};