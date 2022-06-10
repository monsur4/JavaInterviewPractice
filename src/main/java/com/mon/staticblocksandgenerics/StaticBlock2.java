package com.mon.staticblocksandgenerics;

public class StaticBlock2 extends StaticBlock{
    static{
        System.out.println("inside static block 2");
    }
    {
        System.out.println("inside non-static block 2");
        System.out.println(super.j);
    }
    public StaticBlock2(int k2){
        super(k2);
        System.out.println("inside constructor 2");
    }
}

class StaticBlock3{
    public StaticBlock3(){
        System.out.println("in staticblock3 constructor");
    }
}
