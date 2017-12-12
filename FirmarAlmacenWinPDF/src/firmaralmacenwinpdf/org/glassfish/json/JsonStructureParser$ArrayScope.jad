// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonStructureParser.java

package org.glassfish.json;

import java.util.Iterator;
import javax.json.JsonArray;
import javax.json.JsonValue;

// Referenced classes of package org.glassfish.json:
//            JsonStructureParser

private static class JsonStructureParser$ArrayScope extends JsonStructureParser.Scope
{

    public boolean hasNext()
    {
        return it.hasNext();
    }

    public JsonValue next()
    {
        value = (JsonValue)it.next();
        return value;
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

    JsonStructureParser$ArrayScope(JsonArray array)
    {
        super(null);
        it = array.iterator();
    }
}
