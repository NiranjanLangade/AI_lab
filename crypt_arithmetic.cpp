#include <iostream>
#include <unordered_map>
#include <vector>

using namespace std;

int getNumber(string s, unordered_map<char, int>& hm) {
    string num = "";
    for (int i = 0; i < s.length(); i++) {
        num += to_string(hm[s[i]]);
    }
    return stoi(num);
}

void solve(int i, string unique, unordered_map<char, int>& hm, vector<bool>& used, vector<string>& words, string result) {
    if (i == unique.length()) {
        int sum = 0;
        for (const string& s : words) {
            sum += getNumber(s, hm);
        }
        int r = getNumber(result, hm);
        if (sum == r) {
            for (int k = 0; k <= 25; k++) {
                char ch = static_cast<char>('A' + k);
                if (hm.find(ch) != hm.end()) {
                    cout << ch << " -> " << hm[ch] << " ";
                }
            }
            cout << endl;
        }
        return;
    }
    char ch = unique[i];
    for (int j = 0; j <= 9; j++) {
        if (!used[j]) {
            hm[ch] = j;
            used[j] = true;
            solve(i + 1, unique, hm, used, words, result);
            hm[ch] = -1;
            used[j] = false;
        }
    }
}

int main() {
    vector<string> words = {"SEND", "MORE"};
    string result = "MONEY";
    unordered_map<char, int> hm;
    string unique = "";

    for (const string& s : words) {
        for (char ch : s) {
            if (hm.find(ch) == hm.end()) {
                hm[ch] = -1;
                unique += ch;
            }
        }
    }

    for (char ch : result) {
        if (hm.find(ch) == hm.end()) {
            hm[ch] = -1;
            unique += ch;
        }
    }

    vector<bool> used(10, false);
    solve(0, unique, hm, used, words, result);

    return 0;
}
