package EightPuzzle;
import java.util.Arrays;
import java.util.PriorityQueue;

class node implements Comparable<node>{
    int [][]state;
    int cost;
    int heuristic;
    node parent;
    node(int [][]state, int cost, int heuristic, node parent)
    {
        this.state=state;
        this.cost=cost;
        this.heuristic=heuristic;
        this.parent=parent;
    }
    @Override
    public int compareTo(node o)
    {
        return ((this.cost+this.heuristic)-(o.cost+o.heuristic));
    }

    @Override
    public boolean equals(Object o)
    {
     if(this==o)
         return true;
     if(o==null || getClass()!=o.getClass())
         return false;

     node n=(node) o;
     return Arrays.deepEquals(n.state,state);
    }

    @Override
    public int hashCode()
    {
        return Arrays.deepHashCode(state);
    }
}
public class EightPuzzle{
    public static void main(String[]args)
    {
        int [][]intial={
                {1,2,3},
                {4,0,5},
                {6,7,8}
        };

        solve(intial);
    }
    public static void solve(int[][]initial)
    {
        int [][]goal={
                {1,2,3},
                {4,5,6},
                {7,8,0}
        };
        PriorityQueue<node>pq=new PriorityQueue<>();
        node init=new node(initial,0,calculateHeuristic(initial,goal),null);
        pq.add(init);
        while(!pq.isEmpty())
        {
            node n=pq.poll();
            if(Arrays.deepEquals(n.state,goal))
            {
                printSolution(n);
                return;
            }
            int [][]moves={   {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for(int i=0;i<4;i++)
            {
                int nrow=moves[i][0]+getZeroX(n.state);
                int ncol=moves[i][1]+getZeroY(n.state);
                if(nrow>=0 && nrow<3 && ncol>=0 && ncol<3)
                {
                    int[][] newPuzzle=swap(n.state,nrow,ncol,getZeroX(n.state),getZeroY(n.state));
                    int cost=n.cost+1;
                    int heuristic=calculateHeuristic(newPuzzle,goal);
                    node puzzle=new node(newPuzzle,cost,heuristic,n);
                    if(!pq.contains(puzzle)){
                        pq.add(puzzle);
                    }
                }

            }
        }
        System.out.print("No Solution Found");
    }
    public static int getZeroX(int[][]state)
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(state[i][j]==0)
                    return i;
            }
        }
        return -1;
    }
    public static int getZeroY(int[][]state)
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(state[i][j]==0)
                    return j;
            }
        }
        return -1;
    }
    public static int calculateHeuristic(int [][]state,int[][]goal)
    {
        int heuristic=0;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(state[i][j]!=goal[i][j])
                {
                    heuristic++;
                }
            }
        }
        return heuristic;
    }
    public static int [][]swap(int [][]state,int x2,int y2,int x1,int y1)
    {
        int [][]newPuzzle=new int[3][3];
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                newPuzzle[i][j]=state[i][j];
            }
        }
        int temp=newPuzzle[x1][y1];
        newPuzzle[x1][y1]=newPuzzle[x2][y2];
        newPuzzle[x2][y2]=temp;
        return newPuzzle;
    }
    public static void printPuzzle(int [][]state)
    {
        for(int []row:state)
        {
            for(int num:row)
            {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void printPath(node n)
    {
        if(n!=null)
        {
            printPath(n.parent);
            printPuzzle(n.state);
            System.out.println();
        }
    }
    public static void printSolution(node n)
    {
        System.out.println("cost "+n.cost);
        printPath(n);
    }
}


