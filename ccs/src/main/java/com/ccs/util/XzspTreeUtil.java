package com.ccs.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ccs.vo.XzspIndexVO;

public class XzspTreeUtil {

	private List<XzspIndexVO> xzspIndexVOList = new ArrayList<XzspIndexVO>();

	public XzspTreeUtil(List<XzspIndexVO> list) {
		xzspIndexVOList = list;
	}
	
	
	/**
	*
	* @param nodeId
	* @return
	*/
	public XzspIndexVO getNodeById(String nodeId) {
		XzspIndexVO vo = new XzspIndexVO();
		for (XzspIndexVO item : xzspIndexVOList) {
			if (item.getListCode().equals(nodeId)) {
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
	public List<XzspIndexVO> getChildrenNodeById(String nodeId) {
		List<XzspIndexVO> childrenList = new ArrayList<XzspIndexVO>();
		for (XzspIndexVO item : xzspIndexVOList) {
			if (item.getParentCode().equals(nodeId)) {
				childrenList.add(item);
			}
		}
		
        Collections.sort(childrenList,new Comparator<XzspIndexVO>(){  
            @Override  
            public int compare(XzspIndexVO b1, XzspIndexVO b2) {  
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
		XzspIndexVO root = this.getNodeById(rootId);
		
		if(null == root) {
			return null;
		} else {
			EasyUiTree node = new EasyUiTree();
			node.setId(root.getListCode());
			node.setText(root.getListCode() + "-" + root.getName());
			
			List<XzspIndexVO> childrenList = this.getChildrenNodeById(rootId);
			for (XzspIndexVO item : childrenList) {
				EasyUiTree sonNode = this.generateEasyUiTree(item.getListCode());
				node.getChildren().add(sonNode);
			}
			return node;
		}
	}
}
