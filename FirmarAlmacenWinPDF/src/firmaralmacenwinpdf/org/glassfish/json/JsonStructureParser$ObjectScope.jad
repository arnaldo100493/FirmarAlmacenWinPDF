// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonStructureParser.java

package org.glassfish.json;

import java.util.*;
import javax.json.JsonObject;
import javax.json.JsonValue;

// Referenced classes of package org.glassfish.json:
//            JsonStructureParser

private static class JsonStructureParser$ObjectScope extends JsonStructureParser.Scope
{

    public boolean hasNext()
    {
        return it.hasNext();
    }

    public java.util.Map.Entry next()
    {
        java.util.Map.Entry next = (java.util.Map.Entry)it.next();
        key = (String)next.getKey();
        value = (JsonValue)next.getValue();
        return next;
    }

    public void remove()
    {
        throw new UnsupportedOperationException();
    }

    JsonValue getJsonValue()
    {
        return value;
    }

    public volatile Object next()
    {
        return next();
    }

    private final Iterator it;
    private JsonValue value;
    private String key;


    JsonStructureParser$ObjectScope(JsonObject object)
    {
        super(null);
        it = object.entrySet().iterator();
    }
}
