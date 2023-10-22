package CodeCaprice.String;

public class strStr_28 {

    public int strStr(String haystack, String needle) {
        char[] strList = haystack.toCharArray(), pattList = needle.toCharArray();
        int strLen = strList.length, pattLen = pattList.length;
        int[] next = genNext(pattList, pattLen);
        for (int strIdx = 0, pattIdx = 0; strIdx < strLen; strIdx++) {
            while (pattIdx > 0 && pattList[strIdx] != strList[pattIdx])
                pattIdx = next[pattIdx - 1];
            if (pattList[strIdx] == strList[pattIdx]) pattIdx++;
            if (pattIdx == pattLen) return strIdx - pattIdx + 1;
        }
        return -1;
    }

    public int[] genNext(char[] regex, int length) {
        int[] next = new int[length];
        for (int prefix = 0, suffix = 1; suffix < length; suffix++) {
            while (prefix > 0 && regex[prefix] != regex[suffix])
                prefix = next[prefix - 1];
            if (regex[prefix] == regex[suffix]) prefix++;
            next[suffix] = prefix;
        }
        return next;
    }

    public static void main(String[] args) {
        strStr_28 solution = new strStr_28();
        String haystack = "aabaabaaf";
        String needle = "aabaaf";
        System.out.println(solution.strStr(haystack, needle));
    }

}