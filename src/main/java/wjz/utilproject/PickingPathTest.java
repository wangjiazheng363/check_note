package wjz.utilproject;

import lombok.Data;

import java.util.*;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/24 10:40
 *
 */
public class PickingPathTest {

    //矩阵宽度
    private int width = 100;

    //矩阵长度
    private int length = 100;

    //单次拣货数量
    private int pickNum = 3;

    //拣货总量
    private int pickAllNum = 42;

    //货物集合
    private List<Good> goodList;

    //起点坐标

    //终点坐标

    public static void main(String[] args) {

        Point fistPoint = new Point();
        fistPoint.setX(1);
        fistPoint.setY(1);
        Point endPoint = new Point();
        endPoint.setX(20);
        endPoint.setY(20);
        List<Point> points = getPointList();
        
//        List<Point> points = new ArrayList<>();
//        points.add(fistPoint);
//        points = line(fistPoint,endPoint,points);
        System.out.println(points);

    }

    public static List<Point> getPointList(){
        List<Point> points = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int num1 = random.nextInt(20);
            int num2 = random.nextInt(20);
            Point point = new Point();
            point.setY(num2);
            point.setX(num1);
            point.setId(i);
            points.add(point);
        }
        return points;
    }

    public static List<Point> line(Point fistPoint,Point endPoint , List<Point> points){
        Point point = new Point();
        Random random = new Random();
        boolean b = random.nextBoolean();
        int x = fistPoint.getX();
        int y = fistPoint.getY();
        if (b){
            if (x+1>endPoint.getX()){
                if (y+1>endPoint.getY()){
                    return points;
                }
                y = y+1;
            }else{
                x = x+1;
            }
        }else{
            if (y+1>endPoint.getY()){
                if (x+1>endPoint.getX()){
                    return points;
                }
                x = x+1;
            }else{
                y = y+1;
            }
        }
        point.setX(x);
        point.setY(y);
        points.add(point);
        return line(point,endPoint,points);
    }


}

@Data
class Point{
    private int id;
    private int x;
    private int y;
}


@Data
class Good {
    private int id;
    private int x;
    private int y;
    private int num;
}



