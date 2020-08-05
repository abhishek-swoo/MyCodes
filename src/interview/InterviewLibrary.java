package interview;

import java.util.*;

public class InterviewLibrary {

    //    print Maximum in sliding window
    void printMaximum(int[] arr, int k) {
        Deque<Integer> deque = new LinkedList<>();

        int i;
        for (i = 0; i < k; i++) {
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]) {
                deque.removeLast();
            }

            deque.addLast(i);
        }

        for (; i < arr.length; i++) {
            System.out.println(deque.getFirst());

            while (!deque.isEmpty() && deque.peek() <= i - k) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && deque.peekLast() <= arr[i]) {
                deque.removeLast();
            }

            deque.addLast(i);
        }

    }


///////////  Buying and selling stocks at most 2 times

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        //highest profit in 0 ... i
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];

        // DP from left to right
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }

        // DP from right to left
        right[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }

        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, left[i] + right[i]);
        }

        return profit;
    }

//    Stock buying and sell two times end


//stock buy and sell at max k times

    static int maxProfit(int price[],
                         int n, int k) {
        int profit[][] = new int[k + 1][n + 1];

        for (int i = 0; i <= k; i++)
            profit[i][0] = 0;

        for (int j = 0; j <= n; j++)
            profit[0][j] = 0;

        for (int i = 1; i <= k; i++) {
            int diff = Integer.MIN_VALUE;
            for (int j = 1; j < n; j++) {
                diff = Math.max(diff,
                        profit[i - 1][j - 1] -
                                price[j - 1]);
                profit[i][j] = Math.max(profit[i][j - 1],
                        price[j] + diff);
            }
        }

        return profit[k][n - 1];
    }

//    end of buy sell stock at max k time

//    maXHeap

    private int[] Heap;
    private int size;

    private boolean isLeaf(int pos) {
        if (pos >= (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }

    private int leftChild(int pos) {
        return (2 * pos);
    }

    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    private void swap(int fpos, int spos) {
        int tmp;
        tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;
    }

    private void maxHeapify(int pos) {
        if (isLeaf(pos))
            return;

        if (Heap[pos] < Heap[leftChild(pos)] ||
                Heap[pos] < Heap[rightChild(pos)]) {

            if (Heap[leftChild(pos)] > Heap[rightChild(pos)]) {
                swap(pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            } else {
                swap(pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }
        }
    }

//    max heap ends

    //   a divide b without using multiplication and divison
    public long divide(long dividend, long divisor) {
        int sign = getSign(divisor, divisor);
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        long temp = 0, quotient = 0;
        for (long i = 31; i >= 0; --i) {

            if (temp + ((long) divisor << i) <= dividend) {
                temp += (divisor << i);
                quotient |= (1 << i);
            }
        }
        return sign * quotient;
    }

    int getSign(long dividend, long divisor) {
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) return -1;
        return 1;
    }

//    end of a divide b

//    find out whether there are two distinct indices i and j in the array such that the absolute difference between
//    nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        if(nums.length <= 1) return false;
        if(t < 0) return false;

        long width = t + 1;
        HashMap<Long, Long> map = new HashMap<>();

        for(int i = 0;i < nums.length; i++) {
            long bucket = getBucket((long)nums[i], width);

            if(map.containsKey(bucket)) {
                return true;
            }

            if(map.containsKey(bucket - 1) && Math.abs((map.get(bucket - 1) - (long)nums[i])) <= t) {
                return true;
            }

            if(map.containsKey(bucket + 1) && Math.abs((map.get(bucket + 1) - (long)nums[i])) <= t) {
                return true;
            }

            map.put(bucket, (long)nums[i]);

            if(i - k >= 0) {
                long oldBucket = getBucket(nums[i-k], width);
                map.remove(oldBucket);

            }
        }

        return false;
    }

    private static long getBucket(long val, long width) {
        return val < 0? ((val + 1L) / width) - 1L: val/width;
    }

//    end of Contains Duplicate III


// Avoid Flood in The City

    public int[] avoidFlood(int[] rains) {

        TreeSet<Integer> tset = new TreeSet<>();
        HashMap<Integer, Integer> hm = new HashMap<>();

        int ans[] = new int[rains.length];

        for(int i = 0; i < rains.length; i++) {
            if(rains[i] != 0) {
                if(hm.containsKey(rains[i])) {
                    int key = hm.get(rains[i]);
                    Integer val = tset.ceiling(key);
                    if(val == null) {
                        return new int[0];
                    }
                    ans[val] = rains[i];
                    tset.remove(val);
                }
                hm.put(rains[i], i);
                ans[i] = -1;
            } else {
                tset.add(i);
            }
        }

        for(int s: tset) {
            ans[s] = 1;
        }
        return ans;
    }

//    end of Avoid Flood in The City

//    Circular Array loop: Determine if there is a loop (or a cycle) in nums. A cycle must start and end at the same index and the cycle's length > 1

    public boolean circularArrayLoop(int[] nums) {

        boolean[] visited;

        for(int i = 0; i < nums.length; i++) {
            visited = new boolean[nums.length];
            boolean forward = false;
            if(nums[i] > 0) forward = true;
            if(!visited[0] && travelArray(nums, visited, i, forward)) return true;
        }
        return false;
    }

    boolean travelArray(int nums[], boolean visited[], int index, boolean forward) {

        while(true) {

            if(forward && nums[index] < 0) return false;
            if(!forward && nums[index] > 0) return false;

            if(visited[index]) {
                return true;
            } else {
                visited[index] = true;
            }
            int prev = index;
            index += nums[index];
            while(index < 0) {
                index += nums.length;
            }
            index %= nums.length;
            if(prev == index) break;
        }
        return false;
    }

//    End of circular array loop

//    Maximum product subarray
public int maxProduct(int[] nums) {

    int ans = nums[0];
    int min_so_far = nums[0];
    int max_so_far = nums[0];

    for(int i = 1; i < nums.length; i++) {

        int tmp = Math.max(nums[i], Math.max(max_so_far * nums[i], min_so_far * nums[i]));
        min_so_far = Math.min(nums[i], Math.min(max_so_far * nums[i], min_so_far * nums[i]));

        max_so_far = tmp;
        ans= Math.max(ans, max_so_far);
    }
    return ans;
}

//  End of maximum product subarray

//    unique paths II: No. of unique paths with obstacles in grid, obstacles is 1 in grid[i][j]

    int dp[][];
    int uniquePaths(int[][] grid, int i, int j) {
        if(i >= grid.length || j >= grid[i].length || grid[i][j] == 1) {
            return 0;
        }

        if(dp[i][j] != -1) return dp[i][j];

        if(i == grid.length - 1 && j == grid[i].length - 1) {
            return 1;
        }

        int way = 0;

        if(i < grid.length) {
            way += uniquePaths(grid, i + 1, j);
        }

        if(j < grid[i].length) {
            way += uniquePaths(grid, i, j + 1);
        }

        dp[i][j] = way;
        return way;
    }

//End of unique paths II

//    Unique path III: grid is filled with 0, 1, -1, 2. Return the number of 4-directional walks from the starting square
//    to the ending square, that walk over every non-obstacle square exactly once
    int dx[] = {-1, 0, 1,0};
    int dy[] = {0, 1, 0, -1};

    int uniquePaths(int[][] grid, int i, int j, int empty) {

        if(i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == -1) {
            return 0;
        }

        if(grid[i][j] == 2) {
            if(empty == -1) {
                return 1;
            } else {
                return 0;
            }
        }

        int ways = 0;
        grid[i][j] = -1;
        for(int ai = 0; ai < 4; ai++) {
            int ni = i + dx[ai];
            int nj = j + dy[ai];
            ways += uniquePaths(grid, ni, nj, empty -1);
        }
        grid[i][j] = 0;

        return ways;
    }

//    End of unique path III

// Knight Dialer: Each time it lands on a key (including the initial placement of the knight), it presses the number of
// that key, pressing N digits total.How many distinct numbers can you dial in this manner?

    public int knightDialer(int N) {
        grid = new boolean[4][3];
        grid[3][0] = true;
        grid[3][2] = true;

        dpp = new int[4][3][N];

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 3; j++) {
                Arrays.fill(dpp[i][j], -1);
            }
        }

        int ans = 0;
        for(int i = 0; i< 4; i++) {
            for(int j = 0; j < 3; j++) {
                if(!grid[i][j]) {
                    ans = (ans + findMoves(i,j,N-1)) % 1000000007;
                }
            }
        }

        return ans;

    }

    int dxx[] = {-1, 1, 2, 2, 1, -1, -2, -2};
    int dyy[] = {2, 2, 1, -1, -2, -2, -1, 1};

    int dpp[][][];

    boolean grid[][];

    int findMoves(int i, int j, int count) {

        if(count == 0) {
            return 1;
        }
        if(dpp[i][j][count] != -1) return dpp[i][j][count];
        int ans = 0;
        for(int di = 0; di < 8; di++) {
            int x = i + dxx[di];
            int y = j + dyy[di];

            if(isValid(x, y) && !grid[x][y]) {
                ans = (ans + findMoves(x, y, count - 1))% 1000000007;
            }
        }
        dpp[i][j][count] = ans% 1000000007;
        return ans% 1000000007;
    }

    boolean isValid(int i, int j) {
        if(i > 3 || i<0 || j > 2 || j < 0) {
            return false;
        }
        return true;
    }
// end of knight dialer

//    Gas station: You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its
//    next station (i+1). You begin the journey with an empty tank at one of the gas stations.

    public int canCompleteCircuit(int[] gas, int[] cost) {

        int diff = 0;
        int amount = 0;
        int index = 0;

        for(int i = 0; i < gas.length; i++) {
            amount += gas[i] - cost[i];
            if(amount < 0) {
                diff += amount;
                amount = 0;
                index = i+1;
            }
        }

        if(amount + diff >= 0) {
            return index;
        }
        return -1;
    }

//    end of gas station

//    divide chocolate: You want to share the chocolate with your K friends so you start cutting the chocolate bar into K+1 pieces
//using K cuts, each piece consists of some consecutive chunks.

    public int maximizeSweetness(int[] nums, int m) {
        m++;
        long l = Integer.MAX_VALUE, r = 0;
        for(int n : nums) {
            r += n;
            l = Math.min(l, n);
        }
        while(l+1 < r) {
            long mid = l + (r-l) / 2;
            if(canSplit(nums, m, mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        if(canSplit(nums, m, r)) return (int)r;
        else return (int)l;
    }
    public boolean canSplit(int[] nums, int m, long target) {
        long sum = 0, cnt = 0;
        for(int n : nums) {
            if(sum + n >= target) {
                sum = 0;
                cnt++;
            } else {
                sum += n;
            }
        }
        return cnt >= m;
    }

//    end of divide chocolate

//    Count K size increasing subsequence (recursion)

    static int getKSizeSubSequence(int[] arr, int i, int prev, int count, int k) {
        if(i == arr.length && count != k) {
            return 0;
        }

        if(count == k) return 1;

        if(prev < arr[i]) {
            return getKSizeSubSequence(arr, i + 1, i, count + 1, k) +
                    getKSizeSubSequence(arr, i + 1, prev, count, k);
        }
        return getKSizeSubSequence(arr, i + 1, prev, count, k);
    }

// end of Count K size increasing subsequence

//    check a number is fibonacci or not: A number is Fibonacci if and only if one or both of (5*n2 + 4) or (5*n2 – 4) is a perfect square

//    Find the maximum number of subarrays whose sum equals to k

    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                if (sum[end] - sum[start] == k)
                    count++;
            }
        }
        return count;
    }

//    End of maximum number of subarrays whose sum equals to k

//    Follow up question: Find the maximum number of non-overlapping subarrays whose sum equals to k
    public int subarray_sum(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>(nums.length);
        counts.put(0, -1);
        int ans = 0;
        for (int i = 0, cum_sum = 0, last = -1; i < nums.length; ++i) {
            cum_sum += nums[i];
            if (counts.containsKey(cum_sum - k) && counts.get(cum_sum - k) + 1 > last) {
                ++ans; last = i;
            }
            counts.put(cum_sum, i);
        }
        return ans;
    }
//    end

//    generate is supposed to return a random number between 0 to n, but it is not supposed to return a number that it
//    has already returned. If possiblities are exhauted, return -1.

    static class RandomGenerator {

        int[] arr;
        Random rand;
        int cur = 0;

        public RandomGenerator(int n) {
            arr = new int[n];
            rand = new Random();
            for(int i=0;i<arr.length;i++) {
                arr[i] = i;
                int next = rand.nextInt(i+1);
                if(next != i) {
                    int tmp = arr[i];
                    arr[i] = arr[next];
                    arr[next] = tmp;
                }
            }
        }

        public int generate() {
            if(cur < arr.length)
                return arr[cur++];
            return -1;
        }
    }
//    end of random class

//    https://leetcode.com/discuss/interview-question/480256/Google-or-Onsite-or-Find-assignment-of-bread-slice
//    bread slice find index of bread slice assigned to whom

    static class Tuple {
        char first;
        char second;
        Tuple(char f, char s) {
            this.first = f;
            this.second = s;
        }
    }
    public static void main(String[] args) {
        List<Tuple> list = new ArrayList<>();
        list.add(new Tuple('A','C'));
        list.add(new Tuple('C', 'B'));
        list.add(new Tuple('A','D'));

        assignBread(list, 4);
    }


    public static void assignBread(List<Tuple> list, int numPeople) {

        HashMap<Character, int []> map = new HashMap<>();
        int [] bread = new int[numPeople];

        for (int i = 0; i < bread.length; i++) {
            bread[i] = i;
        }
        map.put('A', bread);

        int [] curBread;
        for (Tuple currTuple : list) {

            if(map.containsKey(currTuple.first)) {
                curBread = map.get(currTuple.first);
                int [] leftHalf = new int [curBread.length/2];
                int [] rightHalf = new int [curBread.length/2];

                for (int i = 0; i < leftHalf.length; i++) {
                    leftHalf[i] = curBread[i];
                }
                for (int i = 0; i < rightHalf.length; i++) {
                    rightHalf[i] = curBread[curBread.length/2 + i];
                }
                map.put(currTuple.first, rightHalf);
                map.put(currTuple.second, leftHalf);
            }
        }

        for (Character key : map.keySet()) {
            for (int val : map.get(key)) {
                System.out.println("Person " + key + " has bread: " + val);
            }
        }
    }

// end of bread slice problem

//    Closest XY pair in grid, In a grid there is multiple X and Y

    long findMinDist(char grid[][], int n, int m) {
        long dist[][] = new long[n][m];

        for(int i = 0; i< n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {

                if(grid[i][j] == 'X') {
                    dist[i][j] = 0;
                } else {
                    if(i > 0) {
                        dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                    }
                    if(j > 0) {
                        dist[i][j] = Math.min(dist[i][j] , dist[i][j - 1] + 1);
                    }
                }
            }
        }

        for(int i = n-1; i >= 0; i--) {
            for(int j = m - 1; j >= 0; j--) {
                if(grid[i][j] == 'X') {
                    dist[i][j] = 0;
                } else {
                    if(i < n - 1) {
                        dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
                    }
                    if(j < m - 1) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
                    }
                }
            }
        }
        long ans = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 'Y') {
                    ans = Math.min(ans, dist[i][j]);
                }
            }
        }
        return ans;
    }

//    End of closest XY Pair


}
