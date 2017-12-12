// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMPSchemaRegistry.java

package co.com.pdf.xmp;

import co.com.pdf.xmp.properties.XMPAliasInfo;
import java.util.Map;

// Referenced classes of package co.com.pdf.xmp:
//            XMPException

public interface XMPSchemaRegistry
{

    public abstract String registerNamespace(String s, String s1)
        throws XMPException;

    public abstract String getNamespacePrefix(String s);

    public abstract String getNamespaceURI(String s);

    public abstract Map getNamespaces();

    public abstract Map getPrefixes();

    public abstract void deleteNamespace(String s);

    public abstract XMPAliasInfo resolveAlias(String s, String s1);

    public abstract XMPAliasInfo[] findAliases(String s);

    public abstract XMPAliasInfo findAlias(String s);

    public abstract Map getAliases();
}
