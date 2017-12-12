// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExtensionsGenerator.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            Extension, Extensions

public class ExtensionsGenerator
{

    public ExtensionsGenerator()
    {
        extensions = new Hashtable();
        extOrdering = new Vector();
    }

    public void reset()
    {
        extensions = new Hashtable();
        extOrdering = new Vector();
    }

    public void addExtension(ASN1ObjectIdentifier oid, boolean critical, ASN1Encodable value)
        throws IOException
    {
        addExtension(oid, critical, value.toASN1Primitive().getEncoded("DER"));
    }

    public void addExtension(ASN1ObjectIdentifier oid, boolean critical, byte value[])
    {
        if(extensions.containsKey(oid))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("extension ").append(oid).append(" already added").toString());
        } else
        {
            extOrdering.addElement(oid);
            extensions.put(oid, new Extension(oid, critical, new DEROctetString(value)));
            return;
        }
    }

    public boolean isEmpty()
    {
        return extOrdering.isEmpty();
    }

    public Extensions generate()
    {
        Extension exts[] = new Extension[extOrdering.size()];
        for(int i = 0; i != extOrdering.size(); i++)
            exts[i] = (Extension)extensions.get(extOrdering.elementAt(i));

        return new Extensions(exts);
    }

    private Hashtable extensions;
    private Vector extOrdering;
}
