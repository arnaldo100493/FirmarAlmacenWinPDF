// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509CollectionStoreParameters.java

package co.org.bouncy.x509_;

import java.util.ArrayList;
import java.util.Collection;

// Referenced classes of package co.org.bouncy.x509_:
//            X509StoreParameters

public class X509CollectionStoreParameters
    implements X509StoreParameters
{

    public X509CollectionStoreParameters(Collection collection)
    {
        if(collection == null)
        {
            throw new NullPointerException("collection cannot be null");
        } else
        {
            this.collection = collection;
            return;
        }
    }

    public Object clone()
    {
        return new X509CollectionStoreParameters(collection);
    }

    public Collection getCollection()
    {
        return new ArrayList(collection);
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("X509CollectionStoreParameters: [\n");
        sb.append((new StringBuilder()).append("  collection: ").append(collection).append("\n").toString());
        sb.append("]");
        return sb.toString();
    }

    private Collection collection;
}
