// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IntHashtable.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            IntHashtable

static class IntHashtable$Entry
{

    public int getKey()
    {
        return key;
    }

    public int getValue()
    {
        return value;
    }

    protected Object clone()
    {
        IntHashtable$Entry entry = new IntHashtable$Entry(hash, key, value, next == null ? null : (IntHashtable$Entry)next.clone());
        return entry;
    }

    int hash;
    int key;
    int value;
    IntHashtable$Entry next;

    protected IntHashtable$Entry(int hash, int key, int value, IntHashtable$Entry next)
    {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
