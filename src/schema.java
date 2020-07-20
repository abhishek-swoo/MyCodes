public class schema {

    int dp[][];

    public int minCoins(int coins[], int i, int sum, int target) {

        if(sum > target || i >= coins.length) {
            return 0;
        }

        if (dp[i][sum] != -1) {
            return dp[i][sum];
        }

        int a = minCoins(coins, i + 1, sum + coins[i], target);
        int b = minCoins(coins, i, sum + coins[i], target);
        int c = minCoins(coins, i + 1, sum, target);

        return dp[i][sum] = Math.min(1 + a, Math.min(1 + c, b));
    }

}
