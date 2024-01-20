package domain;


import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {
    private Integer id;

    private String name;
//
//    private String code;

    private Integer parentId;

    private List<Node> childrenNodeList;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Node> getChildrenNodeList() {
        return childrenNodeList;
    }

    public void setChildrenNodeList(List<Node> childrenNodeList) {
        this.childrenNodeList = childrenNodeList;
    }


    public static void main(String[] args) {
        Node node = new Node();
        node.setId(1);
        node.setName("一级");
        node.setParentId(-1);

        Node node1 = new Node();
        node1.setId(2);
        node1.setName("二级-1");
        node1.setParentId(1);

        Node node2 = new Node();
        node2.setId(3);
        node2.setName("二级-2");
        node2.setParentId(1);

        Node node3 = new Node();
        node3.setId(4);
        node3.setName("三级-1");
        node3.setParentId(3);

        List<Node> nodeList = Arrays.asList(node, node1, node2, node3);

      Node resNode=   getNodeTree(nodeList);
        System.out.println(resNode);

    }


    public static  Node getNodeTree(List<Node> nodeList ){

        if(CollectionUtils.isEmpty(nodeList)){
            return null;
        }
        Node node=null;
        for(Node tempNode:nodeList){
           node= buildNode(node,tempNode);
        }


        return node;
    }

    public static Node buildNode(Node node,Node tempNode){
        if(node==null){
            node=new Node();
            node.setId(tempNode.id);
            node.setName(tempNode.getName());
            node.setParentId(tempNode.getParentId());
            node.setChildrenNodeList(tempNode.getChildrenNodeList());
            return node;
        }
        if(tempNode.parentId==node.id){
            if(node.childrenNodeList==null){
                List<Node> nodeList=new ArrayList<>();
                nodeList.add(tempNode);
                node.childrenNodeList=nodeList;
            }else{
                node.getChildrenNodeList().add(tempNode);
            }

            return node;
        }
        if(tempNode.parentId<node.id){
            Node parentNode=buildNode(null,tempNode);
             buildNode(parentNode,node);
        }
        if(tempNode.parentId > node.id){
              if(node.childrenNodeList!=null){
                  for(Node childNode : node.childrenNodeList){
                     node= buildNode(childNode,tempNode);
                  }
              }else{
                node=   buildNode(null,tempNode);
              }

        }
       return node;
    }

}








