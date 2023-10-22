package CodeCaprice.String;

public class repeatSubStr_459 {
    public static void main(String[] args) {
        System.out.println(new repeatSubStr_459().repeatedSubstringPattern("abab"));
    }

    public boolean repeatedSubstringPattern(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int subLen = 1; subLen < len; subLen++) {
            if(len % subLen != 0) continue;
            boolean isRepeat = true;
            for (int subi = 1; subi < len / subLen; subi++) {
                if(!isRepeat) break;
                for (int idxi = 0; idxi < subLen; idxi++) {
                    if(chars[idxi]!=chars[subi*subLen+idxi]) {
                        isRepeat = false;
                        break;
                    }
                }
            }
            if (isRepeat) return true;
        }
        return false;
    }
}
