// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPUserAttributeSubpacketVectorGenerator.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.UserAttributeSubpacket;
import co.org.bouncy.bcpg.attr.ImageAttribute;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPUserAttributeSubpacketVector

public class PGPUserAttributeSubpacketVectorGenerator
{

    public PGPUserAttributeSubpacketVectorGenerator()
    {
        list = new ArrayList();
    }

    public void setImageAttribute(int imageType, byte imageData[])
    {
        if(imageData == null)
        {
            throw new IllegalArgumentException("attempt to set null image");
        } else
        {
            list.add(new ImageAttribute(imageType, imageData));
            return;
        }
    }

    public PGPUserAttributeSubpacketVector generate()
    {
        return new PGPUserAttributeSubpacketVector((UserAttributeSubpacket[])(UserAttributeSubpacket[])list.toArray(new UserAttributeSubpacket[list.size()]));
    }

    private List list;
}
