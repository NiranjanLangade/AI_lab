package WaterJug;

import java.text.CollationElementIterator;
import java.util.*;

class state{
    int x;
    int y;
    state(int x,int y)
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

        state s=(state) obj;
        return (this.x==s.x)&&(this.y==s.y);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x,y);
    }
}
public class Waterjug1 {
    static int jug1=4;
    static int jug2=3;
    static state goal=new state(2,0);
    public static void main(String []args)
    {
        state init=new state(0,0);
        List<state>path=bfs(init);
        if(path!=null)
        {
            System.out.println("Path found:");
            for(state s:path)
            {

                System.out.println("("+s.x+" , "+s.y+")");
            }
        }
        else
        {
            System.out.println("No path Found");
        }

    }
    public static List<state>bfs(state init)
    {
        ArrayDeque<state>q=new ArrayDeque<>();
        HashSet<state>hs=new HashSet<>();
        HashMap<state,state>comeFrom=new HashMap<>();
        q.add(init);
        hs.add(init);
        while(!q.isEmpty())
        {
            state s=q.remove();
            if(s.equals(goal))
            {
                return  path(comeFrom,s);

            }
            for(state neighbour:getNeighbours(s))
            {
                if(!hs.contains(neighbour))
                {
                    q.add(neighbour);
                    hs.add(neighbour);
                    comeFrom.put(neighbour,s);
                }
            }


        }
        return null;
    }
    public static List<state>path(HashMap<state,state>comeFrom,state current)
    {
        List<state>ans=new ArrayList<>();
        while(comeFrom.containsKey(current))
        {
            ans.add(current);
            current=comeFrom.get(current);
        }
        Collections.reverse(ans);
        return ans;
    }
    public static List<state> getNeighbours(state current)
    {
        List<state>neighbours=new ArrayList<>();
        if(current.x<jug1)
        {
            neighbours.add(new state(jug1,current.y));
        }

        if(current.y<jug2)
        {
            neighbours.add(new state(current.x, jug2));
        }
        if(current.x>0)
        {
            neighbours.add(new state(0,current.y));
        }
        if(current.y>0)
        {
            neighbours.add(new state(current.x,0));
        }
        int pour1to2=Math.min(current.x, jug2- current.y);
        if(pour1to2>0)
        {
            neighbours.add(new state(current.x-pour1to2, current.y+pour1to2));
        }
        int pour2to1=Math.min(jug1- current.x, current.y);
        if(pour2to1>0)
        {
            neighbours.add(new state(current.x+pour2to1,current.y-pour2to1));
        }
        return neighbours;

    }
}
