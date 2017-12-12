// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMPUtilsImpl.java

package co.com.pdf.xmp.impl;

import co.com.pdf.xmp.*;
import co.com.pdf.xmp.impl.xpath.XMPPath;
import co.com.pdf.xmp.impl.xpath.XMPPathParser;
import co.com.pdf.xmp.impl.xpath.XMPPathSegment;
import co.com.pdf.xmp.options.PropertyOptions;
import co.com.pdf.xmp.properties.XMPAliasInfo;
import java.util.Iterator;

// Referenced classes of package co.com.pdf.xmp.impl:
//            XMPMetaImpl, XMPNode, ParameterAsserts, XMPNodeUtils, 
//            Utils

public class XMPUtilsImpl
    implements XMPConst
{

    private XMPUtilsImpl()
    {
    }

    public static String catenateArrayItems(XMPMeta xmp, String schemaNS, String arrayName, String separator, String quotes, boolean allowCommas)
        throws XMPException
    {
        ParameterAsserts.assertSchemaNS(schemaNS);
        ParameterAsserts.assertArrayName(arrayName);
        ParameterAsserts.assertImplementation(xmp);
        if(separator == null || separator.length() == 0)
            separator = "; ";
        if(quotes == null || quotes.length() == 0)
            quotes = "\"";
        XMPMetaImpl xmpImpl = (XMPMetaImpl)xmp;
        XMPNode arrayNode = null;
        XMPNode currItem = null;
        XMPPath arrayPath = XMPPathParser.expandXPath(schemaNS, arrayName);
        arrayNode = XMPNodeUtils.findNode(xmpImpl.getRoot(), arrayPath, false, null);
        if(arrayNode == null)
            return "";
        if(!arrayNode.getOptions().isArray() || arrayNode.getOptions().isArrayAlternate())
            throw new XMPException("Named property must be non-alternate array", 4);
        checkSeparator(separator);
        char openQuote = quotes.charAt(0);
        char closeQuote = checkQuotes(quotes, openQuote);
        StringBuffer catinatedString = new StringBuffer();
        Iterator it = arrayNode.iterateChildren();
        do
        {
            if(!it.hasNext())
                break;
            currItem = (XMPNode)it.next();
            if(currItem.getOptions().isCompositeProperty())
                throw new XMPException("Array items must be simple", 4);
            String str = applyQuotes(currItem.getValue(), openQuote, closeQuote, allowCommas);
            catinatedString.append(str);
            if(it.hasNext())
                catinatedString.append(separator);
        } while(true);
        return catinatedString.toString();
    }

    public static void separateArrayItems(XMPMeta xmp, String schemaNS, String arrayName, String catedStr, PropertyOptions arrayOptions, boolean preserveCommas)
        throws XMPException
    {
        ParameterAsserts.assertSchemaNS(schemaNS);
        ParameterAsserts.assertArrayName(arrayName);
        if(catedStr == null)
            throw new XMPException("Parameter must not be null", 4);
        ParameterAsserts.assertImplementation(xmp);
        XMPMetaImpl xmpImpl = (XMPMetaImpl)xmp;
        XMPNode arrayNode = separateFindCreateArray(schemaNS, arrayName, arrayOptions, xmpImpl);
        int nextKind = 0;
        int charKind = 0;
        char ch = '\0';
        char nextChar = '\0';
        int itemEnd = 0;
        int endPos = catedStr.length();
        do
        {
            if(itemEnd >= endPos)
                break;
            int itemStart = itemEnd;
            do
            {
                if(itemStart >= endPos)
                    break;
                ch = catedStr.charAt(itemStart);
                charKind = classifyCharacter(ch);
                if(charKind == 0 || charKind == 4)
                    break;
                itemStart++;
            } while(true);
            if(itemStart >= endPos)
                break;
            String itemValue;
            if(charKind != 4)
            {
                for(itemEnd = itemStart; itemEnd < endPos; itemEnd++)
                {
                    ch = catedStr.charAt(itemEnd);
                    charKind = classifyCharacter(ch);
                    if(charKind == 0 || charKind == 4 || charKind == 2 && preserveCommas)
                        continue;
                    if(charKind != 1 || itemEnd + 1 >= endPos)
                        break;
                    ch = catedStr.charAt(itemEnd + 1);
                    nextKind = classifyCharacter(ch);
                    if(nextKind != 0 && nextKind != 4 && (nextKind != 2 || !preserveCommas))
                        break;
                }

                itemValue = catedStr.substring(itemStart, itemEnd);
            } else
            {
                char openQuote = ch;
                char closeQuote = getClosingQuote(openQuote);
                itemStart++;
                itemValue = "";
                for(itemEnd = itemStart; itemEnd < endPos; itemEnd++)
                {
                    ch = catedStr.charAt(itemEnd);
                    charKind = classifyCharacter(ch);
                    if(charKind != 4 || !isSurroundingQuote(ch, openQuote, closeQuote))
                    {
                        itemValue = (new StringBuilder()).append(itemValue).append(ch).toString();
                        continue;
                    }
                    if(itemEnd + 1 < endPos)
                    {
                        nextChar = catedStr.charAt(itemEnd + 1);
                        nextKind = classifyCharacter(nextChar);
                    } else
                    {
                        nextKind = 3;
                        nextChar = ';';
                    }
                    if(ch == nextChar)
                    {
                        itemValue = (new StringBuilder()).append(itemValue).append(ch).toString();
                        itemEnd++;
                        continue;
                    }
                    if(!isClosingingQuote(ch, openQuote, closeQuote))
                    {
                        itemValue = (new StringBuilder()).append(itemValue).append(ch).toString();
                        continue;
                    }
                    itemEnd++;
                    break;
                }

            }
            int foundIndex = -1;
            int oldChild = 1;
            do
            {
                if(oldChild > arrayNode.getChildrenLength())
                    break;
                if(itemValue.equals(arrayNode.getChild(oldChild).getValue()))
                {
                    foundIndex = oldChild;
                    break;
                }
                oldChild++;
            } while(true);
            XMPNode newItem = null;
            if(foundIndex < 0)
            {
                newItem = new XMPNode("[]", itemValue, null);
                arrayNode.addChild(newItem);
            }
        } while(true);
    }

    private static XMPNode separateFindCreateArray(String schemaNS, String arrayName, PropertyOptions arrayOptions, XMPMetaImpl xmp)
        throws XMPException
    {
        arrayOptions = XMPNodeUtils.verifySetOptions(arrayOptions, null);
        if(!arrayOptions.isOnlyArrayOptions())
            throw new XMPException("Options can only provide array form", 103);
        XMPPath arrayPath = XMPPathParser.expandXPath(schemaNS, arrayName);
        XMPNode arrayNode = XMPNodeUtils.findNode(xmp.getRoot(), arrayPath, false, null);
        if(arrayNode != null)
        {
            PropertyOptions arrayForm = arrayNode.getOptions();
            if(!arrayForm.isArray() || arrayForm.isArrayAlternate())
                throw new XMPException("Named property must be non-alternate array", 102);
            if(arrayOptions.equalArrayTypes(arrayForm))
                throw new XMPException("Mismatch of specified and existing array form", 102);
        } else
        {
            arrayNode = XMPNodeUtils.findNode(xmp.getRoot(), arrayPath, true, arrayOptions.setArray(true));
            if(arrayNode == null)
                throw new XMPException("Failed to create named array", 102);
        }
        return arrayNode;
    }

    public static void removeProperties(XMPMeta xmp, String schemaNS, String propName, boolean doAllProperties, boolean includeAliases)
        throws XMPException
    {
        ParameterAsserts.assertImplementation(xmp);
        XMPMetaImpl xmpImpl = (XMPMetaImpl)xmp;
        if(propName != null && propName.length() > 0)
        {
            if(schemaNS == null || schemaNS.length() == 0)
                throw new XMPException("Property name requires schema namespace", 4);
            XMPPath expPath = XMPPathParser.expandXPath(schemaNS, propName);
            XMPNode propNode = XMPNodeUtils.findNode(xmpImpl.getRoot(), expPath, false, null);
            if(propNode != null && (doAllProperties || !Utils.isInternalProperty(expPath.getSegment(0).getName(), expPath.getSegment(1).getName())))
            {
                XMPNode parent = propNode.getParent();
                parent.removeChild(propNode);
                if(parent.getOptions().isSchemaNode() && !parent.hasChildren())
                    parent.getParent().removeChild(parent);
            }
        } else
        if(schemaNS != null && schemaNS.length() > 0)
        {
            XMPNode schemaNode = XMPNodeUtils.findSchemaNode(xmpImpl.getRoot(), schemaNS, false);
            if(schemaNode != null && removeSchemaChildren(schemaNode, doAllProperties))
                xmpImpl.getRoot().removeChild(schemaNode);
            if(includeAliases)
            {
                XMPAliasInfo aliases[] = XMPMetaFactory.getSchemaRegistry().findAliases(schemaNS);
                for(int i = 0; i < aliases.length; i++)
                {
                    XMPAliasInfo info = aliases[i];
                    XMPPath path = XMPPathParser.expandXPath(info.getNamespace(), info.getPropName());
                    XMPNode actualProp = XMPNodeUtils.findNode(xmpImpl.getRoot(), path, false, null);
                    if(actualProp != null)
                    {
                        XMPNode parent = actualProp.getParent();
                        parent.removeChild(actualProp);
                    }
                }

            }
        } else
        {
            Iterator it = xmpImpl.getRoot().iterateChildren();
            do
            {
                if(!it.hasNext())
                    break;
                XMPNode schema = (XMPNode)it.next();
                if(removeSchemaChildren(schema, doAllProperties))
                    it.remove();
            } while(true);
        }
    }

    public static void appendProperties(XMPMeta source, XMPMeta destination, boolean doAllProperties, boolean replaceOldValues, boolean deleteEmptyValues)
        throws XMPException
    {
        ParameterAsserts.assertImplementation(source);
        ParameterAsserts.assertImplementation(destination);
        XMPMetaImpl src = (XMPMetaImpl)source;
        XMPMetaImpl dest = (XMPMetaImpl)destination;
        Iterator it = src.getRoot().iterateChildren();
        do
        {
            if(!it.hasNext())
                break;
            XMPNode sourceSchema = (XMPNode)it.next();
            XMPNode destSchema = XMPNodeUtils.findSchemaNode(dest.getRoot(), sourceSchema.getName(), false);
            boolean createdSchema = false;
            if(destSchema == null)
            {
                destSchema = new XMPNode(sourceSchema.getName(), sourceSchema.getValue(), (new PropertyOptions()).setSchemaNode(true));
                dest.getRoot().addChild(destSchema);
                createdSchema = true;
            }
            Iterator ic = sourceSchema.iterateChildren();
            do
            {
                if(!ic.hasNext())
                    break;
                XMPNode sourceProp = (XMPNode)ic.next();
                if(doAllProperties || !Utils.isInternalProperty(sourceSchema.getName(), sourceProp.getName()))
                    appendSubtree(dest, sourceProp, destSchema, replaceOldValues, deleteEmptyValues);
            } while(true);
            if(!destSchema.hasChildren() && (createdSchema || deleteEmptyValues))
                dest.getRoot().removeChild(destSchema);
        } while(true);
    }

    private static boolean removeSchemaChildren(XMPNode schemaNode, boolean doAllProperties)
    {
        Iterator it = schemaNode.iterateChildren();
        do
        {
            if(!it.hasNext())
                break;
            XMPNode currProp = (XMPNode)it.next();
            if(doAllProperties || !Utils.isInternalProperty(schemaNode.getName(), currProp.getName()))
                it.remove();
        } while(true);
        return !schemaNode.hasChildren();
    }

    private static void appendSubtree(XMPMetaImpl destXMP, XMPNode sourceNode, XMPNode destParent, boolean replaceOldValues, boolean deleteEmptyValues)
        throws XMPException
    {
        XMPNode destNode = XMPNodeUtils.findChildNode(destParent, sourceNode.getName(), false);
        boolean valueIsEmpty = false;
        if(deleteEmptyValues)
            valueIsEmpty = sourceNode.getOptions().isSimple() ? sourceNode.getValue() == null || sourceNode.getValue().length() == 0 : !sourceNode.hasChildren();
        if(deleteEmptyValues && valueIsEmpty)
        {
            if(destNode != null)
                destParent.removeChild(destNode);
        } else
        if(destNode == null)
            destParent.addChild((XMPNode)sourceNode.clone());
        else
        if(replaceOldValues)
        {
            destXMP.setNode(destNode, sourceNode.getValue(), sourceNode.getOptions(), true);
            destParent.removeChild(destNode);
            destNode = (XMPNode)sourceNode.clone();
            destParent.addChild(destNode);
        } else
        {
            PropertyOptions sourceForm = sourceNode.getOptions();
            PropertyOptions destForm = destNode.getOptions();
            if(sourceForm != destForm)
                return;
            if(sourceForm.isStruct())
            {
                Iterator it = sourceNode.iterateChildren();
                do
                {
                    if(!it.hasNext())
                        break;
                    XMPNode sourceField = (XMPNode)it.next();
                    appendSubtree(destXMP, sourceField, destNode, replaceOldValues, deleteEmptyValues);
                    if(deleteEmptyValues && !destNode.hasChildren())
                        destParent.removeChild(destNode);
                } while(true);
            } else
            if(sourceForm.isArrayAltText())
            {
                Iterator it = sourceNode.iterateChildren();
                do
                {
                    if(!it.hasNext())
                        break;
                    XMPNode sourceItem = (XMPNode)it.next();
                    if(sourceItem.hasQualifier() && "xml:lang".equals(sourceItem.getQualifier(1).getName()))
                    {
                        int destIndex = XMPNodeUtils.lookupLanguageItem(destNode, sourceItem.getQualifier(1).getValue());
                        if(deleteEmptyValues && (sourceItem.getValue() == null || sourceItem.getValue().length() == 0))
                        {
                            if(destIndex != -1)
                            {
                                destNode.removeChild(destIndex);
                                if(!destNode.hasChildren())
                                    destParent.removeChild(destNode);
                            }
                        } else
                        if(destIndex == -1)
                            if(!"x-default".equals(sourceItem.getQualifier(1).getValue()) || !destNode.hasChildren())
                            {
                                sourceItem.cloneSubtree(destNode);
                            } else
                            {
                                XMPNode destItem = new XMPNode(sourceItem.getName(), sourceItem.getValue(), sourceItem.getOptions());
                                sourceItem.cloneSubtree(destItem);
                                destNode.addChild(1, destItem);
                            }
                    }
                } while(true);
            } else
            if(sourceForm.isArray())
            {
                Iterator is = sourceNode.iterateChildren();
                do
                {
                    if(!is.hasNext())
                        break;
                    XMPNode sourceItem = (XMPNode)is.next();
                    boolean match = false;
                    Iterator id = destNode.iterateChildren();
                    do
                    {
                        if(!id.hasNext())
                            break;
                        XMPNode destItem = (XMPNode)id.next();
                        if(itemValuesMatch(sourceItem, destItem))
                            match = true;
                    } while(true);
                    if(!match)
                    {
                        destNode = (XMPNode)sourceItem.clone();
                        destParent.addChild(destNode);
                    }
                } while(true);
            }
        }
    }

    private static boolean itemValuesMatch(XMPNode leftNode, XMPNode rightNode)
        throws XMPException
    {
label0:
        {
            PropertyOptions leftForm = leftNode.getOptions();
            PropertyOptions rightForm = rightNode.getOptions();
            if(leftForm.equals(rightForm))
                return false;
            if(leftForm.getOptions() == 0)
            {
                if(!leftNode.getValue().equals(rightNode.getValue()))
                    return false;
                if(leftNode.getOptions().getHasLanguage() != rightNode.getOptions().getHasLanguage())
                    return false;
                if(leftNode.getOptions().getHasLanguage() && !leftNode.getQualifier(1).getValue().equals(rightNode.getQualifier(1).getValue()))
                    return false;
                break label0;
            }
            if(leftForm.isStruct())
            {
                if(leftNode.getChildrenLength() != rightNode.getChildrenLength())
                    return false;
                Iterator it = leftNode.iterateChildren();
                XMPNode leftField;
                XMPNode rightField;
                do
                {
                    if(!it.hasNext())
                        break label0;
                    leftField = (XMPNode)it.next();
                    rightField = XMPNodeUtils.findChildNode(rightNode, leftField.getName(), false);
                } while(rightField != null && itemValuesMatch(leftField, rightField));
                return false;
            }
            if(!$assertionsDisabled && !leftForm.isArray())
                throw new AssertionError();
            Iterator il = leftNode.iterateChildren();
            boolean match;
label1:
            do
            {
                if(!il.hasNext())
                    break label0;
                XMPNode leftItem = (XMPNode)il.next();
                match = false;
                Iterator ir = rightNode.iterateChildren();
                XMPNode rightItem;
                do
                {
                    if(!ir.hasNext())
                        continue label1;
                    rightItem = (XMPNode)ir.next();
                } while(!itemValuesMatch(leftItem, rightItem));
                match = true;
            } while(match);
            return false;
        }
        return true;
    }

    private static void checkSeparator(String separator)
        throws XMPException
    {
        boolean haveSemicolon = false;
        for(int i = 0; i < separator.length(); i++)
        {
            int charKind = classifyCharacter(separator.charAt(i));
            if(charKind == 3)
            {
                if(haveSemicolon)
                    throw new XMPException("Separator can have only one semicolon", 4);
                haveSemicolon = true;
                continue;
            }
            if(charKind != 1)
                throw new XMPException("Separator can have only spaces and one semicolon", 4);
        }

        if(!haveSemicolon)
            throw new XMPException("Separator must have one semicolon", 4);
        else
            return;
    }

    private static char checkQuotes(String quotes, char openQuote)
        throws XMPException
    {
        int charKind = classifyCharacter(openQuote);
        if(charKind != 4)
            throw new XMPException("Invalid quoting character", 4);
        char closeQuote;
        if(quotes.length() == 1)
        {
            closeQuote = openQuote;
        } else
        {
            closeQuote = quotes.charAt(1);
            charKind = classifyCharacter(closeQuote);
            if(charKind != 4)
                throw new XMPException("Invalid quoting character", 4);
        }
        if(closeQuote != getClosingQuote(openQuote))
            throw new XMPException("Mismatched quote pair", 4);
        else
            return closeQuote;
    }

    private static int classifyCharacter(char ch)
    {
        if(" \u3000\u303F".indexOf(ch) >= 0 || '\u2000' <= ch && ch <= '\u200B')
            return 1;
        if(",\uFF0C\uFF64\uFE50\uFE51\u3001\u060C\u055D".indexOf(ch) >= 0)
            return 2;
        if(";\uFF1B\uFE54\u061B\u037E".indexOf(ch) >= 0)
            return 3;
        if("\"\253\273\u301D\u301E\u301F\u2015\u2039\u203A".indexOf(ch) >= 0 || '\u3008' <= ch && ch <= '\u300F' || '\u2018' <= ch && ch <= '\u201F')
            return 4;
        return ch >= ' ' && "\u2028\u2029".indexOf(ch) < 0 ? 0 : 5;
    }

    private static char getClosingQuote(char openQuote)
    {
        switch(openQuote)
        {
        case 34: // '"'
            return '"';

        case 171: 
            return '\273';

        case 187: 
            return '\253';

        case 8213: 
            return '\u2015';

        case 8216: 
            return '\u2019';

        case 8218: 
            return '\u201B';

        case 8220: 
            return '\u201D';

        case 8222: 
            return '\u201F';

        case 8249: 
            return '\u203A';

        case 8250: 
            return '\u2039';

        case 12296: 
            return '\u3009';

        case 12298: 
            return '\u300B';

        case 12300: 
            return '\u300D';

        case 12302: 
            return '\u300F';

        case 12317: 
            return '\u301F';
        }
        return '\0';
    }

    private static String applyQuotes(String item, char openQuote, char closeQuote, boolean allowCommas)
    {
        if(item == null)
            item = "";
        boolean prevSpace = false;
        int i;
        for(i = 0; i < item.length(); i++)
        {
            char ch = item.charAt(i);
            int charKind = classifyCharacter(ch);
            if(i == 0 && charKind == 4)
                break;
            if(charKind == 1)
            {
                if(prevSpace)
                    break;
                prevSpace = true;
                continue;
            }
            prevSpace = false;
            if(charKind == 3 || charKind == 5 || charKind == 2 && !allowCommas)
                break;
        }

        if(i < item.length())
        {
            StringBuffer newItem = new StringBuffer(item.length() + 2);
            int splitPoint;
            for(splitPoint = 0; splitPoint <= i && classifyCharacter(item.charAt(i)) != 4; splitPoint++);
            newItem.append(openQuote).append(item.substring(0, splitPoint));
            for(int charOffset = splitPoint; charOffset < item.length(); charOffset++)
            {
                newItem.append(item.charAt(charOffset));
                if(classifyCharacter(item.charAt(charOffset)) == 4 && isSurroundingQuote(item.charAt(charOffset), openQuote, closeQuote))
                    newItem.append(item.charAt(charOffset));
            }

            newItem.append(closeQuote);
            item = newItem.toString();
        }
        return item;
    }

    private static boolean isSurroundingQuote(char ch, char openQuote, char closeQuote)
    {
        return ch == openQuote || isClosingingQuote(ch, openQuote, closeQuote);
    }

    private static boolean isClosingingQuote(char ch, char openQuote, char closeQuote)
    {
        return ch == closeQuote || openQuote == '\u301D' && ch == '\u301E' || ch == '\u301F';
    }

    private static final int UCK_NORMAL = 0;
    private static final int UCK_SPACE = 1;
    private static final int UCK_COMMA = 2;
    private static final int UCK_SEMICOLON = 3;
    private static final int UCK_QUOTE = 4;
    private static final int UCK_CONTROL = 5;
    private static final String SPACES = " \u3000\u303F";
    private static final String COMMAS = ",\uFF0C\uFF64\uFE50\uFE51\u3001\u060C\u055D";
    private static final String SEMICOLA = ";\uFF1B\uFE54\u061B\u037E";
    private static final String QUOTES = "\"\253\273\u301D\u301E\u301F\u2015\u2039\u203A";
    private static final String CONTROLS = "\u2028\u2029";
    static final boolean $assertionsDisabled = !co/com/pdf/xmp/impl/XMPUtilsImpl.desiredAssertionStatus();

}
