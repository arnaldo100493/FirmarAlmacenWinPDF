// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneralNamesBuilder.java

package co.org.bouncy.asn1.x509;

import java.util.Vector;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            GeneralName, GeneralNames

public class GeneralNamesBuilder
{

    public GeneralNamesBuilder()
    {
        names = new Vector();
    }

    public GeneralNamesBuilder addNames(GeneralNames names)
    {
        GeneralName n[] = names.getNames();
        for(int i = 0; i != n.length; i++)
            this.names.addElement(n[i]);

        return this;
    }

    public GeneralNamesBuilder addName(GeneralName name)
    {
        names.addElement(name);
        return this;
    }

    public GeneralNames build()
    {
        GeneralName tmp[] = new GeneralName[names.size()];
        for(int i = 0; i != tmp.length; i++)
            tmp[i] = (GeneralName)names.elementAt(i);

        return new GeneralNames(tmp);
    }

    private Vector names;
}
