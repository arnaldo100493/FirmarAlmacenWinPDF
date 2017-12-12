// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaCRLStore.java

package co.org.bouncy.cert.jcajce;

import co.org.bouncy.cert.X509CRLHolder;
import co.org.bouncy.util.CollectionStore;
import java.io.IOException;
import java.security.cert.CRLException;
import java.security.cert.X509CRL;
import java.util.*;

public class JcaCRLStore extends CollectionStore
{

    public JcaCRLStore(Collection collection)
        throws CRLException
    {
        super(convertCRLs(collection));
    }

    private static Collection convertCRLs(Collection collection)
        throws CRLException
    {
        List list = new ArrayList(collection.size());
        for(Iterator it = collection.iterator(); it.hasNext();)
        {
            Object crl = it.next();
            if(crl instanceof X509CRL)
                try
                {
                    list.add(new X509CRLHolder(((X509CRL)crl).getEncoded()));
                }
                catch(IOException e)
                {
                    throw new CRLException((new StringBuilder()).append("cannot read encoding: ").append(e.getMessage()).toString());
                }
            else
                list.add((X509CRLHolder)crl);
        }

        return list;
    }
}
