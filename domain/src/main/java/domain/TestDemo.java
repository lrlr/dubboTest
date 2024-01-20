package domain;

public class TestDemo {
    private int[] num;
    int size;
    int flag=0;
    public TestDemo(int cap){
        num=new int[cap];
        size=0;
    }

    public boolean push(int a){
        if(size>=num.length){
            return false;
        }
        num[size]=a;
        size++;
        return true;
    }

    public int pull(){
         int res =num[0];
         for(int i=1;i<size;i++){
             num[i-1]=num[i];
         }
         size--;
         return res;
    }

}
