// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LongHashtable.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            LongHashtable

static class LongHashtable$Entry
{

    public long getKey()
    {
        return key;
    }

    public long getValue()
    {
        return value;
    }

    protected Object clone()
    {
        LongHashtable$Entry entry = new LongHashtable$Entry(hash, key, value, next == null ? null : (LongHashtable$Entry)next.clone());
        return entry;
    }

    int hash;
    long key;
    long value;
    LongHashtable$Entry next;

    protected LongHashtable$Entry(int hash, long key, long value, LongHashtable$Entry next)
    {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
