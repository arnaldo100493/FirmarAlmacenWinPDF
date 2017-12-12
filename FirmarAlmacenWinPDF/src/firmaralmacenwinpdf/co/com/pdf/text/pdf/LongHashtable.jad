// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LongHashtable.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.error_messages.MessageLocalization;
import java.util.*;

public class LongHashtable
    implements Cloneable
{
    static class LongHashtableIterator
        implements Iterator
    {

        public boolean hasNext()
        {
            if(entry != null)
                return true;
            while(index-- > 0) 
                if((entry = table[index]) != null)
                    return true;
            return false;
        }

        public Entry next()
        {
            if(entry == null)
                while(index-- > 0 && (entry = table[index]) == null) ;
            if(entry != null)
            {
                Entry e = entry;
                entry = e.next;
                return e;
            } else
            {
                throw new NoSuchElementException(MessageLocalization.getComposedMessage("inthashtableiterator", new Object[0]));
            }
        }

        public void remove()
        {
            throw new UnsupportedOperationException(MessageLocalization.getComposedMessage("remove.not.supported", new Object[0]));
        }

        public volatile Object next()
        {
            return next();
        }

        int index;
        Entry table[];
        Entry entry;

        LongHashtableIterator(Entry table[])
        {
            this.table = table;
            index = table.length;
        }
    }

    static class Entry
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
            Entry entry = new Entry(hash, key, value, next == null ? null : (Entry)next.clone());
            return entry;
        }

        int hash;
        long key;
        long value;
        Entry next;

        protected Entry(int hash, long key, long value, Entry next)
        {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


    public LongHashtable()
    {
        this(150, 0.75F);
    }

    public LongHashtable(int initialCapacity)
    {
        this(initialCapacity, 0.75F);
    }

    public LongHashtable(int initialCapacity, float loadFactor)
    {
        if(initialCapacity < 0)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("illegal.capacity.1", initialCapacity));
        if(loadFactor <= 0.0F)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("illegal.load.1", new Object[] {
                String.valueOf(loadFactor)
            }));
        if(initialCapacity == 0)
            initialCapacity = 1;
        this.loadFactor = loadFactor;
        table = new Entry[initialCapacity];
        threshold = (int)((float)initialCapacity * loadFactor);
    }

    public int size()
    {
        return count;
    }

    public boolean isEmpty()
    {
        return count == 0;
    }

    public boolean contains(long value)
    {
        Entry tab[] = table;
        for(int i = tab.length; i-- > 0;)
        {
            Entry e = tab[i];
            while(e != null) 
            {
                if(e.value == value)
                    return true;
                e = e.next;
            }
        }

        return false;
    }

    public boolean containsValue(long value)
    {
        return contains(value);
    }

    public boolean containsKey(long key)
    {
        Entry tab[] = table;
        int hash = (int)(key ^ key >>> 32);
        int index = (hash & 0x7fffffff) % tab.length;
        for(Entry e = tab[index]; e != null; e = e.next)
            if(e.hash == hash && e.key == key)
                return true;

        return false;
    }

    public long get(long key)
    {
        Entry tab[] = table;
        int hash = (int)(key ^ key >>> 32);
        int index = (hash & 0x7fffffff) % tab.length;
        for(Entry e = tab[index]; e != null; e = e.next)
            if(e.hash == hash && e.key == key)
                return e.value;

        return 0L;
    }

    protected void rehash()
    {
        int oldCapacity = table.length;
        Entry oldMap[] = table;
        int newCapacity = oldCapacity * 2 + 1;
        Entry newMap[] = new Entry[newCapacity];
        threshold = (int)((float)newCapacity * loadFactor);
        table = newMap;
        for(int i = oldCapacity; i-- > 0;)
        {
            Entry old = oldMap[i];
            while(old != null) 
            {
                Entry e = old;
                old = old.next;
                int index = (e.hash & 0x7fffffff) % newCapacity;
                e.next = newMap[index];
                newMap[index] = e;
            }
        }

    }

    public long put(long key, long value)
    {
        Entry tab[] = table;
        int hash = (int)(key ^ key >>> 32);
        int index = (hash & 0x7fffffff) % tab.length;
        Entry e;
        for(e = tab[index]; e != null; e = e.next)
            if(e.hash == hash && e.key == key)
            {
                long old = e.value;
                e.value = value;
                return old;
            }

        if(count >= threshold)
        {
            rehash();
            tab = table;
            index = (hash & 0x7fffffff) % tab.length;
        }
        e = new Entry(hash, key, value, tab[index]);
        tab[index] = e;
        count++;
        return 0L;
    }

    public long remove(long key)
    {
        Entry tab[] = table;
        int hash = (int)(key ^ key >>> 32);
        int index = (hash & 0x7fffffff) % tab.length;
        Entry e = tab[index];
        Entry prev = null;
        for(; e != null; e = e.next)
        {
            if(e.hash == hash && e.key == key)
            {
                if(prev != null)
                    prev.next = e.next;
                else
                    tab[index] = e.next;
                count--;
                long oldValue = e.value;
                e.value = 0L;
                return oldValue;
            }
            prev = e;
        }

        return 0L;
    }

    public void clear()
    {
        Entry tab[] = table;
        for(int index = tab.length; --index >= 0;)
            tab[index] = null;

        count = 0;
    }

    public Iterator getEntryIterator()
    {
        return new LongHashtableIterator(table);
    }

    public long[] toOrderedKeys()
    {
        long res[] = getKeys();
        Arrays.sort(res);
        return res;
    }

    public long[] getKeys()
    {
        long res[] = new long[count];
        int ptr = 0;
        int index = table.length;
        Entry entry = null;
        do
        {
            if(entry == null)
                while(index-- > 0 && (entry = table[index]) == null) ;
            if(entry != null)
            {
                Entry e = entry;
                entry = e.next;
                res[ptr++] = e.key;
            } else
            {
                return res;
            }
        } while(true);
    }

    public long getOneKey()
    {
        if(count == 0)
            return 0L;
        int index = table.length;
        Entry entry;
        for(entry = null; index-- > 0 && (entry = table[index]) == null;);
        if(entry == null)
            return 0L;
        else
            return entry.key;
    }

    public Object clone()
    {
        try
        {
            LongHashtable t = (LongHashtable)super.clone();
            t.table = new Entry[table.length];
            for(int i = table.length; i-- > 0;)
                t.table[i] = table[i] == null ? null : (Entry)table[i].clone();

            return t;
        }
        catch(CloneNotSupportedException e)
        {
            throw new InternalError();
        }
    }

    private transient Entry table[];
    private transient int count;
    private int threshold;
    private float loadFactor;
}
