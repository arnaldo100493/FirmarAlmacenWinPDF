// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMPIteratorImpl.java

package co.com.pdf.xmp.impl;

import co.com.pdf.xmp.XMPMetaFactory;
import co.com.pdf.xmp.XMPSchemaRegistry;
import co.com.pdf.xmp.options.PropertyOptions;
import co.com.pdf.xmp.properties.XMPPropertyInfo;

// Referenced classes of package co.com.pdf.xmp.impl:
//            QName, XMPNode, XMPIteratorImpl

class XMPIteratorImpl$NodeIterator$1
    implements XMPPropertyInfo
{

    public String getNamespace()
    {
        if(!val$node.getOptions().isSchemaNode())
        {
            QName qname = new QName(val$node.getName());
            return XMPMetaFactory.getSchemaRegistry().getNamespaceURI(qname.getPrefix());
        } else
        {
            return val$baseNS;
        }
    }

    public String getPath()
    {
        return val$path;
    }

    public String getValue()
    {
        return val$value;
    }

    public PropertyOptions getOptions()
    {
        return val$node.getOptions();
    }

    public String getLanguage()
    {
        return null;
    }

    final XMPNode val$node;
    final String val$baseNS;
    final String val$path;
    final String val$value;
    final XMPIteratorImpl.NodeIterator this$1;

    XMPIteratorImpl$NodeIterator$1()
    {
        this$1 = final_nodeiterator;
        val$node = xmpnode;
        val$baseNS = s;
        val$path = s1;
        val$value = String.this;
        super();
    }
}
