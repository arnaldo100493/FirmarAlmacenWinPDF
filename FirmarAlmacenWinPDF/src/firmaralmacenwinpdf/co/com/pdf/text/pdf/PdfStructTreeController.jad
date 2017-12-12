// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfStructTreeController.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            BadPdfFormatException, PdfDictionary, PdfArray, PdfObject, 
//            PdfNull, PdfIndirectReference, PRIndirectReference, PdfName, 
//            PdfBoolean, PdfNumber, PdfString, PdfReader, 
//            PdfCopy, PdfStructureTreeRoot, PdfIndirectObject

public class PdfStructTreeController
{
    public static final class returnType extends Enum
    {

        public static returnType[] values()
        {
            return (returnType[])$VALUES.clone();
        }

        public static returnType valueOf(String name)
        {
            return (returnType)Enum.valueOf(co/com/pdf/text/pdf/PdfStructTreeController$returnType, name);
        }

        public static final returnType BELOW;
        public static final returnType FOUND;
        public static final returnType ABOVE;
        public static final returnType NOTFOUND;
        private static final returnType $VALUES[];

        static 
        {
            BELOW = new returnType("BELOW", 0);
            FOUND = new returnType("FOUND", 1);
            ABOVE = new returnType("ABOVE", 2);
            NOTFOUND = new returnType("NOTFOUND", 3);
            $VALUES = (new returnType[] {
                BELOW, FOUND, ABOVE, NOTFOUND
            });
        }

        private returnType(String s, int i)
        {
            super(s, i);
        }
    }


    protected PdfStructTreeController(PdfReader reader, PdfCopy writer)
        throws BadPdfFormatException
    {
        roleMap = null;
        sourceRoleMap = null;
        sourceClassMap = null;
        nullReference = null;
        if(!writer.isTagged())
        {
            throw new BadPdfFormatException(MessageLocalization.getComposedMessage("no.structtreeroot.found", new Object[0]));
        } else
        {
            this.writer = writer;
            structureTreeRoot = writer.getStructureTreeRoot();
            structureTreeRoot.put(PdfName.PARENTTREE, new PdfDictionary(PdfName.STRUCTELEM));
            setReader(reader);
            return;
        }
    }

    protected void setReader(PdfReader reader)
        throws BadPdfFormatException
    {
        this.reader = reader;
        PdfObject obj = reader.getCatalog().get(PdfName.STRUCTTREEROOT);
        obj = getDirectObject(obj);
        if(obj == null || !obj.isDictionary())
            throw new BadPdfFormatException(MessageLocalization.getComposedMessage("no.structtreeroot.found", new Object[0]));
        structTreeRoot = (PdfDictionary)obj;
        obj = getDirectObject(structTreeRoot.get(PdfName.PARENTTREE));
        if(obj == null || !obj.isDictionary())
        {
            throw new BadPdfFormatException(MessageLocalization.getComposedMessage("the.document.does.not.contain.parenttree", new Object[0]));
        } else
        {
            parentTree = (PdfDictionary)obj;
            sourceRoleMap = null;
            sourceClassMap = null;
            nullReference = null;
            return;
        }
    }

    public static boolean checkTagged(PdfReader reader)
    {
        PdfObject obj = reader.getCatalog().get(PdfName.STRUCTTREEROOT);
        obj = getDirectObject(obj);
        if(obj == null || !obj.isDictionary())
            return false;
        PdfDictionary structTreeRoot = (PdfDictionary)obj;
        obj = getDirectObject(structTreeRoot.get(PdfName.PARENTTREE));
        return obj.isDictionary();
    }

    public static PdfObject getDirectObject(PdfObject object)
    {
        if(object == null)
            return null;
        for(; object.isIndirect(); object = PdfReader.getPdfObjectRelease(object));
        return object;
    }

    public void copyStructTreeForPage(PdfNumber sourceArrayNumber, int newArrayNumber)
        throws BadPdfFormatException, IOException
    {
        if(copyPageMarks(parentTree, sourceArrayNumber, newArrayNumber) == returnType.NOTFOUND)
            throw new BadPdfFormatException(MessageLocalization.getComposedMessage("invalid.structparent", new Object[0]));
        else
            return;
    }

    private returnType copyPageMarks(PdfDictionary parentTree, PdfNumber arrayNumber, int newArrayNumber)
        throws BadPdfFormatException, IOException
    {
        PdfArray pages = (PdfArray)getDirectObject(parentTree.get(PdfName.NUMS));
        if(pages == null)
        {
            PdfArray kids = (PdfArray)getDirectObject(parentTree.get(PdfName.KIDS));
            if(kids == null)
                return returnType.NOTFOUND;
            int cur = kids.size() / 2;
            int begin = 0;
            do
            {
                PdfDictionary kidTree = (PdfDictionary)getDirectObject(kids.getPdfObject(cur + begin));
                static class _cls1
                {

                    static final int $SwitchMap$co$com$pdf$text$pdf$PdfStructTreeController$returnType[];

                    static 
                    {
                        $SwitchMap$co$com$pdf$text$pdf$PdfStructTreeController$returnType = new int[returnType.values().length];
                        try
                        {
                            $SwitchMap$co$com$pdf$text$pdf$PdfStructTreeController$returnType[returnType.FOUND.ordinal()] = 1;
                        }
                        catch(NoSuchFieldError ex) { }
                        try
                        {
                            $SwitchMap$co$com$pdf$text$pdf$PdfStructTreeController$returnType[returnType.ABOVE.ordinal()] = 2;
                        }
                        catch(NoSuchFieldError ex) { }
                        try
                        {
                            $SwitchMap$co$com$pdf$text$pdf$PdfStructTreeController$returnType[returnType.BELOW.ordinal()] = 3;
                        }
                        catch(NoSuchFieldError ex) { }
                    }
                }

                switch(_cls1..SwitchMap.co.com.pdf.text.pdf.PdfStructTreeController.returnType[copyPageMarks(kidTree, arrayNumber, newArrayNumber).ordinal()])
                {
                case 1: // '\001'
                    return returnType.FOUND;

                case 2: // '\002'
                    begin += cur;
                    cur /= 2;
                    if(cur == 0)
                        cur = 1;
                    if(cur + begin == kids.size())
                        return returnType.ABOVE;
                    break;

                case 3: // '\003'
                    if(cur + begin == 0)
                        return returnType.BELOW;
                    if(cur == 0)
                        return returnType.NOTFOUND;
                    cur /= 2;
                    break;

                default:
                    return returnType.NOTFOUND;
                }
            } while(true);
        } else
        if(pages.size() == 0)
            return returnType.NOTFOUND;
        else
            return findAndCopyMarks(pages, arrayNumber.intValue(), newArrayNumber);
    }

    private returnType findAndCopyMarks(PdfArray pages, int arrayNumber, int newArrayNumber)
        throws BadPdfFormatException, IOException
    {
        if(pages.getAsNumber(0).intValue() > arrayNumber)
            return returnType.BELOW;
        if(pages.getAsNumber(pages.size() - 2).intValue() < arrayNumber)
            return returnType.ABOVE;
        int cur = pages.size() / 4;
        int begin = 0;
        do
        {
            int curNumber = pages.getAsNumber((begin + cur) * 2).intValue();
            if(curNumber == arrayNumber)
            {
                PdfObject obj = pages.getPdfObject((begin + cur) * 2 + 1);
                PdfObject obj1 = obj;
                for(; obj.isIndirect(); obj = PdfReader.getPdfObjectRelease(obj));
                if(obj.isArray())
                {
                    PdfObject firstNotNullKid = null;
                    for(Iterator i$ = ((PdfArray)obj).iterator(); i$.hasNext();)
                    {
                        PdfObject numObj = (PdfObject)i$.next();
                        if(numObj.isNull())
                        {
                            if(nullReference == null)
                                nullReference = writer.addToBody(new PdfNull()).getIndirectReference();
                            structureTreeRoot.setPageMark(newArrayNumber, nullReference);
                        } else
                        {
                            PdfObject res = writer.copyObject(numObj, true, false);
                            if(firstNotNullKid == null)
                                firstNotNullKid = res;
                            structureTreeRoot.setPageMark(newArrayNumber, (PdfIndirectReference)res);
                        }
                    }

                    PdfObject structKids = structTreeRoot.get(PdfName.K);
                    if(structKids == null || !structKids.isArray() && !structKids.isIndirect())
                        addKid(structureTreeRoot, firstNotNullKid);
                    else
                    if(structKids.isIndirect())
                    {
                        addKid(structKids);
                    } else
                    {
                        PdfObject kid;
                        for(Iterator i$ = ((PdfArray)structKids).iterator(); i$.hasNext(); addKid(kid))
                            kid = (PdfObject)i$.next();

                    }
                } else
                if(obj.isDictionary())
                {
                    PdfDictionary k = getKDict((PdfDictionary)obj);
                    if(k == null)
                        return returnType.NOTFOUND;
                    PdfObject res = writer.copyObject(obj1, true, false);
                    structureTreeRoot.setAnnotationMark(newArrayNumber, (PdfIndirectReference)res);
                } else
                {
                    return returnType.NOTFOUND;
                }
                return returnType.FOUND;
            }
            if(curNumber < arrayNumber)
            {
                begin += cur;
                cur /= 2;
                if(cur == 0)
                    cur = 1;
                if(cur + begin == pages.size())
                    return returnType.NOTFOUND;
            } else
            {
                if(cur + begin == 0)
                    return returnType.BELOW;
                if(cur == 0)
                    return returnType.NOTFOUND;
                cur /= 2;
            }
        } while(true);
    }

    static PdfDictionary getKDict(PdfDictionary obj)
    {
        PdfDictionary k = obj.getAsDict(PdfName.K);
        if(k != null)
        {
            if(PdfName.OBJR.equals(k.getAsName(PdfName.TYPE)))
                return k;
        } else
        {
            PdfArray k1 = obj.getAsArray(PdfName.K);
            if(k1 == null)
                return null;
            for(int i = 0; i < k1.size(); i++)
            {
                k = k1.getAsDict(i);
                if(k != null && PdfName.OBJR.equals(k.getAsName(PdfName.TYPE)))
                    return k;
            }

        }
        return null;
    }

    private void addKid(PdfObject obj)
        throws IOException, BadPdfFormatException
    {
        if(!obj.isIndirect())
            return;
        PRIndirectReference currRef = (PRIndirectReference)obj;
        PdfCopy.RefKey key = new PdfCopy.RefKey(currRef);
        if(!writer.indirects.containsKey(key))
            writer.copyIndirect(currRef, true, false);
        PdfIndirectReference newKid = ((PdfCopy.IndirectReferences)writer.indirects.get(key)).getRef();
        if(writer.updateRootKids)
            addKid(((PdfDictionary) (structureTreeRoot)), ((PdfObject) (newKid)));
    }

    private static PdfArray getDirectArray(PdfArray in)
    {
        PdfArray out = new PdfArray();
        for(int i = 0; i < in.size(); i++)
        {
            PdfObject value = getDirectObject(in.getPdfObject(i));
            if(value == null)
                continue;
            if(value.isArray())
            {
                out.add(getDirectArray((PdfArray)value));
                continue;
            }
            if(value.isDictionary())
                out.add(getDirectDict((PdfDictionary)value));
            else
                out.add(value);
        }

        return out;
    }

    private static PdfDictionary getDirectDict(PdfDictionary in)
    {
        PdfDictionary out = new PdfDictionary();
        Iterator i$ = in.hashMap.entrySet().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            PdfObject value = getDirectObject((PdfObject)entry.getValue());
            if(value != null)
                if(value.isArray())
                    out.put((PdfName)entry.getKey(), getDirectArray((PdfArray)value));
                else
                if(value.isDictionary())
                    out.put((PdfName)entry.getKey(), getDirectDict((PdfDictionary)value));
                else
                    out.put((PdfName)entry.getKey(), value);
        } while(true);
        return out;
    }

    public static boolean compareObjects(PdfObject value1, PdfObject value2)
    {
        value2 = getDirectObject(value2);
        if(value2 == null)
            return false;
        if(value1.type() != value2.type())
            return false;
        if(value1.isBoolean())
        {
            if(value1 == value2)
                return true;
            if(value2 instanceof PdfBoolean)
                return ((PdfBoolean)value1).booleanValue() == ((PdfBoolean)value2).booleanValue();
            else
                return false;
        }
        if(value1.isName())
            return value1.equals(value2);
        if(value1.isNumber())
        {
            if(value1 == value2)
                return true;
            if(value2 instanceof PdfNumber)
                return ((PdfNumber)value1).doubleValue() == ((PdfNumber)value2).doubleValue();
            else
                return false;
        }
        if(value1.isNull())
        {
            if(value1 == value2)
                return true;
            return value2 instanceof PdfNull;
        }
        if(value1.isString())
        {
            if(value1 == value2)
                return true;
            if(value2 instanceof PdfString)
                return ((PdfString)value2).value == null && ((PdfString)value1).value == null || ((PdfString)value1).value != null && ((PdfString)value1).value.equals(((PdfString)value2).value);
            else
                return false;
        }
        if(value1.isArray())
        {
            PdfArray array1 = (PdfArray)value1;
            PdfArray array2 = (PdfArray)value2;
            if(array1.size() != array2.size())
                return false;
            for(int i = 0; i < array1.size(); i++)
                if(!compareObjects(array1.getPdfObject(i), array2.getPdfObject(i)))
                    return false;

            return true;
        }
        if(value1.isDictionary())
        {
            PdfDictionary first = (PdfDictionary)value1;
            PdfDictionary second = (PdfDictionary)value2;
            if(first.size() != second.size())
                return false;
            for(Iterator i$ = first.hashMap.keySet().iterator(); i$.hasNext();)
            {
                PdfName name = (PdfName)i$.next();
                if(!compareObjects(first.get(name), second.get(name)))
                    return false;
            }

            return true;
        } else
        {
            return false;
        }
    }

    protected void addClass(PdfObject object)
        throws BadPdfFormatException
    {
        object = getDirectObject(object);
        if(object.isDictionary())
        {
            PdfObject curClass = ((PdfDictionary)object).get(PdfName.C);
            if(curClass == null)
                return;
            if(curClass.isArray())
            {
                PdfArray array = (PdfArray)curClass;
                for(int i = 0; i < array.size(); i++)
                    addClass(array.getPdfObject(i));

            } else
            if(curClass.isName())
                addClass(curClass);
        } else
        if(object.isName())
        {
            PdfName name = (PdfName)object;
            if(sourceClassMap == null)
            {
                object = getDirectObject(structTreeRoot.get(PdfName.CLASSMAP));
                if(object == null || !object.isDictionary())
                    return;
                sourceClassMap = (PdfDictionary)object;
            }
            object = getDirectObject(sourceClassMap.get(name));
            if(object == null)
                return;
            PdfObject put = structureTreeRoot.getMappedClass(name);
            if(put != null)
            {
                if(!compareObjects(put, object))
                    throw new BadPdfFormatException(MessageLocalization.getComposedMessage("conflict.in.classmap", new Object[] {
                        name
                    }));
            } else
            if(object.isDictionary())
                structureTreeRoot.mapClass(name, getDirectDict((PdfDictionary)object));
            else
            if(object.isArray())
                structureTreeRoot.mapClass(name, getDirectArray((PdfArray)object));
        }
    }

    protected void addRole(PdfName structType)
        throws BadPdfFormatException
    {
        if(structType == null)
            return;
        PdfName arr$[] = standardTypes;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            PdfName name = arr$[i$];
            if(name.equals(structType))
                return;
        }

        PdfObject object;
        if(sourceRoleMap == null)
        {
            object = getDirectObject(structTreeRoot.get(PdfName.ROLEMAP));
            if(object == null || !object.isDictionary())
                return;
            sourceRoleMap = (PdfDictionary)object;
        }
        object = sourceRoleMap.get(structType);
        if(object == null || !object.isName())
            return;
        PdfObject currentRole;
        if(roleMap == null)
        {
            roleMap = new PdfDictionary();
            structureTreeRoot.put(PdfName.ROLEMAP, roleMap);
            roleMap.put(structType, object);
        } else
        if((currentRole = roleMap.get(structType)) != null)
        {
            if(!currentRole.equals(object))
                throw new BadPdfFormatException(MessageLocalization.getComposedMessage("conflict.in.rolemap", new Object[] {
                    object
                }));
        } else
        {
            roleMap.put(structType, object);
        }
    }

    protected void addKid(PdfDictionary parent, PdfObject kid)
    {
        PdfObject kidObj = parent.get(PdfName.K);
        PdfArray kids;
        if(kidObj instanceof PdfArray)
        {
            kids = (PdfArray)kidObj;
        } else
        {
            kids = new PdfArray();
            if(kidObj != null)
                kids.add(kidObj);
        }
        kids.add(kid);
        parent.put(PdfName.K, kids);
    }

    private PdfDictionary structTreeRoot;
    private PdfCopy writer;
    private PdfStructureTreeRoot structureTreeRoot;
    private PdfDictionary parentTree;
    protected PdfReader reader;
    private PdfDictionary roleMap;
    private PdfDictionary sourceRoleMap;
    private PdfDictionary sourceClassMap;
    private PdfIndirectReference nullReference;
    public static final PdfName standardTypes[];

    static 
    {
        standardTypes = (new PdfName[] {
            PdfName.P, PdfName.H, PdfName.H1, PdfName.H2, PdfName.H3, PdfName.H4, PdfName.H5, PdfName.H6, PdfName.L, PdfName.LBL, 
            PdfName.LI, PdfName.LBODY, PdfName.TABLE, PdfName.TABLEROW, PdfName.TH, PdfName.TD, PdfName.THEAD, PdfName.TBODY, PdfName.TFOOT, PdfName.SPAN, 
            PdfName.QUOTE, PdfName.NOTE, PdfName.REFERENCE, PdfName.BIBENTRY, PdfName.CODE, PdfName.LINK, PdfName.ANNOT, PdfName.RUBY, PdfName.WARICHU
        });
    }
}
