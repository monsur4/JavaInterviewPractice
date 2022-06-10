package com.mon.staticblocksandgenerics;

import java.util.Arrays;

public class StaticBlock {
    static int i;
    int j;
    int k;

    public StaticBlock(int k){
        this.k = k;
        System.out.println("inside constructor");
    }
    public void run(String... args){
        Arrays.stream(args).forEach(System.out::println);
    }
    static {
        i = 56;
        System.out.println("inside static block 1 a");
    }
    {
        j += 20;
        System.out.println("inside non-static block 1");
    }
}
