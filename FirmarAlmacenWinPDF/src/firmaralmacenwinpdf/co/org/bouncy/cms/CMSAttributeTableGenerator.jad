// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSAttributeTableGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.cms.AttributeTable;
import java.util.Map;

// Referenced classes of package co.org.bouncy.cms:
//            CMSAttributeTableGenerationException

public interface CMSAttributeTableGenerator
{

    public abstract AttributeTable getAttributes(Map map)
        throws CMSAttributeTableGenerationException;

    public static final String CONTENT_TYPE = "contentType";
    public static final String DIGEST = "digest";
    public static final String SIGNATURE = "encryptedDigest";
    public static final String DIGEST_ALGORITHM_IDENTIFIER = "digestAlgID";
}
