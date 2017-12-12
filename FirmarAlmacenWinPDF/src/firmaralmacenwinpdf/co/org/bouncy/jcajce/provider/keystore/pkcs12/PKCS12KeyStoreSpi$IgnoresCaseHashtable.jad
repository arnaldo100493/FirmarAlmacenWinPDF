// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS12KeyStoreSpi.java

package co.org.bouncy.jcajce.provider.keystore.pkcs12;

import co.org.bouncy.util.Strings;
import java.util.Enumeration;
import java.util.Hashtable;

// Referenced classes of package co.org.bouncy.jcajce.provider.keystore.pkcs12:
//            PKCS12KeyStoreSpi

private static class PKCS12KeyStoreSpi$IgnoresCaseHashtable
{

    public void put(String key, Object value)
    {
        String lower = key != null ? Strings.toLowerCase(key) : null;
        String k = (String)keys.get(lower);
        if(k != null)
            orig.remove(k);
        keys.put(lower, key);
        orig.put(key, value);
    }

    public Enumeration keys()
    {
        return orig.keys();
    }

    public Object remove(String alias)
    {
        String k = (String)keys.remove(alias != null ? ((Object) (Strings.toLowerCase(alias))) : null);
        if(k == null)
            return null;
        else
            return orig.remove(k);
    }

    public Object get(String alias)
    {
        String k = (String)keys.get(alias != null ? ((Object) (Strings.toLowerCase(alias))) : null);
        if(k == null)
            return null;
        else
            return orig.get(k);
    }

    public Enumeration elements()
    {
        return orig.elements();
    }

    private Hashtable orig;
    private Hashtable keys;

    private PKCS12KeyStoreSpi$IgnoresCaseHashtable()
    {
        orig = new Hashtable();
        keys = new Hashtable();
    }

    PKCS12KeyStoreSpi$IgnoresCaseHashtable(PKCS12KeyStoreSpi._cls1 x0)
    {
        this();
    }
}
