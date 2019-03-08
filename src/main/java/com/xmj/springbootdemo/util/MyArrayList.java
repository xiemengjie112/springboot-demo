package com.xmj.springbootdemo.util;

/**
 * Description:
 * Author: xieMengJie
 * CreateDate: 2019/2/22 21:02
 */
public class MyArrayList {

        private Object[] elementData;       //底层数组
        private int size;                   //数组大小

        public int size() {
            /*
             * 返回数组大小
             */
            return size;
        }

        public MyArrayList() {
            /*
             * 无参构造器，通过显式调用含参构造器
             */
            this(10);
        }

        public MyArrayList(int initialCapacity) {
            /*
             * 1.含参构造器
             * 2.要对传入的初始量的合法性进行检测
             * 3.通过新建数组实现
             */
            if (initialCapacity < 0) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            elementData = new Object[initialCapacity];
        }

        public boolean isEmpty() {
            /*
             * 判断是否为空
             */
            return size == 0;
        }

        public Object get(int index) {
            /*
             * 1.获取指定下标的对象
             * 2.下标合法性检测
             */
            rangeCheck(index);
            return elementData[index];
        }

        public boolean add(Object obj) {
            /*
             * 添加对象（不指定位置）
             * 注意数组扩容
             */
            ensureCapacity();
            elementData[size] = obj;
            size++;
            return true;
        }

        public void add(int index, Object obj) {
            /*
             * 插入操作（指定位置）
             * 1.下标合法性检查
             * 2.数组容量检查、扩容
             * 3.数组复制（原数组，开始下标，目的数组，开始下标，长度）
             */
            rangeCheck(index);
            ensureCapacity();
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = obj;
            size++;
        }

        public Object remove(int index) {
            /*
             * 1.删除指定下标对象，并返回其值
             * 2.下标合法性检测
             * 3.通过数组复制实现
             * 4.因为前移，数组最后一位要置为空
             */
            rangeCheck(index);
            int arrnums = size - index - 1;
            Object oldValue = elementData[index];
            if (arrnums > 0) {
                System.arraycopy(elementData, index + 1, elementData, index, arrnums);
            }
            elementData[--size] = null;
            return oldValue;
        }

        public boolean remove(Object obj) {
            /*
             * 1.删除指定对象
             * 2.通过遍历
             * 3.equals的底层运用，找到下标，调用remove(int i)
             */
            for (int i = 0; i < size; i++) {
                if (get(i).equals(obj)) {         //注意底层用的是equals不是“==”
                    remove(i);
                }
                break;
            }
            return true;
        }

        public Object set(int index, Object obj) {
            /*
             * 1.将指定下标的对象改变
             * 2.下标合法性检查
             * 3.直接通过数组的赋值来实现改变
             * 4.返回旧值
             */
            rangeCheck(index);
            Object oldValue = elementData[index];
            elementData[index] = obj;
            return oldValue;
        }

        private void rangeCheck(int index) {
            /*
             * 对下标的检查
             */
            if (index < 0 || index >= size) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        private void ensureCapacity() {
            /*
             * 1.对容器容量的检查
             * 2.数组扩容，通过数组复制来实现（量和值两者都要保障）
             */
            if (size == elementData.length) {
                Object[] newArray = new Object[size * 2 + 1];
                System.arraycopy(elementData, 0, newArray, 0, elementData.length);
                elementData = newArray;
            }
        }

        public static void main(String[] args) {

                    MyArrayList mylist = new MyArrayList();
            mylist.add("123");
            mylist.add("呵呵呵");
            mylist.add("哦哦");
            mylist.add("哈哈哈");
            mylist.add(1, "你好");
            String old = (String) mylist.remove(3);    //返回的是旧值
            System.out.println(old);
            System.out.println(mylist.remove("哦哦"));
            System.out.println(mylist.isEmpty());
            System.out.println(mylist.get(1));
            System.out.println(mylist.size());
            System.out.println(mylist.set(2, "你好啊"));   //返回旧值
            System.out.println(mylist.get(2));
        }

}
