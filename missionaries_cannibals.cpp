#include <bits/stdc++.h>
using namespace std;


bool isgoal(vector<int>a){
    if (a[2]==3 && a[3]==3){
        return true;
    }
    return false;
}
bool notvalid(vector<int>a){
    if ((a[1]>a[0] && a[0]!=0)|| ((a[3]>a[2]) && a[2]!=0)){
        return false;
    }
    return true;

}




int main(){
    vector<int>a={3,3,0,0,0};
    int ch=0;
    queue<pair<vector<int>,vector<pair<char,char>>>>q;
    vector<pair<char,char>>ans;
    q.push({a,ans});
    while(!q.empty()){
        vector<int>curr=q.front().first;
        vector<pair<char,char>>ans2=q.front().second;
        q.pop();
        if (!notvalid(curr)){
            continue;

        }

        if (isgoal(curr)){
            for (auto c:ans2){
                cout << c.first << " " << c.second << endl;
            } 
            break;
        }
        for (int i=0;i<=2;i++){
            for (int j=0;j<=2;j++){
                if (i+j<=2 && i+j>0){
                    vector<int>b=curr;
                    if (curr[4]==0 && b[0]>=i && b[1]>=j){

                        b[0]-=i;
                        b[1]-=j;
                        b[2]+=i;
                        b[3]+=j;
                        pair<char,char>boat={'#','#'};
                        if (i==2){
                            boat.first='M';
                            boat.second='M';
                        }
                        if (j==2){
                            boat.first='C';
                            boat.second='C';

                        }
                        if (i==1){
                            boat.first='M';
                        }
                        if (j==1){
                            boat.second='C';
                        }
                        b[4]=1-b[4];
                        ans2.push_back(boat);
                        // cout << boat.first << " " << boat.second << endl;
                        q.push({b,ans2});
                        ans2.pop_back();



                    }
                    else if (curr[4]==1 && b[2]>=i && b[3]>=j){
                        b[0]+=i;
                        b[1]+=j;
                        b[2]-=i;
                        b[3]-=j;
                        pair<char,char>boat={'#','#'};
                        if (i==2){
                            boat.first='M';
                            boat.second='M';
                        }
                        if (j==2){
                            boat.first='C';
                            boat.second='C';

                        }
                        if (i==1){
                            boat.first='M';
                        }
                        if (j==1){
                            boat.second='C';
                        }
                        b[4]=1-b[4];
                        ans2.push_back(boat);
                        // cout << boat.first << " " << boat.second << endl;

                        q.push({b,ans2});
                        ans2.pop_back();


                    }

                }
            }
        }
        // break;

    }
  return 0;
}