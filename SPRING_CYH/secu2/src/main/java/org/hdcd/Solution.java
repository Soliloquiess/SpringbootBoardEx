package org.hdcd;

class Solution {
    public boolean solution(String s){
        if(s==null){
            return false;
        }
        for (int i = 0; i< s.length();i++){
            char ch = s.charAt(i);
            if(('A'<=ch && ch<='Z')&& !('a'<=ch && ch<='z')){
                return false;
            }
        }
        return  true;
    }

    public static void main(String[] args) {
        Solution T = new Solution();
        boolean x= T.solution("a1234");
        System.out.println(x);
    }
}

