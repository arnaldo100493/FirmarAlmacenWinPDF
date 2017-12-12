// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TernaryTree.java

package co.com.pdf.text.pdf.hyphenation;

import java.util.Enumeration;
import java.util.Stack;

// Referenced classes of package co.com.pdf.text.pdf.hyphenation:
//            TernaryTree, CharVector

public class TernaryTree$Iterator
    implements Enumeration
{
    private class Item
        implements Cloneable
    {

        public Item clone()
        {
            return new Item(parent, child);
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        char parent;
        char child;
        final TernaryTree.Iterator this$1;

        public Item()
        {
            this$1 = TernaryTree.Iterator.this;
            super();
            parent = '\0';
            child = '\0';
        }

        public Item(char p, char c)
        {
            this$1 = TernaryTree.Iterator.this;
            super();
            parent = p;
            child = c;
        }
    }


    public void rewind()
    {
        ns.removeAllElements();
        ks.setLength(0);
        cur = root;
        run();
    }

    public String nextElement()
    {
        String res = curkey;
        cur = up();
        run();
        return res;
    }

    public char getValue()
    {
        if(cur >= 0)
            return eq[cur];
        else
            return '\0';
    }

    public boolean hasMoreElements()
    {
        return cur != -1;
    }

    private int up()
    {
        Item i = new Item();
        int res = 0;
        if(ns.empty())
            return -1;
        if(cur != 0 && sc[cur] == 0)
            return lo[cur];
        do
        {
            for(boolean climb = true; climb;)
            {
                i = (Item)ns.pop();
                i.child++;
                switch(i.child)
                {
                case 1: // '\001'
                    if(sc[i.parent] != 0)
                    {
                        res = eq[i.parent];
                        ns.push(i.clone());
                        ks.append(sc[i.parent]);
                    } else
                    {
                        i.child++;
                        ns.push(i.clone());
                        res = hi[i.parent];
                    }
                    climb = false;
                    break;

                case 2: // '\002'
                    res = hi[i.parent];
                    ns.push(i.clone());
                    if(ks.length() > 0)
                        ks.setLength(ks.length() - 1);
                    climb = false;
                    break;

                default:
                    if(ns.empty())
                        return -1;
                    climb = true;
                    break;
                }
            }

            return res;
        } while(true);
    }

    private int run()
    {
        if(cur == -1)
            return -1;
        boolean leaf = false;
        do
        {
            do
            {
                if(cur == 0)
                    break;
                if(sc[cur] == '\uFFFF')
                {
                    leaf = true;
                    break;
                }
                ns.push(new Item((char)cur, '\0'));
                if(sc[cur] == 0)
                {
                    leaf = true;
                    break;
                }
                cur = lo[cur];
            } while(true);
            if(!leaf)
            {
                cur = up();
                if(cur == -1)
                    return -1;
            } else
            {
                StringBuffer buf = new StringBuffer(ks.toString());
                if(sc[cur] == '\uFFFF')
                {
                    for(int p = lo[cur]; kv.get(p) != 0; buf.append(kv.get(p++)));
                }
                curkey = buf.toString();
                return 0;
            }
        } while(true);
    }

    public volatile Object nextElement()
    {
        return nextElement();
    }

    int cur;
    String curkey;
    Stack ns;
    StringBuffer ks;
    final TernaryTree this$0;

    public TernaryTree$Iterator()
    {
        this$0 = TernaryTree.this;
        super();
        cur = -1;
        ns = new Stack();
        ks = new StringBuffer();
        rewind();
    }
}
