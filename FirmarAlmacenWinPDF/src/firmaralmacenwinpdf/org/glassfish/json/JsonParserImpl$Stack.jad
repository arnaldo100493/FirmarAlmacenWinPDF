// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonParserImpl.java

package org.glassfish.json;

import java.util.NoSuchElementException;

// Referenced classes of package org.glassfish.json:
//            JsonParserImpl

private static final class JsonParserImpl$Stack
{

    private void push(t context)
    {
        context.next = head;
        head = context;
    }

    private t pop()
    {
        if(head == null)
        {
            throw new NoSuchElementException();
        } else
        {
            t temp = head;
            head = head.next;
            return temp;
        }
    }

    private boolean isEmpty()
    {
        return head == null;
    }

    private t head;




    private JsonParserImpl$Stack()
    {
    }

    JsonParserImpl$Stack(JsonParserImpl._cls1 x0)
    {
        this();
    }
}
