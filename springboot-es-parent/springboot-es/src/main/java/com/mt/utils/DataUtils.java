package com.mt.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.util.*;

/**
 * 数据工具类
 */
public abstract class DataUtils {
    
    @SuppressWarnings("unchecked")
    public static <K,V> Map<K,V> listToMap(List<V> list,String propertyName){
        try{
            HashMap<K,V> map = new HashMap<K,V>();
            if(list != null && list.size() > 0){
                for(V v : list){
                     PropertyDescriptor pd2 = new PropertyDescriptor(propertyName,  v.getClass()); 
                     K k = (K) pd2.getReadMethod().invoke(v);
                     map.put(k, v);
                }
            }
            return map;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new HashMap<K,V>();
    }
    
    public static <K,V> List<V> mapToValues(Map<K,V> map){
        return new ArrayList<V>(map.values());
    }
    
    public static <K,V> List<K> mapToKeys(Map<K,V> map){
        List<K> keys = new ArrayList<K>(map.size());
        Iterator<K> it = map.keySet().iterator();
        while(it.hasNext()){
            keys.add(it.next());
        }
        return keys;
    }
    
    public static <K,V> List<K> listToKeys(List<V> list,String propertyName){
        try{
            List<K> _list = new ArrayList<K>();
            if(list != null && list.size() > 0){
                for(V v : list){
                    PropertyDescriptor pd2 = new PropertyDescriptor(propertyName,  v.getClass()); 
                    K key = (K) pd2.getReadMethod().invoke(v);
                    _list.add(key);
                }
            }
            return _list;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ArrayList<K>();
    }
    
    public static <K,V> List<K> listToKeysNoRepeat(List<V> list,String propertyName){
        try{
            HashSet<K> set = new HashSet<K>();
            if(list != null && list.size() > 0){
                for(V v : list){
                    PropertyDescriptor pd2 = new PropertyDescriptor(propertyName,  v.getClass()); 
                    K key = (K) pd2.getReadMethod().invoke(v);
                    set.add(key);
                }
            }
            return new ArrayList<K>(set);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ArrayList<K>();
    }
    
    public static <K,V> K getPropertyValue(V v,String propertyName){
        try{
            PropertyDescriptor pd2 = new PropertyDescriptor(propertyName,  v.getClass()); 
            return (K) pd2.getReadMethod().invoke(v);  
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static <V> List<V> sortByAsc(List<V> list,String propertyName){
        if(list != null && list.size() > 0){
            Collections.sort(list, new Comparator<V>() {
                @Override
                public int compare(V o1, V o2) {
                    try {
                        PropertyDescriptor pd1 = new PropertyDescriptor(propertyName,  o1.getClass());
                        PropertyDescriptor pd2 = new PropertyDescriptor(propertyName,  o2.getClass());
                        Comparable c1 = (Comparable) pd1.getReadMethod().invoke(o1);
                        Comparable c2 = (Comparable) pd2.getReadMethod().invoke(o2);
                        return c1.compareTo(c2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                }
            });
        }
        return list;
    }
    
    public static <V> List<V> sortByDesc(List<V> list,String propertyName){
        if(list != null && list.size() > 0){
            Collections.sort(list, new Comparator<V>() {
                @Override
                public int compare(V o1, V o2) {
                    try {
                        PropertyDescriptor pd1 = new PropertyDescriptor(propertyName,  o1.getClass());
                        PropertyDescriptor pd2 = new PropertyDescriptor(propertyName,  o2.getClass());
                        Comparable c1 = (Comparable) pd1.getReadMethod().invoke(o1);
                        Comparable c2 = (Comparable) pd2.getReadMethod().invoke(o2);
                        return -c1.compareTo(c2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } 
                    return 0;
                }
            });
        }
        return list;
    }

    /**
     * 
     * list分成n份，余下的第n+1份
     */
    public static <V> List<List<V>> split(List<V> list,int n){
        List<List<V>> dList = new ArrayList<List<V>>();
        if(n > 0 && list != null && list.size() > 0){
            if(list.size() < n){
                List<V> tList = list.subList(0, list.size());
                dList.add(tList);
            }else{
                int len = list.size() / n;
                for(int i = 0;i < n;i++){
                    List<V> tList = list.subList(i * len, (i + 1) * len);
                    dList.add(tList);
                }
                int idx = n * len;
                if(idx < list.size()){
                    List<V> tList = list.subList(idx, list.size());
                    dList.add(tList);
                }
            }
        }
        return dList;
    }
    /**
     *
     * list分成n份，余数补最后一份
     */
    public static <V> List<List<V>> split2(List<V> list,int n){
        List<List<V>> dList = new ArrayList<List<V>>();
        if(n > 0 && list != null && list.size() > 0){
            if(list.size() < n){
                List<V> tList = list.subList(0, list.size());
                dList.add(tList);
            }else{
                int len = list.size() / n;
                for(int i = 0;i < n;i++){
                    List<V> tList = list.subList(i * len, (i + 1) * len);
                    dList.add(tList);
                }
                int idx = n * len;
                if(idx < list.size()){
                    List<V> tList = list.subList(idx, list.size());
                    if(dList.size() == 0){
                        dList.add(tList);
                    }else{
                        List<V> lastList = new ArrayList<>(dList.get(dList.size() - 1));
                        lastList.addAll(tList);
                        dList.set(dList.size() -1,lastList);
                    }
                }
            }
        }
        return dList;
    }
    
    public static <V> V[] listToArray(List<V> list,Class<V> clazz){
        V[] array = (V[]) Array.newInstance(clazz, list.size());
        Array.newInstance(clazz, list.size());
        list.toArray(array);
        return array;
    }
    
    public static boolean arrayContain(String[] arr,String src){
        if(arr != null && arr.length > 0){
            for(String str : arr){
                if(str.equals(src))
                    return true;
            }
        }
        return false;
    }

    public static <T> List<T> asList(T t){
        List<T> list = new ArrayList<T>();
        list.add(t);
        return list;
    }

    public static <T> boolean equals2(T t1,T t2){
        if(t1 != null){
            return t1.equals(t2);
        }
        return false;
    }

    public static <T> boolean equals3(T t1,T t2){
        if(t1 != null){
            return t1.equals(t2);
        }else{
            if(t2 == null){
                return true;
            }
        }
        return false;
    }

    public static boolean equalsString(String str1,String str2){
        return StringUtils.isNotBlank(str1) && str1.equals(str2);
    }

    public static String firstWordToUpperCase(String str){
        if(str != null && str.length() > 0){
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return null;
    }

    public static String joinByComma(List<String> list){
        StringJoiner sj = new StringJoiner(",");
        if(!CollectionUtils.isEmpty(list)) {
            list.forEach(str->{
                sj.add(str);
            });
        }
        return sj.toString();
    }

    public static String joinBySeparator(List<String> list,String separator){
        StringJoiner sj = new StringJoiner(separator);
        if(!CollectionUtils.isEmpty(list)) {
            list.forEach(str->{
                sj.add(str);
            });
        }
        return sj.toString();
    }

    public static List<String> splitByComma(String str){
        if(StringUtils.isNotBlank(str)){
            String[] arr = str.split(",");
            return new ArrayList<String>(Arrays.asList(arr));
        }
        return new ArrayList<>();
    }

    public static List<Integer> splitIntByComma(String str){
        if(StringUtils.isNotBlank(str)){
            String[] arr = str.split(",");
            List<Integer> list = new ArrayList<>();
            for(String s : arr){
                try {
                    list.add(Integer.parseInt(s));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            return list;
        }
        return new ArrayList<>();
    }

    public static List<Long> splitLongByComma(String str){
        if(StringUtils.isNotBlank(str)){
            String[] arr = str.split(",");
            List<Long> list = new ArrayList<>();
            for(String s : arr){
                try {
                    list.add(Long.parseLong(s));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            return list;
        }
        return new ArrayList<>();
    }

    public static List<String> splitBySeparator(String str,String separator){
        if(StringUtils.isNotBlank(str)){
            String[] arr = str.split(separator);
            return new ArrayList<String>(Arrays.asList(arr));
        }
        return new ArrayList<>();
    }

    public static String stringToEllipsis(String src,int keep){
        int minKeep = Math.min(src.length(),keep);
        return src.substring(0,minKeep) + "...";
    }

    public static boolean listContain(List<String> list,String src){
        if(list != null && list.size() > 0){
            for(String str : list){
                if(str.equals(src))
                    return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(DataUtils.stringToEllipsis("abcdef",60));
        System.out.println(DataUtils.stringToEllipsis("abcdef",0));
        System.out.println(DataUtils.firstWordToUpperCase("abc"));
    }
}
