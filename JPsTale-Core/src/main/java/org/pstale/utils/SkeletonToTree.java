package org.pstale.utils;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import com.jme3.animation.Bone;
import com.jme3.animation.Skeleton;

/**
 * 根据骨骼生成树
 * @author yanmaoyuan
 *
 */
public class SkeletonToTree {

	public TreeModel make(Skeleton ske) {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("骨骼");
		Bone[] roots = ske.getRoots();
		DefaultMutableTreeNode[] treeRoots = new DefaultMutableTreeNode[roots.length];
		for(int i=0; i<roots.length; i++) {
			
			treeRoots[i] = new DefaultMutableTreeNode(roots[i].getName());
			bfs(root, roots[i]);
		}
		return new DefaultTreeModel(root, false);
	}
	
	public void bfs(DefaultMutableTreeNode root, Bone bone) {
		DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(bone.getName());
		root.add(treeNode);
		
		for(Bone child : bone.getChildren()) {
			bfs(treeNode, child);
		}
	}
}
