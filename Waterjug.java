package WaterJug;

import java.util.*;

class node{
    int x;
    int y;
    node(int x,int y)
    {
        this.x=x;
        this.y=y;

    }
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj)
            return true;
        if(obj==null || getClass()!=obj.getClass())
            return false;

        node n=(node)obj;
        return (this.x==n.x)&&(this.y==n.y);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(x,y);
    }

}
public class Waterjug{
    static int jug1=4;
    static int jug2=3;

    static node goal=new node(2,0);
    public static void main(String []args)
    {
        node init=new node(0,0);
        List<node>path=astar(init);
        if(path!=null)
        {
            System.out.println("Path Found");
            for(node n:path)
            {
                System.out.println("("+n.x+" , "+n.y+")");
            }
        }
        else
        {
            System.out.println("Path not Found");
        }

    }
    public static List<node>astar(node init)
    {
        PriorityQueue<node>pq=new PriorityQueue<>(Comparator.comparingInt((node)->heuristic(node)));
        HashMap<node,node>comeFrom=new HashMap<>();
        comeFrom.put(init,null);
        HashMap<node,Integer>costSoFar=new HashMap<>();
        pq.add(init);
        costSoFar.put(init,0);
        while(!pq.isEmpty())
        {
            node current=pq.remove();
            if(current.equals(goal))
            {
                return path(current,comeFrom);
            }
            for(node n:getNeighbours(current))
            {
            int newCost=costSoFar.get(current)+1;
            if(!costSoFar.containsKey(n) || newCost<costSoFar.get(n))
            {
                costSoFar.put(n,newCost);
                comeFrom.put(n,current);
                pq.add(n);
            }
            }
        }
        return null;

    }
    public static List<node>path(node current,HashMap<node,node>comeFrom)
    {
        List<node>ans=new ArrayList<>();
        while(comeFrom.containsKey(current))
        {
            ans.add(current);
            current=comeFrom.get(current);
        }
        Collections.reverse(ans);
        return ans;
    }
    public static List<node>getNeighbours(node current)
    {
        List<node>neighbours=new ArrayList<>();
        if(current.x<jug1)
        {
            neighbours.add(new node(jug1,current.y));
        }

        if(current.y<jug2)
        {
            neighbours.add(new node(current.x, jug2));
        }
        if(current.x>0)
        {
            neighbours.add(new node(0,current.y));
        }
        if(current.y>0)
        {
            neighbours.add(new node(current.x,0));
        }
        int pour1to2=Math.min(current.x, jug2- current.y);
        if(pour1to2>0)
        {
            neighbours.add(new node(current.x-pour1to2, current.y+pour1to2));
        }
        int pour2to1=Math.min(jug1- current.x, current.y);
        if(pour2to1>0)
        {
            neighbours.add(new node(current.x+pour2to1,current.y-pour2to1));
        }
        return neighbours;
    }
    public static int heuristic(node current)
    {
        return Math.abs(current.x-goal.x)+Math.abs(current.y-goal.y);
    }
}