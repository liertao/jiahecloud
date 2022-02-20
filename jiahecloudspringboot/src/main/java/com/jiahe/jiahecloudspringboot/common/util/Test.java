package com.jiahe.jiahecloudspringboot.common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception{
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);list.add(2);list.add(3);list.add(4);list.add(5);
        list.add(6);list.add(7);list.add(8);list.add(9);list.add(10);
        Iterator<Integer> it = list.iterator();
        System.out.print(list);
        while(it.hasNext()){
            System.out.println(it.next());
            Thread.sleep(2000);
        }
    }
}
