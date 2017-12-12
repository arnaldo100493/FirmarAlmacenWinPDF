// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonStructureParser.java

package org.glassfish.json;

import java.util.Iterator;
import javax.json.*;

// Referenced classes of package org.glassfish.json:
//            JsonStructureParser

private static abstract class JsonStructureParser$Scope
    implements Iterator
{

    abstract JsonValue getJsonValue();

    static JsonStructureParser$Scope createScope(JsonValue value)
    {
        if(value instanceof JsonArray)
            return new cope((JsonArray)value);
        if(value instanceof JsonObject)
            return new Scope((JsonObject)value);
        else
            throw new JsonException((new StringBuilder()).append("Cannot be called for value=").append(value).toString());
    }

    private JsonStructureParser$Scope()
    {
    }

    JsonStructureParser$Scope(JsonStructureParser._cls1 x0)
    {
        this();
    }
}
