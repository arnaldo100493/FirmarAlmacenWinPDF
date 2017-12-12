// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMPSchemaRegistryImpl.java

package co.com.pdf.xmp.impl;

import co.com.pdf.xmp.*;
import co.com.pdf.xmp.options.AliasOptions;
import co.com.pdf.xmp.options.PropertyOptions;
import co.com.pdf.xmp.properties.XMPAliasInfo;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package co.com.pdf.xmp.impl:
//            ParameterAsserts, Utils, XMPNodeUtils

public final class XMPSchemaRegistryImpl
    implements XMPSchemaRegistry, XMPConst
{

    public XMPSchemaRegistryImpl()
    {
        namespaceToPrefixMap = new HashMap();
        prefixToNamespaceMap = new HashMap();
        aliasMap = new HashMap();
        p = Pattern.compile("[/*?\\[\\]]");
        try
        {
            registerStandardNamespaces();
            registerStandardAliases();
        }
        catch(XMPException e)
        {
            throw new RuntimeException("The XMPSchemaRegistry cannot be initialized!");
        }
    }

    public synchronized String registerNamespace(String namespaceURI, String suggestedPrefix)
        throws XMPException
    {
        ParameterAsserts.assertSchemaNS(namespaceURI);
        ParameterAsserts.assertPrefix(suggestedPrefix);
        if(suggestedPrefix.charAt(suggestedPrefix.length() - 1) != ':')
            suggestedPrefix = (new StringBuilder()).append(suggestedPrefix).append(':').toString();
        if(!Utils.isXMLNameNS(suggestedPrefix.substring(0, suggestedPrefix.length() - 1)))
            throw new XMPException("The prefix is a bad XML name", 201);
        String registeredPrefix = (String)namespaceToPrefixMap.get(namespaceURI);
        String registeredNS = (String)prefixToNamespaceMap.get(suggestedPrefix);
        if(registeredPrefix != null)
            return registeredPrefix;
        if(registeredNS != null)
        {
            String generatedPrefix = suggestedPrefix;
            for(int i = 1; prefixToNamespaceMap.containsKey(generatedPrefix); i++)
                generatedPrefix = (new StringBuilder()).append(suggestedPrefix.substring(0, suggestedPrefix.length() - 1)).append("_").append(i).append("_:").toString();

            suggestedPrefix = generatedPrefix;
        }
        prefixToNamespaceMap.put(suggestedPrefix, namespaceURI);
        namespaceToPrefixMap.put(namespaceURI, suggestedPrefix);
        return suggestedPrefix;
    }

    public synchronized void deleteNamespace(String namespaceURI)
    {
        String prefixToDelete = getNamespacePrefix(namespaceURI);
        if(prefixToDelete != null)
        {
            namespaceToPrefixMap.remove(namespaceURI);
            prefixToNamespaceMap.remove(prefixToDelete);
        }
    }

    public synchronized String getNamespacePrefix(String namespaceURI)
    {
        return (String)namespaceToPrefixMap.get(namespaceURI);
    }

    public synchronized String getNamespaceURI(String namespacePrefix)
    {
        if(namespacePrefix != null && !namespacePrefix.endsWith(":"))
            namespacePrefix = (new StringBuilder()).append(namespacePrefix).append(":").toString();
        return (String)prefixToNamespaceMap.get(namespacePrefix);
    }

    public synchronized Map getNamespaces()
    {
        return Collections.unmodifiableMap(new TreeMap(namespaceToPrefixMap));
    }

    public synchronized Map getPrefixes()
    {
        return Collections.unmodifiableMap(new TreeMap(prefixToNamespaceMap));
    }

    private void registerStandardNamespaces()
        throws XMPException
    {
        registerNamespace("http://www.w3.org/XML/1998/namespace", "xml");
        registerNamespace("http://www.w3.org/1999/02/22-rdf-syntax-ns#", "rdf");
        registerNamespace("http://purl.org/dc/elements/1.1/", "dc");
        registerNamespace("http://iptc.org/std/Iptc4xmpCore/1.0/xmlns/", "Iptc4xmpCore");
        registerNamespace("http://iptc.org/std/Iptc4xmpExt/2008-02-29/", "Iptc4xmpExt");
        registerNamespace("http://ns.adobe.com/DICOM/", "DICOM");
        registerNamespace("http://ns.useplus.org/ldf/xmp/1.0/", "plus");
        registerNamespace("adobe:ns:meta/", "x");
        registerNamespace("http://ns.adobe.com/iX/1.0/", "iX");
        registerNamespace("http://ns.adobe.com/xap/1.0/", "xmp");
        registerNamespace("http://ns.adobe.com/xap/1.0/rights/", "xmpRights");
        registerNamespace("http://ns.adobe.com/xap/1.0/mm/", "xmpMM");
        registerNamespace("http://ns.adobe.com/xap/1.0/bj/", "xmpBJ");
        registerNamespace("http://ns.adobe.com/xmp/note/", "xmpNote");
        registerNamespace("http://ns.adobe.com/pdf/1.3/", "pdf");
        registerNamespace("http://ns.adobe.com/pdfx/1.3/", "pdfx");
        registerNamespace("http://www.npes.org/pdfx/ns/id/", "pdfxid");
        registerNamespace("http://www.aiim.org/pdfa/ns/schema#", "pdfaSchema");
        registerNamespace("http://www.aiim.org/pdfa/ns/property#", "pdfaProperty");
        registerNamespace("http://www.aiim.org/pdfa/ns/type#", "pdfaType");
        registerNamespace("http://www.aiim.org/pdfa/ns/field#", "pdfaField");
        registerNamespace("http://www.aiim.org/pdfa/ns/id/", "pdfaid");
        registerNamespace("http://www.aiim.org/pdfa/ns/extension/", "pdfaExtension");
        registerNamespace("http://ns.adobe.com/photoshop/1.0/", "photoshop");
        registerNamespace("http://ns.adobe.com/album/1.0/", "album");
        registerNamespace("http://ns.adobe.com/exif/1.0/", "exif");
        registerNamespace("http://cipa.jp/exif/1.0/", "exifEX");
        registerNamespace("http://ns.adobe.com/exif/1.0/aux/", "aux");
        registerNamespace("http://ns.adobe.com/tiff/1.0/", "tiff");
        registerNamespace("http://ns.adobe.com/png/1.0/", "png");
        registerNamespace("http://ns.adobe.com/jpeg/1.0/", "jpeg");
        registerNamespace("http://ns.adobe.com/jp2k/1.0/", "jp2k");
        registerNamespace("http://ns.adobe.com/camera-raw-settings/1.0/", "crs");
        registerNamespace("http://ns.adobe.com/StockPhoto/1.0/", "bmsp");
        registerNamespace("http://ns.adobe.com/creatorAtom/1.0/", "creatorAtom");
        registerNamespace("http://ns.adobe.com/asf/1.0/", "asf");
        registerNamespace("http://ns.adobe.com/xmp/wav/1.0/", "wav");
        registerNamespace("http://ns.adobe.com/bwf/bext/1.0/", "bext");
        registerNamespace("http://ns.adobe.com/riff/info/", "riffinfo");
        registerNamespace("http://ns.adobe.com/xmp/1.0/Script/", "xmpScript");
        registerNamespace("http://ns.adobe.com/TransformXMP/", "txmp");
        registerNamespace("http://ns.adobe.com/swf/1.0/", "swf");
        registerNamespace("http://ns.adobe.com/xmp/1.0/DynamicMedia/", "xmpDM");
        registerNamespace("http://ns.adobe.com/xmp/transient/1.0/", "xmpx");
        registerNamespace("http://ns.adobe.com/xap/1.0/t/", "xmpT");
        registerNamespace("http://ns.adobe.com/xap/1.0/t/pg/", "xmpTPg");
        registerNamespace("http://ns.adobe.com/xap/1.0/g/", "xmpG");
        registerNamespace("http://ns.adobe.com/xap/1.0/g/img/", "xmpGImg");
        registerNamespace("http://ns.adobe.com/xap/1.0/sType/Font#", "stFnt");
        registerNamespace("http://ns.adobe.com/xap/1.0/sType/Dimensions#", "stDim");
        registerNamespace("http://ns.adobe.com/xap/1.0/sType/ResourceEvent#", "stEvt");
        registerNamespace("http://ns.adobe.com/xap/1.0/sType/ResourceRef#", "stRef");
        registerNamespace("http://ns.adobe.com/xap/1.0/sType/Version#", "stVer");
        registerNamespace("http://ns.adobe.com/xap/1.0/sType/Job#", "stJob");
        registerNamespace("http://ns.adobe.com/xap/1.0/sType/ManifestItem#", "stMfs");
        registerNamespace("http://ns.adobe.com/xmp/Identifier/qual/1.0/", "xmpidq");
    }

    public synchronized XMPAliasInfo resolveAlias(String aliasNS, String aliasProp)
    {
        String aliasPrefix = getNamespacePrefix(aliasNS);
        if(aliasPrefix == null)
            return null;
        else
            return (XMPAliasInfo)aliasMap.get((new StringBuilder()).append(aliasPrefix).append(aliasProp).toString());
    }

    public synchronized XMPAliasInfo findAlias(String qname)
    {
        return (XMPAliasInfo)aliasMap.get(qname);
    }

    public synchronized XMPAliasInfo[] findAliases(String aliasNS)
    {
        String prefix = getNamespacePrefix(aliasNS);
        List result = new ArrayList();
        if(prefix != null)
        {
            Iterator it = aliasMap.keySet().iterator();
            do
            {
                if(!it.hasNext())
                    break;
                String qname = (String)it.next();
                if(qname.startsWith(prefix))
                    result.add(findAlias(qname));
            } while(true);
        }
        return (XMPAliasInfo[])(XMPAliasInfo[])result.toArray(new XMPAliasInfo[result.size()]);
    }

    synchronized void registerAlias(String aliasNS, String aliasProp, final String actualNS, final String actualProp, AliasOptions aliasForm)
        throws XMPException
    {
        ParameterAsserts.assertSchemaNS(aliasNS);
        ParameterAsserts.assertPropName(aliasProp);
        ParameterAsserts.assertSchemaNS(actualNS);
        ParameterAsserts.assertPropName(actualProp);
        final AliasOptions aliasOpts = aliasForm == null ? new AliasOptions() : new AliasOptions(XMPNodeUtils.verifySetOptions(aliasForm.toPropertyOptions(), null).getOptions());
        if(p.matcher(aliasProp).find() || p.matcher(actualProp).find())
            throw new XMPException("Alias and actual property names must be simple", 102);
        String aliasPrefix = getNamespacePrefix(aliasNS);
        final String actualPrefix = getNamespacePrefix(actualNS);
        if(aliasPrefix == null)
            throw new XMPException("Alias namespace is not registered", 101);
        if(actualPrefix == null)
            throw new XMPException("Actual namespace is not registered", 101);
        String key = (new StringBuilder()).append(aliasPrefix).append(aliasProp).toString();
        if(aliasMap.containsKey(key))
            throw new XMPException("Alias is already existing", 4);
        if(aliasMap.containsKey((new StringBuilder()).append(actualPrefix).append(actualProp).toString()))
        {
            throw new XMPException("Actual property is already an alias, use the base property", 4);
        } else
        {
            XMPAliasInfo aliasInfo = new XMPAliasInfo() {

                public String getNamespace()
                {
                    return actualNS;
                }

                public String getPrefix()
                {
                    return actualPrefix;
                }

                public String getPropName()
                {
                    return actualProp;
                }

                public AliasOptions getAliasForm()
                {
                    return aliasOpts;
                }

                public String toString()
                {
                    return (new StringBuilder()).append(actualPrefix).append(actualProp).append(" NS(").append(actualNS).append("), FORM (").append(getAliasForm()).append(")").toString();
                }

                final String val$actualNS;
                final String val$actualPrefix;
                final String val$actualProp;
                final AliasOptions val$aliasOpts;
                final XMPSchemaRegistryImpl this$0;

            
            {
                this$0 = XMPSchemaRegistryImpl.this;
                actualNS = s;
                actualPrefix = s1;
                actualProp = s2;
                aliasOpts = aliasoptions;
                super();
            }
            }
;
            aliasMap.put(key, aliasInfo);
            return;
        }
    }

    public synchronized Map getAliases()
    {
        return Collections.unmodifiableMap(new TreeMap(aliasMap));
    }

    private void registerStandardAliases()
        throws XMPException
    {
        AliasOptions aliasToArrayOrdered = (new AliasOptions()).setArrayOrdered(true);
        AliasOptions aliasToArrayAltText = (new AliasOptions()).setArrayAltText(true);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Author", "http://purl.org/dc/elements/1.1/", "creator", aliasToArrayOrdered);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Authors", "http://purl.org/dc/elements/1.1/", "creator", null);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Description", "http://purl.org/dc/elements/1.1/", "description", null);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Format", "http://purl.org/dc/elements/1.1/", "format", null);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Keywords", "http://purl.org/dc/elements/1.1/", "subject", null);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Locale", "http://purl.org/dc/elements/1.1/", "language", null);
        registerAlias("http://ns.adobe.com/xap/1.0/", "Title", "http://purl.org/dc/elements/1.1/", "title", null);
        registerAlias("http://ns.adobe.com/xap/1.0/rights/", "Copyright", "http://purl.org/dc/elements/1.1/", "rights", null);
        registerAlias("http://ns.adobe.com/pdf/1.3/", "Author", "http://purl.org/dc/elements/1.1/", "creator", aliasToArrayOrdered);
        registerAlias("http://ns.adobe.com/pdf/1.3/", "BaseURL", "http://ns.adobe.com/xap/1.0/", "BaseURL", null);
        registerAlias("http://ns.adobe.com/pdf/1.3/", "CreationDate", "http://ns.adobe.com/xap/1.0/", "CreateDate", null);
        registerAlias("http://ns.adobe.com/pdf/1.3/", "Creator", "http://ns.adobe.com/xap/1.0/", "CreatorTool", null);
        registerAlias("http://ns.adobe.com/pdf/1.3/", "ModDate", "http://ns.adobe.com/xap/1.0/", "ModifyDate", null);
        registerAlias("http://ns.adobe.com/pdf/1.3/", "Subject", "http://purl.org/dc/elements/1.1/", "description", aliasToArrayAltText);
        registerAlias("http://ns.adobe.com/pdf/1.3/", "Title", "http://purl.org/dc/elements/1.1/", "title", aliasToArrayAltText);
        registerAlias("http://ns.adobe.com/photoshop/1.0/", "Author", "http://purl.org/dc/elements/1.1/", "creator", aliasToArrayOrdered);
        registerAlias("http://ns.adobe.com/photoshop/1.0/", "Caption", "http://purl.org/dc/elements/1.1/", "description", aliasToArrayAltText);
        registerAlias("http://ns.adobe.com/photoshop/1.0/", "Copyright", "http://purl.org/dc/elements/1.1/", "rights", aliasToArrayAltText);
        registerAlias("http://ns.adobe.com/photoshop/1.0/", "Keywords", "http://purl.org/dc/elements/1.1/", "subject", null);
        registerAlias("http://ns.adobe.com/photoshop/1.0/", "Marked", "http://ns.adobe.com/xap/1.0/rights/", "Marked", null);
        registerAlias("http://ns.adobe.com/photoshop/1.0/", "Title", "http://purl.org/dc/elements/1.1/", "title", aliasToArrayAltText);
        registerAlias("http://ns.adobe.com/photoshop/1.0/", "WebStatement", "http://ns.adobe.com/xap/1.0/rights/", "WebStatement", null);
        registerAlias("http://ns.adobe.com/tiff/1.0/", "Artist", "http://purl.org/dc/elements/1.1/", "creator", aliasToArrayOrdered);
        registerAlias("http://ns.adobe.com/tiff/1.0/", "Copyright", "http://purl.org/dc/elements/1.1/", "rights", null);
        registerAlias("http://ns.adobe.com/tiff/1.0/", "DateTime", "http://ns.adobe.com/xap/1.0/", "ModifyDate", null);
        registerAlias("http://ns.adobe.com/tiff/1.0/", "ImageDescription", "http://purl.org/dc/elements/1.1/", "description", null);
        registerAlias("http://ns.adobe.com/tiff/1.0/", "Software", "http://ns.adobe.com/xap/1.0/", "CreatorTool", null);
        registerAlias("http://ns.adobe.com/png/1.0/", "Author", "http://purl.org/dc/elements/1.1/", "creator", aliasToArrayOrdered);
        registerAlias("http://ns.adobe.com/png/1.0/", "Copyright", "http://purl.org/dc/elements/1.1/", "rights", aliasToArrayAltText);
        registerAlias("http://ns.adobe.com/png/1.0/", "CreationTime", "http://ns.adobe.com/xap/1.0/", "CreateDate", null);
        registerAlias("http://ns.adobe.com/png/1.0/", "Description", "http://purl.org/dc/elements/1.1/", "description", aliasToArrayAltText);
        registerAlias("http://ns.adobe.com/png/1.0/", "ModificationTime", "http://ns.adobe.com/xap/1.0/", "ModifyDate", null);
        registerAlias("http://ns.adobe.com/png/1.0/", "Software", "http://ns.adobe.com/xap/1.0/", "CreatorTool", null);
        registerAlias("http://ns.adobe.com/png/1.0/", "Title", "http://purl.org/dc/elements/1.1/", "title", aliasToArrayAltText);
    }

    private Map namespaceToPrefixMap;
    private Map prefixToNamespaceMap;
    private Map aliasMap;
    private Pattern p;
}
