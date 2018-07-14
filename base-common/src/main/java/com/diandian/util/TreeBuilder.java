package com.diandian.util;

import com.diandian.domain.TreeNode;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2018-01-24
 */
public class TreeBuilder<T extends TreeNode> implements Comparator<T> {
    /**
     * 两层循环实现建树
     * @param treeNodes 传入的树节点列表
     * @return
     */
    public List<T> bulid(List<T> treeNodes) {
    	
    	
        List<T> trees = new LinkedList();

        for (T treeNode : treeNodes) {

            if (treeNode.getParent_id() == null) {
                treeNode.setDepth(0);
                trees.add(treeNode);
            }

            for (T it : treeNodes) {
                if (it.getParent_id() != null && it.getParent_id().equals(treeNode.getId())) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new HashSet());
                    }
                    it.setDepth(treeNode.getDepth()+1);
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }

	@Override
	public int compare(T o1, T o2) {
		// TODO Auto-generated method stub
		if(o1.getOrderNum() == o2.getOrderNum()) {
			return o1.compareTo(o2);
		}
		return o1.getOrderNum() - o2.getOrderNum();
	}
}
