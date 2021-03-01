package customer.service;

import java.util.ArrayList;

/**
 * @ClassName Test
 * @Description: TODO 
 * @Author lirui
 * @Date 2020/3/18 
 * @Version V1.0
**/
public class Test {
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("7");
        ArrayList<String> list1=new ArrayList<>();
        list1.add("1");


        list1.add("7");
        list.removeAll(list1);
    }
}
