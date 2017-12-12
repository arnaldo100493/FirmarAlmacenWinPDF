// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HyphenationTree.java

package co.com.pdf.text.pdf.hyphenation;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text.pdf.hyphenation:
//            TernaryTree, ByteVector, SimplePatternParser, Hyphenation, 
//            PatternConsumer, CharVector

public class HyphenationTree extends TernaryTree
    implements PatternConsumer
{

    public HyphenationTree()
    {
        stoplist = new HashMap(23);
        classmap = new TernaryTree();
        vspace = new ByteVector();
        vspace.alloc(1);
    }

    protected int packValues(String values)
    {
        int n = values.length();
        int m = (n & 1) != 1 ? (n >> 1) + 1 : (n >> 1) + 2;
        int offset = vspace.alloc(m);
        byte va[] = vspace.getArray();
        for(int i = 0; i < n; i++)
        {
            int j = i >> 1;
            byte v = (byte)((values.charAt(i) - 48) + 1 & 0xf);
            if((i & 1) == 1)
                va[j + offset] = (byte)(va[j + offset] | v);
            else
                va[j + offset] = (byte)(v << 4);
        }

        va[(m - 1) + offset] = 0;
        return offset;
    }

    protected String unpackValues(int k)
    {
        StringBuffer buf = new StringBuffer();
        byte v = vspace.get(k++);
        do
        {
            if(v == 0)
                break;
            char c = (char)(((v >>> 4) - 1) + 48);
            buf.append(c);
            c = (char)(v & 0xf);
            if(c == 0)
                break;
            c = (char)((c - 1) + 48);
            buf.append(c);
            v = vspace.get(k++);
        } while(true);
        return buf.toString();
    }

    public void loadSimplePatterns(InputStream stream)
    {
        SimplePatternParser pp = new SimplePatternParser();
        ivalues = new TernaryTree();
        pp.parse(stream, this);
        trimToSize();
        vspace.trimToSize();
        classmap.trimToSize();
        ivalues = null;
    }

    public String findPattern(String pat)
    {
        int k = super.find(pat);
        if(k >= 0)
            return unpackValues(k);
        else
            return "";
    }

    protected int hstrcmp(char s[], int si, char t[], int ti)
    {
        for(; s[si] == t[ti]; ti++)
        {
            if(s[si] == 0)
                return 0;
            si++;
        }

        if(t[ti] == 0)
            return 0;
        else
            return s[si] - t[ti];
    }

    protected byte[] getValues(int k)
    {
        StringBuffer buf = new StringBuffer();
        byte v = vspace.get(k++);
        do
        {
            if(v == 0)
                break;
            char c = (char)((v >>> 4) - 1);
            buf.append(c);
            c = (char)(v & 0xf);
            if(c == 0)
                break;
            c--;
            buf.append(c);
            v = vspace.get(k++);
        } while(true);
        byte res[] = new byte[buf.length()];
        for(int i = 0; i < res.length; i++)
            res[i] = (byte)buf.charAt(i);

        return res;
    }

    protected void searchPatterns(char word[], int index, byte il[])
    {
        int i = index;
        char sp = word[i];
        char p = root;
label0:
        do
        {
            if(p <= 0 || p >= sc.length)
                break;
            if(sc[p] == '\uFFFF')
            {
                if(hstrcmp(word, i, kv.getArray(), lo[p]) == 0)
                {
                    byte values[] = getValues(eq[p]);
                    int j = index;
                    byte arr$[] = values;
                    int len$ = arr$.length;
                    for(int i$ = 0; i$ < len$; i$++)
                    {
                        byte value = arr$[i$];
                        if(j < il.length && value > il[j])
                            il[j] = value;
                        j++;
                    }

                }
                return;
            }
            int d = sp - sc[p];
            if(d == 0)
            {
                if(sp == 0)
                    break;
                sp = word[++i];
                p = eq[p];
                char q = p;
                do
                {
                    if(q <= 0 || q >= sc.length || sc[q] == '\uFFFF')
                        continue label0;
                    if(sc[q] == 0)
                    {
                        byte values[] = getValues(eq[q]);
                        int j = index;
                        byte arr$[] = values;
                        int len$ = arr$.length;
                        int i$ = 0;
                        while(i$ < len$) 
                        {
                            byte value = arr$[i$];
                            if(j < il.length && value > il[j])
                                il[j] = value;
                            j++;
                            i$++;
                        }
                        continue label0;
                    }
                    q = lo[q];
                } while(true);
            }
            p = d >= 0 ? hi[p] : lo[p];
        } while(true);
    }

    public Hyphenation hyphenate(String word, int remainCharCount, int pushCharCount)
    {
        char w[] = word.toCharArray();
        return hyphenate(w, 0, w.length, remainCharCount, pushCharCount);
    }

    public Hyphenation hyphenate(char w[], int offset, int len, int remainCharCount, int pushCharCount)
    {
        char word[] = new char[len + 3];
        char c[] = new char[2];
        int iIgnoreAtBeginning = 0;
        int iLength = len;
        boolean bEndOfLetters = false;
        for(int i = 1; i <= len; i++)
        {
            c[0] = w[(offset + i) - 1];
            int nc = classmap.find(c, 0);
            if(nc < 0)
            {
                if(i == 1 + iIgnoreAtBeginning)
                    iIgnoreAtBeginning++;
                else
                    bEndOfLetters = true;
                iLength--;
                continue;
            }
            if(!bEndOfLetters)
                word[i - iIgnoreAtBeginning] = (char)nc;
            else
                return null;
        }

        int origlen = len;
        len = iLength;
        if(len < remainCharCount + pushCharCount)
            return null;
        int result[] = new int[len + 1];
        int k = 0;
        String sw = new String(word, 1, len);
        if(stoplist.containsKey(sw))
        {
            ArrayList hw = (ArrayList)stoplist.get(sw);
            int j = 0;
            for(int i = 0; i < hw.size(); i++)
            {
                Object o = hw.get(i);
                if(o instanceof String)
                {
                    j += ((String)o).length();
                    if(j >= remainCharCount && j < len - pushCharCount)
                        result[k++] = j + iIgnoreAtBeginning;
                }
            }

        } else
        {
            word[0] = '.';
            word[len + 1] = '.';
            word[len + 2] = '\0';
            byte il[] = new byte[len + 3];
            for(int i = 0; i < len + 1; i++)
                searchPatterns(word, i, il);

            for(int i = 0; i < len; i++)
                if((il[i + 1] & 1) == 1 && i >= remainCharCount && i <= len - pushCharCount)
                    result[k++] = i + iIgnoreAtBeginning;

        }
        if(k > 0)
        {
            int res[] = new int[k];
            System.arraycopy(result, 0, res, 0, k);
            return new Hyphenation(new String(w, offset, origlen), res);
        } else
        {
            return null;
        }
    }

    public void addClass(String chargroup)
    {
        if(chargroup.length() > 0)
        {
            char equivChar = chargroup.charAt(0);
            char key[] = new char[2];
            key[1] = '\0';
            for(int i = 0; i < chargroup.length(); i++)
            {
                key[0] = chargroup.charAt(i);
                classmap.insert(key, 0, equivChar);
            }

        }
    }

    public void addException(String word, ArrayList hyphenatedword)
    {
        stoplist.put(word, hyphenatedword);
    }

    public void addPattern(String pattern, String ivalue)
    {
        int k = ivalues.find(ivalue);
        if(k <= 0)
        {
            k = packValues(ivalue);
            ivalues.insert(ivalue, (char)k);
        }
        insert(pattern, (char)k);
    }

    public void printStats()
    {
        System.out.println((new StringBuilder()).append("Value space size = ").append(Integer.toString(vspace.length())).toString());
        super.printStats();
    }

    private static final long serialVersionUID = 0x944361669cea8548L;
    protected ByteVector vspace;
    protected HashMap stoplist;
    protected TernaryTree classmap;
    private transient TernaryTree ivalues;
}
