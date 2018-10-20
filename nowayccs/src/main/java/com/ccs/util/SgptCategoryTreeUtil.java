package com.ccs.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ccs.vo.EventCategoryVO;

public class SgptCategoryTreeUtil {

	private List<EventCategoryVO> voList = new ArrayList<EventCategoryVO>();

	public SgptCategoryTreeUtil(List<EventCategoryVO> list) {
		voList = list;
	}
	
	
	/**
	*
	* @param nodeId
	* @return
	*/
	public EventCategoryVO getNodeById(String nodeId) {
		EventCategoryVO vo = new EventCategoryVO();
		for (EventCategoryVO item : voList) {
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
	public List<EventCategoryVO> getChildrenNodeById(String nodeId) {
		List<EventCategoryVO> childrenList = new ArrayList<EventCategoryVO>();
		for (EventCategoryVO item : voList) {
			if (item.getParentCode().equals(nodeId)) {
				childrenList.add(item);
			}
		}
		
        Collections.sort(childrenList,new Comparator<EventCategoryVO>(){  
            @Override  
            public int compare(EventCategoryVO b1, EventCategoryVO b2) {  
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
		EventCategoryVO root = this.getNodeById(rootId);
		
		if(null == root) {
			return null;
		} else {
			EasyUiTree node = new EasyUiTree();
			node.setId(root.getCode());
			node.setText(root.getName());
			
			List<EventCategoryVO> childrenList = this.getChildrenNodeById(rootId);
			for (EventCategoryVO item : childrenList) {
				EasyUiTree sonNode = this.generateEasyUiTree(item.getCode());
				node.getChildren().add(sonNode);
			}
			return node;
		}
	}
}
