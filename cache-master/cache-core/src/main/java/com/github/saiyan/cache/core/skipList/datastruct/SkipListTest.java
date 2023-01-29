package com.github.saiyan.cache.core.skipList.datastruct;

import com.github.saiyan.cache.core.skipList.SkipList;

public class SkipListTest {
    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.put("1",1);
        skipList.put("5",5);
        skipList.put("3",3);
        skipList.put("7",7);
        skipList.put("8",8);

        System.out.println("skipList.get(\"7\") = " + skipList.get("7"));


    }
}
