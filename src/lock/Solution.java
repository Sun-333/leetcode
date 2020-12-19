package lock;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
    public static int[] dailyTemperatures(int[] T) {

        int[] res = new int[T.length];
        Stack<int[]> stack = new Stack();

        for(int i=0;i<T.length;i++){
            int j=0;
            while(!stack.isEmpty()&&T[i]>stack.peek()[0]){
                j++;
                int[] num = stack.pop();
                int number = num[0];
                int index = num[1];
                res[index]=j;
            }
            stack.push(new int[]{T[i],i});
        }
        return res;
    }

    public static void main(String[] args) {
        Solution.dailyTemperatures(new int[]{73,74,75,71,69,72,76,73});
    }
}