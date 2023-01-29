package com.github.saiyan.cache.core.support.proxy.bs;

public class Exer {
    public static int f(char[] str,int index,char pre) {

        if (index == str.length) {
            return 0;
        }
        int ans = 0;
        if (str[index] != pre) {
            ans +=f(str,index+1,str[index]);
        } else {
            if (index == 1) {
                int p1 = f(str,index+1,str[index])+1;
                int p2 = f(str,index+1,pre)+1;
                int p = Math.min(p1,p2);
                ans += p;
            } else {
                if (str[index] == 'A') {
                    str[index] = 'B';
                } else {
                    str[index] = 'A';
                }
                int p1 = f(str,index+1,str[index])+1;
                ans += p1;
            }

        }
        return ans;

    }

    public static void main(String[] args) {
        String s = "ABBA";
        char[] str = s.toCharArray();
        System.out.println("f(str,1,str[0]) = " + f(str, 1, str[0]));
    }
}
