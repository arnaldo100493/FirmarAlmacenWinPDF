// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMPNormalizer.java

package co.com.pdf.xmp.impl;

import co.com.pdf.xmp.*;
import co.com.pdf.xmp.impl.xpath.XMPPath;
import co.com.pdf.xmp.impl.xpath.XMPPathParser;
import co.com.pdf.xmp.options.*;
import co.com.pdf.xmp.properties.XMPAliasInfo;
import java.util.*;

// Referenced classes of package co.com.pdf.xmp.impl:
//            XMPNode, XMPDateTimeImpl, XMPMetaImpl, Utils, 
//            XMPNodeUtils

public class XMPNormalizer
{

    private XMPNormalizer()
    {
    }

    static XMPMeta process(XMPMetaImpl xmp, ParseOptions options)
        throws XMPException
    {
        XMPNode tree = xmp.getRoot();
        touchUpDataModel(xmp);
        moveExplicitAliases(tree, options);
        tweakOldXMP(tree);
        deleteEmptySchemas(tree);
        return xmp;
    }

    private static void tweakOldXMP(XMPNode tree)
        throws XMPException
    {
        if(tree.getName() != null && tree.getName().length() >= 36)
        {
            String nameStr = tree.getName().toLowerCase();
            if(nameStr.startsWith("uuid:"))
                nameStr = nameStr.substring(5);
            if(Utils.checkUUIDFormat(nameStr))
            {
                XMPPath path = XMPPathParser.expandXPath("http://ns.adobe.com/xap/1.0/mm/", "InstanceID");
                XMPNode idNode = XMPNodeUtils.findNode(tree, path, true, null);
                if(idNode != null)
                {
                    idNode.setOptions(null);
                    idNode.setValue((new StringBuilder()).append("uuid:").append(nameStr).toString());
                    idNode.removeChildren();
                    idNode.removeQualifiers();
                    tree.setName(null);
                } else
                {
                    throw new XMPException("Failure creating xmpMM:InstanceID", 9);
                }
            }
        }
    }

    private static void touchUpDataModel(XMPMetaImpl xmp)
        throws XMPException
    {
        XMPNodeUtils.findSchemaNode(xmp.getRoot(), "http://purl.org/dc/elements/1.1/", true);
        Iterator it = xmp.getRoot().iterateChildren();
        do
        {
            if(!it.hasNext())
                break;
            XMPNode currSchema = (XMPNode)it.next();
            if("http://purl.org/dc/elements/1.1/".equals(currSchema.getName()))
                normalizeDCArrays(currSchema);
            else
            if("http://ns.adobe.com/exif/1.0/".equals(currSchema.getName()))
            {
                fixGPSTimeStamp(currSchema);
                XMPNode arrayNode = XMPNodeUtils.findChildNode(currSchema, "exif:UserComment", false);
                if(arrayNode != null)
                    repairAltText(arrayNode);
            } else
            if("http://ns.adobe.com/xmp/1.0/DynamicMedia/".equals(currSchema.getName()))
            {
                XMPNode dmCopyright = XMPNodeUtils.findChildNode(currSchema, "xmpDM:copyright", false);
                if(dmCopyright != null)
                    migrateAudioCopyright(xmp, dmCopyright);
            } else
            if("http://ns.adobe.com/xap/1.0/rights/".equals(currSchema.getName()))
            {
                XMPNode arrayNode = XMPNodeUtils.findChildNode(currSchema, "xmpRights:UsageTerms", false);
                if(arrayNode != null)
                    repairAltText(arrayNode);
            }
        } while(true);
    }

    private static void normalizeDCArrays(XMPNode dcSchema)
        throws XMPException
    {
        for(int i = 1; i <= dcSchema.getChildrenLength(); i++)
        {
            XMPNode currProp = dcSchema.getChild(i);
            PropertyOptions arrayForm = (PropertyOptions)dcArrayForms.get(currProp.getName());
            if(arrayForm == null)
                continue;
            if(currProp.getOptions().isSimple())
            {
                XMPNode newArray = new XMPNode(currProp.getName(), arrayForm);
                currProp.setName("[]");
                newArray.addChild(currProp);
                dcSchema.replaceChild(i, newArray);
                if(arrayForm.isArrayAltText() && !currProp.getOptions().getHasLanguage())
                {
                    XMPNode newLang = new XMPNode("xml:lang", "x-default", null);
                    currProp.addQualifier(newLang);
                }
                continue;
            }
            currProp.getOptions().setOption(7680, false);
            currProp.getOptions().mergeWith(arrayForm);
            if(arrayForm.isArrayAltText())
                repairAltText(currProp);
        }

    }

    private static void repairAltText(XMPNode arrayNode)
        throws XMPException
    {
        if(arrayNode == null || !arrayNode.getOptions().isArray())
            return;
        arrayNode.getOptions().setArrayOrdered(true).setArrayAlternate(true).setArrayAltText(true);
        Iterator it = arrayNode.iterateChildren();
        do
        {
            if(!it.hasNext())
                break;
            XMPNode currChild = (XMPNode)it.next();
            if(currChild.getOptions().isCompositeProperty())
                it.remove();
            else
            if(!currChild.getOptions().getHasLanguage())
            {
                String childValue = currChild.getValue();
                if(childValue == null || childValue.length() == 0)
                {
                    it.remove();
                } else
                {
                    XMPNode repairLang = new XMPNode("xml:lang", "x-repair", null);
                    currChild.addQualifier(repairLang);
                }
            }
        } while(true);
    }

    private static void moveExplicitAliases(XMPNode tree, ParseOptions options)
        throws XMPException
    {
        if(!tree.getHasAliases())
            return;
        tree.setHasAliases(false);
        boolean strictAliasing = options.getStrictAliasing();
        Iterator schemaIt = tree.getUnmodifiableChildren().iterator();
        do
        {
            if(!schemaIt.hasNext())
                break;
            XMPNode currSchema = (XMPNode)schemaIt.next();
            if(currSchema.getHasAliases())
            {
                Iterator propertyIt = currSchema.iterateChildren();
                do
                {
                    if(!propertyIt.hasNext())
                        break;
                    XMPNode currProp = (XMPNode)propertyIt.next();
                    if(currProp.isAlias())
                    {
                        currProp.setAlias(false);
                        XMPAliasInfo info = XMPMetaFactory.getSchemaRegistry().findAlias(currProp.getName());
                        if(info != null)
                        {
                            XMPNode baseSchema = XMPNodeUtils.findSchemaNode(tree, info.getNamespace(), null, true);
                            baseSchema.setImplicit(false);
                            XMPNode baseNode = XMPNodeUtils.findChildNode(baseSchema, (new StringBuilder()).append(info.getPrefix()).append(info.getPropName()).toString(), false);
                            if(baseNode == null)
                            {
                                if(info.getAliasForm().isSimple())
                                {
                                    String qname = (new StringBuilder()).append(info.getPrefix()).append(info.getPropName()).toString();
                                    currProp.setName(qname);
                                    baseSchema.addChild(currProp);
                                    propertyIt.remove();
                                } else
                                {
                                    baseNode = new XMPNode((new StringBuilder()).append(info.getPrefix()).append(info.getPropName()).toString(), info.getAliasForm().toPropertyOptions());
                                    baseSchema.addChild(baseNode);
                                    transplantArrayItemAlias(propertyIt, currProp, baseNode);
                                }
                            } else
                            if(info.getAliasForm().isSimple())
                            {
                                if(strictAliasing)
                                    compareAliasedSubtrees(currProp, baseNode, true);
                                propertyIt.remove();
                            } else
                            {
                                XMPNode itemNode = null;
                                if(info.getAliasForm().isArrayAltText())
                                {
                                    int xdIndex = XMPNodeUtils.lookupLanguageItem(baseNode, "x-default");
                                    if(xdIndex != -1)
                                        itemNode = baseNode.getChild(xdIndex);
                                } else
                                if(baseNode.hasChildren())
                                    itemNode = baseNode.getChild(1);
                                if(itemNode == null)
                                {
                                    transplantArrayItemAlias(propertyIt, currProp, baseNode);
                                } else
                                {
                                    if(strictAliasing)
                                        compareAliasedSubtrees(currProp, itemNode, true);
                                    propertyIt.remove();
                                }
                            }
                        }
                    }
                } while(true);
                currSchema.setHasAliases(false);
            }
        } while(true);
    }

    private static void transplantArrayItemAlias(Iterator propertyIt, XMPNode childNode, XMPNode baseArray)
        throws XMPException
    {
        if(baseArray.getOptions().isArrayAltText())
        {
            if(childNode.getOptions().getHasLanguage())
                throw new XMPException("Alias to x-default already has a language qualifier", 203);
            XMPNode langQual = new XMPNode("xml:lang", "x-default", null);
            childNode.addQualifier(langQual);
        }
        propertyIt.remove();
        childNode.setName("[]");
        baseArray.addChild(childNode);
    }

    private static void fixGPSTimeStamp(XMPNode exifSchema)
        throws XMPException
    {
        XMPNode gpsDateTime = XMPNodeUtils.findChildNode(exifSchema, "exif:GPSTimeStamp", false);
        if(gpsDateTime == null)
            return;
        XMPDateTime binGPSStamp;
        XMPDateTime binOtherDate;
        XMPNode otherDate;
        Calendar cal;
        try
        {
            binGPSStamp = XMPUtils.convertToDate(gpsDateTime.getValue());
            if(binGPSStamp.getYear() != 0 || binGPSStamp.getMonth() != 0 || binGPSStamp.getDay() != 0)
                return;
        }
        catch(XMPException e)
        {
            return;
        }
        otherDate = XMPNodeUtils.findChildNode(exifSchema, "exif:DateTimeOriginal", false);
        if(otherDate == null)
            otherDate = XMPNodeUtils.findChildNode(exifSchema, "exif:DateTimeDigitized", false);
        binOtherDate = XMPUtils.convertToDate(otherDate.getValue());
        cal = binGPSStamp.getCalendar();
        cal.set(1, binOtherDate.getYear());
        cal.set(2, binOtherDate.getMonth());
        cal.set(5, binOtherDate.getDay());
        binGPSStamp = new XMPDateTimeImpl(cal);
        gpsDateTime.setValue(XMPUtils.convertFromDate(binGPSStamp));
    }

    private static void deleteEmptySchemas(XMPNode tree)
    {
        Iterator it = tree.iterateChildren();
        do
        {
            if(!it.hasNext())
                break;
            XMPNode schema = (XMPNode)it.next();
            if(!schema.hasChildren())
                it.remove();
        } while(true);
    }

    private static void compareAliasedSubtrees(XMPNode aliasNode, XMPNode baseNode, boolean outerCall)
        throws XMPException
    {
        if(!aliasNode.getValue().equals(baseNode.getValue()) || aliasNode.getChildrenLength() != baseNode.getChildrenLength())
            throw new XMPException("Mismatch between alias and base nodes", 203);
        if(!outerCall && (!aliasNode.getName().equals(baseNode.getName()) || !aliasNode.getOptions().equals(baseNode.getOptions()) || aliasNode.getQualifierLength() != baseNode.getQualifierLength()))
            throw new XMPException("Mismatch between alias and base nodes", 203);
        Iterator an = aliasNode.iterateChildren();
        XMPNode aliasChild;
        XMPNode baseChild;
        for(Iterator bn = baseNode.iterateChildren(); an.hasNext() && bn.hasNext(); compareAliasedSubtrees(aliasChild, baseChild, false))
        {
            aliasChild = (XMPNode)an.next();
            baseChild = (XMPNode)bn.next();
        }

        an = aliasNode.iterateQualifier();
        XMPNode aliasQual;
        XMPNode baseQual;
        for(Iterator bn = baseNode.iterateQualifier(); an.hasNext() && bn.hasNext(); compareAliasedSubtrees(aliasQual, baseQual, false))
        {
            aliasQual = (XMPNode)an.next();
            baseQual = (XMPNode)bn.next();
        }

    }

    private static void migrateAudioCopyright(XMPMeta xmp, XMPNode dmCopyright)
    {
        try
        {
            XMPNode dcSchema = XMPNodeUtils.findSchemaNode(((XMPMetaImpl)xmp).getRoot(), "http://purl.org/dc/elements/1.1/", true);
            String dmValue = dmCopyright.getValue();
            String doubleLF = "\n\n";
            XMPNode dcRightsArray = XMPNodeUtils.findChildNode(dcSchema, "dc:rights", false);
            if(dcRightsArray == null || !dcRightsArray.hasChildren())
            {
                dmValue = (new StringBuilder()).append(doubleLF).append(dmValue).toString();
                xmp.setLocalizedText("http://purl.org/dc/elements/1.1/", "rights", "", "x-default", dmValue, null);
            } else
            {
                int xdIndex = XMPNodeUtils.lookupLanguageItem(dcRightsArray, "x-default");
                if(xdIndex < 0)
                {
                    String firstValue = dcRightsArray.getChild(1).getValue();
                    xmp.setLocalizedText("http://purl.org/dc/elements/1.1/", "rights", "", "x-default", firstValue, null);
                    xdIndex = XMPNodeUtils.lookupLanguageItem(dcRightsArray, "x-default");
                }
                XMPNode defaultNode = dcRightsArray.getChild(xdIndex);
                String defaultValue = defaultNode.getValue();
                int lfPos = defaultValue.indexOf(doubleLF);
                if(lfPos < 0)
                {
                    if(!dmValue.equals(defaultValue))
                        defaultNode.setValue((new StringBuilder()).append(defaultValue).append(doubleLF).append(dmValue).toString());
                } else
                if(!defaultValue.substring(lfPos + 2).equals(dmValue))
                    defaultNode.setValue((new StringBuilder()).append(defaultValue.substring(0, lfPos + 2)).append(dmValue).toString());
            }
            dmCopyright.getParent().removeChild(dmCopyright);
        }
        catch(XMPException e) { }
    }

    private static void initDCArrays()
    {
        dcArrayForms = new HashMap();
        PropertyOptions bagForm = new PropertyOptions();
        bagForm.setArray(true);
        dcArrayForms.put("dc:contributor", bagForm);
        dcArrayForms.put("dc:language", bagForm);
        dcArrayForms.put("dc:publisher", bagForm);
        dcArrayForms.put("dc:relation", bagForm);
        dcArrayForms.put("dc:subject", bagForm);
        dcArrayForms.put("dc:type", bagForm);
        PropertyOptions seqForm = new PropertyOptions();
        seqForm.setArray(true);
        seqForm.setArrayOrdered(true);
        dcArrayForms.put("dc:creator", seqForm);
        dcArrayForms.put("dc:date", seqForm);
        PropertyOptions altTextForm = new PropertyOptions();
        altTextForm.setArray(true);
        altTextForm.setArrayOrdered(true);
        altTextForm.setArrayAlternate(true);
        altTextForm.setArrayAltText(true);
        dcArrayForms.put("dc:description", altTextForm);
        dcArrayForms.put("dc:rights", altTextForm);
        dcArrayForms.put("dc:title", altTextForm);
    }

    private static Map dcArrayForms;

    static 
    {
        initDCArrays();
    }
}
