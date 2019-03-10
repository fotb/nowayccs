package com.ccs.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ccs.vo.LifeCategoryVO;

public class LifeCategoryTreeUtil {

	private List<LifeCategoryVO> voList = new ArrayList<LifeCategoryVO>();

	public LifeCategoryTreeUtil(List<LifeCategoryVO> list) {
		voList = list;
	}
	
	
	/**
	*
	* @param nodeId
	* @return
	*/
	public LifeCategoryVO getNodeById(String nodeId) {
		LifeCategoryVO vo = new LifeCategoryVO();
		for (LifeCategoryVO item : voList) {
			if (item.getCode().equals(nodeId)) {
				vo = item;
				break;
			}
		}
		return vo;
	}

	/**
	*
	* @param nodeId
	* @return
	*/
	public List<LifeCategoryVO> getChildrenNodeById(String nodeId) {
		List<LifeCategoryVO> childrenList = new ArrayList<LifeCategoryVO>();
		for (LifeCategoryVO item : voList) {
			if (item.getParentCode().equals(nodeId)) {
				childrenList.add(item);
			}
		}
		
        Collections.sort(childrenList,new Comparator<LifeCategoryVO>(){  
            @Override  
            public int compare(LifeCategoryVO b1, LifeCategoryVO b2) {  
                return b1.getPid().compareTo(b2.getPid());  
            }  
        }); 
		return childrenList;
	}

	/**
	* 递归生成Tree结构数据
	* @param rootId
	* @return
	*/
	public EasyUiTree generateEasyUiTree(String rootId) {
		LifeCategoryVO root = this.getNodeById(rootId);
		
		if(null == root) {
			return null;
		} else {
			EasyUiTree node = new EasyUiTree();
			node.setId(root.getCode());
			node.setText(root.getName());
			if("0".equals(root.getCode())) {
				node.setState("open");
			} else if(root.getCode().length() == 2){
				node.setState("closed");
			}
			
			List<LifeCategoryVO> childrenList = this.getChildrenNodeById(rootId);
			for (LifeCategoryVO item : childrenList) {
				EasyUiTree sonNode = this.generateEasyUiTree(item.getCode());
				node.getChildren().add(sonNode);
			}
			return node;
		}
	}
}
