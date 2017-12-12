// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LongHashtable.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.error_messages.MessageLocalization;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package co.com.pdf.text.pdf:
//            LongHashtable

static class LongHashtable$LongHashtableIterator
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

    public LongHashtable.Entry next()
    {
        if(entry == null)
            while(index-- > 0 && (entry = table[index]) == null) ;
        if(entry != null)
        {
            LongHashtable.Entry e = entry;
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
    LongHashtable.Entry table[];
    LongHashtable.Entry entry;

    LongHashtable$LongHashtableIterator(LongHashtable.Entry table[])
    {
        this.table = table;
        index = table.length;
    }
}
