// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultAuthenticatedAttributeTableGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import java.util.Hashtable;
import java.util.Map;

// Referenced classes of package co.org.bouncy.cms:
//            CMSAttributeTableGenerator

public class DefaultAuthenticatedAttributeTableGenerator
    implements CMSAttributeTableGenerator
{

    public DefaultAuthenticatedAttributeTableGenerator()
    {
        table = new Hashtable();
    }

    public DefaultAuthenticatedAttributeTableGenerator(AttributeTable attributeTable)
    {
        if(attributeTable != null)
            table = attributeTable.toHashtable();
        else
            table = new Hashtable();
    }

    protected Hashtable createStandardAttributeTable(Map parameters)
    {
        Hashtable std = (Hashtable)table.clone();
        if(!std.containsKey(CMSAttributes.contentType))
        {
            ASN1ObjectIdentifier contentType = ASN1ObjectIdentifier.getInstance(parameters.get("contentType"));
            Attribute attr = new Attribute(CMSAttributes.contentType, new DERSet(contentType));
            std.put(attr.getAttrType(), attr);
        }
        if(!std.containsKey(CMSAttributes.messageDigest))
        {
            byte messageDigest[] = (byte[])(byte[])parameters.get("digest");
            Attribute attr = new Attribute(CMSAttributes.messageDigest, new DERSet(new DEROctetString(messageDigest)));
            std.put(attr.getAttrType(), attr);
        }
        return std;
    }

    public AttributeTable getAttributes(Map parameters)
    {
        return new AttributeTable(createStandardAttributeTable(parameters));
    }

    private final Hashtable table;
}
