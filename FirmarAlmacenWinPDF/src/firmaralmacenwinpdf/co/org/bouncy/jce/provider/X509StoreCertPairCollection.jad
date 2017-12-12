// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509StoreCertPairCollection.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.util.CollectionStore;
import co.org.bouncy.util.Selector;
import co.org.bouncy.x509_.*;
import java.util.Collection;

public class X509StoreCertPairCollection extends X509StoreSpi
{

    public X509StoreCertPairCollection()
    {
    }

    public void engineInit(X509StoreParameters params)
    {
        if(!(params instanceof X509CollectionStoreParameters))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Initialization parameters must be an instance of ").append(co/org/bouncy/x509_/X509CollectionStoreParameters.getName()).append(".").toString());
        } else
        {
            _store = new CollectionStore(((X509CollectionStoreParameters)params).getCollection());
            return;
        }
    }

    public Collection engineGetMatches(Selector selector)
    {
        return _store.getMatches(selector);
    }

    private CollectionStore _store;
}
