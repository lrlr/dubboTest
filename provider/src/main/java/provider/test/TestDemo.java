package provider.test;

import org.openjdk.jol.info.ClassLayout;

/**
 * @ClassName TestDemo
 * @Description: TODO 
 * @Author lirui
 * @Date 2020/5/2 
 * @Version V1.0
**/
public class TestDemo {
    private static HeadObject headObject=new HeadObject();
    public static void main(String[] args) {
        System.out.println(ClassLayout.parseInstance(headObject).toPrintable());
    }
}
