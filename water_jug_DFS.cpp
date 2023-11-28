#include <bits/stdc++.h>
using namespace std;
typedef pair<int, int> p;

void printPath(map<p, p> mp, p u)
{
    if (u.first == 0 && u.second == 0)
    {
        cout << 0 << " " << 0 << endl;
        return;
    }
    printPath(mp, mp[u]);
    cout << u.first << " " << u.second << endl;
}

bool DFS(int x, int y, int target, map<p, int> &m, map<p, p> &mp, p u)
{
    if (m[u] == 1)
        return false;

    if ((u.first > x || u.second > y || u.first < 0 || u.second < 0))
        return false;

    m[{u.first, u.second}] = 1;

    if (u.first == target || u.second == target)
    {
        printPath(mp, u);
        if (u.first == target)
        {
            if (u.second != 0)
                cout << u.first << " " << 0 << endl;
        }
        else
        {
            if (u.first != 0)
                cout << 0 << " " << u.second << endl;
        }
        return true;
    }

    if (DFS(x, y, target, m, mp, {u.first, y}))
    {
        mp[{u.first, y}] = u;
        return true;
    }

    if (DFS(x, y, target, m, mp, {x, u.second}))
    {
        mp[{x, u.second}] = u;
        return true;
    }

    int d = y - u.second;
    if (u.first >= d)
    {
        int c = u.first - d;
        if (DFS(x, y, target, m, mp, {c, y}))
        {
            mp[{c, y}] = u;
            return true;
        }
    }
    else
    {
        int c = u.first + u.second;
        if (DFS(x, y, target, m, mp, {0, c}))
        {
            mp[{0, c}] = u;
            return true;
        }
    }

    d = x - u.first;
    if (u.second >= d)
    {
        int c = u.second - d;
        if (DFS(x, y, target, m, mp, {x, c}))
        {
            mp[{x, c}] = u;
            return true;
        }
    }
    else
    {
        int c = u.first + u.second;
        if (DFS(x, y, target, m, mp, {c, 0}))
        {
            mp[{c, 0}] = u;
            return true;
        }
    }

    if (DFS(x, y, target, m, mp, {u.first, 0}))
    {
        mp[{u.first, 0}] = u;
        return true;
    }

    if (DFS(x, y, target, m, mp, {0, u.second}))
    {
        mp[{0, u.second}] = u;
        return true;
    }

    return false;
}

void DFSSolution(int x, int y, int target)
{
    map<p, int> m;
    bool isSolvable = false;
    map<p, p> mp;
    p start = {0, 0};
    if (DFS(x, y, target, m, mp, start))
        isSolvable = true;

    if (!isSolvable)
        cout << "Solution not possible";
}

int main()
{
    int Jug1 = 4, Jug2 = 3, target = 2;
    cout << "Path from initial state to solution state:\n";
    DFSSolution(Jug1, Jug2, target);
    return 0;
}