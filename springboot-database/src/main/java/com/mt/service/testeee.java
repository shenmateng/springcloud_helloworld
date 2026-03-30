package com.mt.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mt.ai.AIResultQuestionVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mateng
 * @program: spring-cloud
 * @Date: 2025/2/28 16:03
 * @Version:
 * @Description:
 * ░░░░░░░░░░░░░░░░░░░░░░░░▄░░
 * ░░░░░░░░░▐█░░░░░░░░░░░▄▀▒▌░
 * ░░░░░░░░▐▀▒█░░░░░░░░▄▀▒▒▒▐
 * ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐
 * ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐
 * ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌
 * ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒
 * ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐
 * ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄
 * ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒
 * ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒
 * You are not expected to understand this
 */
public class testeee {

    private static List<String> screenMiddlewareVOS = new ArrayList<>();
    public static void main(String[] args) throws JsonProcessingException {
//        String json = "[{\"quContent\": \"请简述Java中ArrayList和LinkedList的区别。\", \"answer\": \"ArrayList是基于数组实现的，支持随机访问元素，适合频繁查询操作；LinkedList基于双向链表实现，插入和删除操作更高效，适合频繁的增删操作。\", \"analysis\": \"本题考察对Java集合框架中两种常见列表实现的理解，包括它们的内部结构和适用场景。\"}, {\"quContent\": \"解释Java中的HashSet是如何保证元素不重复的。\", \"answer\": \"HashSet使用HashMap来存储元素，通过计算元素的hashCode来确定其存储位置，如果两个元素的hashCode相同，则通过equals方法进一步判断是否相等，从而保证元素的唯一性。\", \"analysis\": \"本题旨在了解HashSet的工作原理以及它如何利用HashMap的特性来实现元素的唯一性。\"}, {\"quContent\": \"描述Java中TreeSet的自然排序和定制排序的区别。\", \"answer\": \"自然排序是按照元素自身的自然顺序（如整数的大小）进行排序；定制排序是通过实现Comparator接口来定义元素的排序规则，可以按照自定义的逻辑进行排序。\", \"analysis\": \"此题用于区分TreeSet的两种排序方式，理解自然排序和定制排序的概念及应用场景。\"}, {\"quContent\": \"阐述在Java中ConcurrentHashMap处理并发访问的机制。\", \"answer\": \"ConcurrentHashMap通过分段锁的方式允许多个线程同时访问不同的段，减少锁的竞争；同时使用CAS（Compare-And-Swap）操作来确保原子性更新，提高并发性能。\", \"analysis\": \"本题考查对ConcurrentHashMap并发机制的理解，包括其内部结构和如何处理并发冲突。\"}, {\"quContent\": \"比较Java中CopyOnWriteArrayList与ArrayList在多线程环境下的性能表现。\", \"answer\": \"CopyOnWriteArrayList在写操作时会复制整个底层数组，因此写操作成本较高，但读操作无锁，适用于读多写少的场景；而ArrayList在多线程下需要外部同步措施，否则可能导致并发问题，适用于单线程或少量写操作的场景。\", \"analysis\": \"该题目要求对比两种线程安全的列表实现在多线程环境中的性能差异，理解各自的优缺点和适用场景。\"}]";


        String ss = "```json\n" +
                "[{\"quContent\": \"请简述Java中ArrayList的特点。\", \"answer\": \"动态数组，可以自动调整大小，允许重复元素，非线程安全。\", \"analysis\": \"ArrayList是基于动态数组实现的集合类，它能够根据需要自动调整其大小。ArrayList是有序的集合，可以包含重复的元素，但它不是线程安全的，即在多线程环境下操作可能会出现并发修改的问题。\"}, {\"quContent\": \"解释Java中HashMap的工作原理。\", \"answer\": \"通过键值对存储元素，使用哈希表实现，根据键的哈希码计算索引。\", \"analysis\": \"HashMap是一种基于哈希表实现的Map接口，它存储键值对并且不允许重复的键。当插入元素时，根据键的哈希码计算出索引，然后在链表或红黑树（取决于桶中元素的数量）中处理冲突。\"}, {\"quContent\": \"比较Java中HashSet和TreeSet的区别。\", \"answer\": \"HashSet基于哈希表实现，不保证顺序；TreeSet基于红黑树实现，保证元素的排序。\", \"analysis\": \"HashSet使用哈希表来存储元素，它不允许重复的元素且不保证元素的顺序。而TreeSet则使用红黑树来保证元素处于排序状态，它同样不允许重复元素。\"}, {\"quContent\": \"说明Java中LinkedList的特点。\", \"answer\": \"双向链表实现，允许null元素，可以高效地进行添加和删除操作。\", \"analysis\": \"LinkedList是一个双向链表，它的每个节点包含对前一个和后一个节点的引用。这使得在列表的任何位置添加或删除节点都非常高效，但访问元素的速度较慢，因为需要从头开始遍历。\"}, {\"quContent\": \"描述Java中PriorityQueue是如何工作的。\", \"answer\": \"基于优先级堆实现，元素的排序基于自然排序或者Comparator。\", \"analysis\": \"PriorityQueue是一个基于优先级堆实现的队列，它的元素按照自然排序或者提供的Comparator进行排序。每次poll()操作都会返回队列中优先级最高的元素。\"}]\n" +
                "```";
        //帮我对ss截取json部分
        String json1 = ss.substring(ss.indexOf("["),ss.lastIndexOf("]")+1);
        System.out.println(json1);
        // 创建 ObjectMapper 实例
        ObjectMapper objectMapper = new ObjectMapper();
        // 将 JSON 转换为 List<User>
        List<AIResultQuestionVO> users = objectMapper.readValue(json1, new TypeReference<List<AIResultQuestionVO>>(){});

        screenMiddlewareVOS.clear();
        System.out.println(screenMiddlewareVOS);

        SafetyReportType safetyReportType = SafetyReportType.fromCode(1);
        System.out.println(safetyReportType);
    }
}
