package com.zbs.java8.functioninter;

/**
 * description: InterfaceEntryImpl
 * date: 2020/3/31 14:24
 * author: zbs
 * version: 1.0
 *
 * @Override：表示是否可以重写方法
 */
public class InterfaceEntryImpl implements InterfaceEntry {
    @Override
    public void simplePrint() {
        System.out.println(this.getClass()+"接口实现类Impl，simplePrint()");
    }

//    @Override
//    public void simplePrint2() {
//
//    }
}
