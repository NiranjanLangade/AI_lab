package MissionariesCannibals;
import java.util.*;
class pair<F,S>{
    private F first;
    private S second;
    pair(F first,S second)
    {
        this.first=first;
        this.second=second;
    }
    public F getFirst()
    {
        return first;
    }

    public S getSecond()
    {
        return second;
    }
    public void setFirst(F ch)
    {
        this.first=ch;
        return;
    }
    public void setSecond(S ch)
    {
        this.second=ch;
        return;
    }
}

public class MissionariesAndCannibals {
    public static boolean isGoal(List<Integer>state)
    {
        return (state.get(2)==3 && state.get(3)==3);

    }
    public static boolean notValid(List<Integer>state)
    {
        if((state.get(1)>state.get(0) && state.get(0)!=0)||(state.get(2)>state.get(3)&& state.get(3)!=0))
            return true;

        return false;

    }
    public static void main(String[]args)
    {
    List<Integer>initial=Arrays.asList(3, 3, 0, 0, 0);
    Queue<pair<List<Integer>,List<pair<Character,Character>>>>q=new LinkedList<>();
    List<pair<Character,Character>>mov=new ArrayList<>();
    q.add(new pair<>(initial,mov));
    while(q.size()>0)
    {
        List<Integer>state=q.peek().getFirst();
        List<pair<Character,Character>>moves=q.peek().getSecond();
        if(notValid(state))
            continue;
        if(isGoal(state))
        {

            for(pair movements:moves)
            {
                System.out.println(movements.getFirst()+" "+movements.getSecond());
            }
            break;
        }
        for(int i=0;i<=2;i++)
        {
            for(int j=0;j<=2;j++)
            {
                List<Integer>curr=state;

                    if(i+j<=2 && i+j>0) {
                        if (state.get(4) == 0 && curr.get(0) >= i && curr.get(1) >= j) {
                            curr.set(0, curr.get(0) - i);
                            curr.set(1, curr.get(1) - j);
                            curr.set(2, curr.get(2) + i);
                            curr.set(3, curr.get(3) + j);
                            pair<Character, Character> p = new pair<>('#', '#');
                            if (i == 2) {
                                p.setFirst('M');
                                p.setSecond('M');
                            }
                            if (j == 2) {
                                p.setFirst('C');
                                p.setSecond('C');
                            }
                            if (i == 1) {
                                p.setFirst('M');
                            }
                            if (j == 1) {
                                p.setSecond('C');
                            }
                            moves.add(p);
                            curr.set(4, 1 - curr.get(4));
                            q.add(new pair<>(curr, moves));
                            moves.remove(moves.size() - 1);
                        }
                        else if (state.get(4) == 1 && curr.get(2) >= i && curr.get(3) >= j) {

                            curr.set(0, curr.get(0) + i);
                            curr.set(1, curr.get(1) + j);
                            curr.set(2, curr.get(2) - i);
                            curr.set(3, curr.get(3) - j);

                            pair<Character, Character> p = new pair<>('#', '#');
                            if (i == 2) {
                                p.setFirst('M');
                                p.setSecond('M');
                            }
                            if (j == 2) {
                                p.setFirst('C');
                                p.setSecond('C');
                            }
                            if (i == 1) {
                                p.setFirst('M');
                            }
                            if (j == 1) {
                                p.setSecond('C');
                            }
                            moves.add(p);
                            curr.set(4, 1 - curr.get(4));
                            q.add(new pair<>(curr, moves));
                            moves.remove(moves.size() - 1);

                        }
                    }
                }
            }
        }
    }
    }
