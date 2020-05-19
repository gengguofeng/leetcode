import java.util.HashMap;
import java.util.Map;

public class S0001_TwoSum {
    public static void main(String[] args) {

        int[] nums = {1,3,2,11,15,7} ;
        int target = 9 ;

        for (int i:twoSum(nums,target)) {
            System.err.println(i);
        }

    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * 在进行迭代并将元素插入到表中的同时，我们还会回过头来检查表中是否已经存在当前元素所对应的目标元素。
     * 如果它存在，那我们已经找到了对应解，并立即将其返回。
     * @param nums
     * @param target
     * @return
     */
    public static  int[] twoSum(int[] nums, int target) {

        Map<Integer,Integer> map = new HashMap<>() ;
        for (int i = 0 ; i < nums.length ; i ++){
            int key = nums[i] ;
            int index = i ;
            if(map.containsKey(target-key)){
                return new int[]{i,map.get(target-key)} ;
            }
            map.put(key,index) ;
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
