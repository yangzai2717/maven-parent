package com.pyy.Collection;

import org.junit.Test;

import java.util.*;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/14 10:25
 * @Description:
 */
public class ListClient {

    private Random random = new Random();


    @Test
    public void client() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; ++i) {
            list.add(random.nextInt(i + 1));
        }

        System.out.println(list);
        for (ListIterator<Integer> i = list.listIterator(); i.hasNext(); ) {
            if (i.next() == 0) {
                i.set(188);
                i.add(-1);
            }
        }

        System.out.println(list);
    }

    @Test
    public void asStack() {
        Deque<Integer> stack = new LinkedList<>();
        //Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < 10; ++i) {
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    @Test
    public void clientLinkedHashMap() {
        Map<String, Integer> map = new LinkedHashMap<>();
        System.out.print("insert key:");
        for (int i = 0; i < 20; ++i) {
            String key = String.valueOf(random.nextInt(10));
            System.out.printf(" %s", key);
            if (map.get(key) == null) {
                map.put(key, 1);
            } else {
                map.put(key, map.get(key) + 1);
            }
        }
        System.out.printf("%n iterator:");

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.printf(" <%s -> %s>", entry.getKey(), entry.getValue());
        }

    }

    @Test
    public void clientSortedMap() {
        // value作为期望的order
        SortedMap<Bean, Integer> map = new TreeMap<>();
        map.put(new Bean(true, 3.14, "true"), 1);
        // 该对象与上面的bean compare会返回0
        map.put(new Bean(false, 3.14, "false"), 1);
        map.put(new Bean(true, 3.14, "false"), 2);
        map.put(new Bean(false, 3.14, "true"), 0);
        System.out.println(map);

        Bean firstKey = map.firstKey();
        System.out.printf("first: %s -> %d%n", firstKey, map.get(firstKey));
        Bean lastKey = map.lastKey();
        System.out.printf("last: %s -> %d%n", lastKey, map.get(lastKey));

        map.remove(firstKey);
        Map.Entry<Bean, Integer> firstEntry = ((TreeMap<Bean, Integer>) map).firstEntry();
        System.out.printf("A first: %s -> %d%n", firstEntry.getKey(), firstEntry.getValue());
    }
}
