// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmpBasicSchema.java

package co.com.pdf.text.xml.xmp;


// Referenced classes of package co.com.pdf.text.xml.xmp:
//            XmpSchema, XmpArray

/**
 * @deprecated Class XmpBasicSchema is deprecated
 */

public class XmpBasicSchema extends XmpSchema
{

    public XmpBasicSchema()
    {
        super("xmlns:xmp=\"http://ns.adobe.com/xap/1.0/\"");
    }

    public void addCreatorTool(String creator)
    {
        setProperty("xmp:CreatorTool", creator);
    }

    public void addCreateDate(String date)
    {
        setProperty("xmp:CreateDate", date);
    }

    public void addModDate(String date)
    {
        setProperty("xmp:ModifyDate", date);
    }

    public void addMetaDataDate(String date)
    {
        setProperty("xmp:MetadataDate", date);
    }

    public void addIdentifiers(String id[])
    {
        XmpArray array = new XmpArray("rdf:Bag");
        for(int i = 0; i < id.length; i++)
            array.add(id[i]);

        setProperty("xmp:Identifier", array);
    }

    public void addNickname(String name)
    {
        setProperty("xmp:Nickname", name);
    }

    private static final long serialVersionUID = 0xde76766c8255e63eL;
    public static final String DEFAULT_XPATH_ID = "xmp";
    public static final String DEFAULT_XPATH_URI = "http://ns.adobe.com/xap/1.0/";
    public static final String ADVISORY = "xmp:Advisory";
    public static final String BASEURL = "xmp:BaseURL";
    public static final String CREATEDATE = "xmp:CreateDate";
    public static final String CREATORTOOL = "xmp:CreatorTool";
    public static final String IDENTIFIER = "xmp:Identifier";
    public static final String METADATADATE = "xmp:MetadataDate";
    public static final String MODIFYDATE = "xmp:ModifyDate";
    public static final String NICKNAME = "xmp:Nickname";
    public static final String THUMBNAILS = "xmp:Thumbnails";
}
