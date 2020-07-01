package com.keeppeng.store;

public class StoreDemoOne {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        System.out.println(D.i);
        System.out.println(D.class.newInstance().j);
    }
}
class D{
    /**
     * 未定义，初始值为0
     */
    static int i;
    int j = 2;


}
