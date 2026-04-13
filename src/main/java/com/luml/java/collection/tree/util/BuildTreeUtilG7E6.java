package com.luml.java.collection.tree.util;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.luml.java.collection.tree.vo.ITreeVO;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 列表转成树形列表通用方法
 *
 * @auther caolu@e6yun.com
 * @date 2019/3/1 18:32
 */
public class BuildTreeUtilG7E6 {


    /**
     * 默认情况下传入0 作为 rootId  自定义情况 自行传入
     * @param list list
     * @return List<T>
     */
    public static <T extends ITreeVO> List<T> listToTree(List<T> list){
        return listToTree(list,0);
    }

    /**
     * 将list转成Tree
     * @param list  处理好的list数据
     * @param rootId  指定的跟部门id
     * @return List<T>
     */
    public static <T extends ITreeVO> List<T> listToTree(List<T> list, Object rootId){
        Multimap<Object,T> multimap = LinkedListMultimap.create();
        for (T t : list) {
            multimap.put(t.getId(),t);
        }

        return  multimap.values().stream().peek(i->{
            if(multimap.containsKey(i.getPid())){
                Collection<T> iTreeVOS = multimap.get(i.getPid());
                for(T iTreeVO : iTreeVOS){
                    if(iTreeVO.getChildren() == null){
                        iTreeVO.setChildren(new LinkedList<>());
                    }
                    iTreeVO.getChildren().add(i);
                }
            }
        }).filter(x->x.getPid().equals(rootId)).collect(toList());
    }


}

