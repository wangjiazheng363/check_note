package wjz.utilproject;

import java.util.Random;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/24 9:45
 *
 */
public class BinarySearchTest {


    public static void main(String[] args){


        // 创建一个随机数生成器
        Random random = new Random();

        // 生成一个在 [0, 100) 范围内的整数
        int num1 = random.nextInt(1000000000);
        //打印结果
        System.out.println("答案："+num1);
        searchNumber(num1,0,1000000000 , 0);


    }


    //递归函数查找答案
    public static Integer searchNumber(Integer random , Integer first, Integer last , int count){
        //得到左右区间
        int middle = (last - first) / 2 + first;
        System.out.println("中间点："+middle);
        System.out.println("last："+last);
        System.out.println("first："+first);
        if (random>middle){
            return searchNumber(random,middle,last , count+1);
        } else if (random<middle) {
            return searchNumber(random,first,middle , count+1);
        }else{
            System.out.println("当前查询次数："+count);
            System.out.println("结果："+middle);
            System.out.println("答案："+random);
            return middle;
        }
    }

}
