package Chapter2;

public class BinaryTest {
    static int[] a = {-2, 11, -4, 13, -5, -2};

    public static void main(String[] args){
        System.out.println(maxSubSum(a,0,5));
    }

    public static int maxSubSum(int[] nums, int left, int right){
        if(left == right){
            if(nums[right] >= 0){
                return nums[left];
            }else{
                return 0;
            }
        }else{
            int center = (left + right) / 2;
            int leftConsequence = maxSubSum(nums, left, center);
            int rightConsequence = maxSubSum(nums, center + 1, right);
            int leftSum = 0;
            int leftMaxSum = 0;
            int rightSum = 0;
            int rightMaxSum = 0;
            int centerConsequence;
            for(int i = center;i>=left;i--){
                leftSum = leftSum + nums[i];
                if(leftSum > leftMaxSum){
                    leftMaxSum = leftSum;
                }
            }
            for(int i = center + 1;i<=right;i++){
                rightSum = rightSum + nums[i];
                if(rightSum > rightMaxSum){
                    rightMaxSum = rightSum;
                }
            }
            centerConsequence = leftMaxSum + rightMaxSum;
            return Math.max(Math.max(leftConsequence,rightConsequence),centerConsequence);
        }
    }
}
