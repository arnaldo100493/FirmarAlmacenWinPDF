// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AcroFields.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.io.*;
import co.com.pdf.text.pdf.codec.Base64;
import co.com.pdf.text.pdf.security.PdfPKCS7;
import co.com.pdf.text.xml.XmlToTxt;
import java.io.*;
import java.util.*;
import org.w3c.dom.Node;

// Referenced classes of package co.com.pdf.text.pdf:
//            XfaForm, PdfStamperImp, PdfDictionary, PdfArray, 
//            PdfString, PdfName, PRTokeniser, RandomAccessFileOrArray, 
//            GrayColor, CMYKColor, PRIndirectReference, DocumentFont, 
//            BaseFont, PRStream, TextField, PdfNumber, 
//            PdfAppearance, PdfIndirectReference, FontDetails, PushbuttonField, 
//            PdfReader, PdfWriter, PdfBoolean, PdfObject, 
//            FdfWriter, BaseField, ByteBuffer, PdfFormField, 
//            PdfEncodings, ExtendedColor, FdfReader, XfdfReader, 
//            PdfDate, IntHashtable

public class AcroFields
{
    public static class FieldPosition
    {

        public int page;
        public Rectangle position;

        public FieldPosition()
        {
        }
    }

    private static class SorterComparator
        implements Comparator
    {

        public int compare(Object o1[], Object o2[])
        {
            int n1 = ((int[])(int[])o1[1])[0];
            int n2 = ((int[])(int[])o2[1])[0];
            return n1 - n2;
        }

        public volatile int compare(Object x0, Object x1)
        {
            return compare((Object[])x0, (Object[])x1);
        }

        private SorterComparator()
        {
        }

    }

    private static class InstHit
    {

        public boolean isHit(int n)
        {
            if(hits == null)
                return true;
            else
                return hits.containsKey(n);
        }

        IntHashtable hits;

        public InstHit(int inst[])
        {
            if(inst == null)
                return;
            hits = new IntHashtable();
            for(int k = 0; k < inst.length; k++)
                hits.put(inst[k], 1);

        }
    }

    public static class Item
    {

        public void writeToAll(PdfName key, PdfObject value, int writeFlags)
        {
            PdfDictionary curDict = null;
            if((writeFlags & 1) != 0)
            {
                for(int i = 0; i < merged.size(); i++)
                {
                    curDict = getMerged(i);
                    curDict.put(key, value);
                }

            }
            if((writeFlags & 2) != 0)
            {
                for(int i = 0; i < widgets.size(); i++)
                {
                    curDict = getWidget(i);
                    curDict.put(key, value);
                }

            }
            if((writeFlags & 4) != 0)
            {
                for(int i = 0; i < values.size(); i++)
                {
                    curDict = getValue(i);
                    curDict.put(key, value);
                }

            }
        }

        public void markUsed(AcroFields parentFields, int writeFlags)
        {
            if((writeFlags & 4) != 0)
            {
                for(int i = 0; i < size(); i++)
                    parentFields.markUsed(getValue(i));

            }
            if((writeFlags & 2) != 0)
            {
                for(int i = 0; i < size(); i++)
                    parentFields.markUsed(getWidget(i));

            }
        }

        public int size()
        {
            return values.size();
        }

        void remove(int killIdx)
        {
            values.remove(killIdx);
            widgets.remove(killIdx);
            widget_refs.remove(killIdx);
            merged.remove(killIdx);
            page.remove(killIdx);
            tabOrder.remove(killIdx);
        }

        public PdfDictionary getValue(int idx)
        {
            return (PdfDictionary)values.get(idx);
        }

        void addValue(PdfDictionary value)
        {
            values.add(value);
        }

        public PdfDictionary getWidget(int idx)
        {
            return (PdfDictionary)widgets.get(idx);
        }

        void addWidget(PdfDictionary widget)
        {
            widgets.add(widget);
        }

        public PdfIndirectReference getWidgetRef(int idx)
        {
            return (PdfIndirectReference)widget_refs.get(idx);
        }

        void addWidgetRef(PdfIndirectReference widgRef)
        {
            widget_refs.add(widgRef);
        }

        public PdfDictionary getMerged(int idx)
        {
            return (PdfDictionary)merged.get(idx);
        }

        void addMerged(PdfDictionary mergeDict)
        {
            merged.add(mergeDict);
        }

        public Integer getPage(int idx)
        {
            return (Integer)page.get(idx);
        }

        void addPage(int pg)
        {
            page.add(Integer.valueOf(pg));
        }

        void forcePage(int idx, int pg)
        {
            page.set(idx, Integer.valueOf(pg));
        }

        public Integer getTabOrder(int idx)
        {
            return (Integer)tabOrder.get(idx);
        }

        void addTabOrder(int order)
        {
            tabOrder.add(Integer.valueOf(order));
        }

        public static final int WRITE_MERGED = 1;
        public static final int WRITE_WIDGET = 2;
        public static final int WRITE_VALUE = 4;
        protected ArrayList values;
        protected ArrayList widgets;
        protected ArrayList widget_refs;
        protected ArrayList merged;
        protected ArrayList page;
        protected ArrayList tabOrder;

        public Item()
        {
            values = new ArrayList();
            widgets = new ArrayList();
            widget_refs = new ArrayList();
            merged = new ArrayList();
            page = new ArrayList();
            tabOrder = new ArrayList();
        }
    }


    AcroFields(PdfReader reader, PdfWriter writer)
    {
        extensionFonts = new HashMap();
        generateAppearances = true;
        localFonts = new HashMap();
        this.reader = reader;
        this.writer = writer;
        try
        {
            xfa = new XfaForm(reader);
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
        if(writer instanceof PdfStamperImp)
            append = ((PdfStamperImp)writer).isAppend();
        fill();
    }

    void fill()
    {
        fields = new HashMap();
        PdfDictionary top = (PdfDictionary)PdfReader.getPdfObjectRelease(reader.getCatalog().get(PdfName.ACROFORM));
        if(top == null)
            return;
        PdfBoolean needappearances = top.getAsBoolean(PdfName.NEEDAPPEARANCES);
        if(needappearances == null || !needappearances.booleanValue())
            setGenerateAppearances(true);
        else
            setGenerateAppearances(false);
        PdfArray arrfds = (PdfArray)PdfReader.getPdfObjectRelease(top.get(PdfName.FIELDS));
        if(arrfds == null || arrfds.size() == 0)
            return;
        for(int k = 1; k <= reader.getNumberOfPages(); k++)
        {
            PdfDictionary page = reader.getPageNRelease(k);
            PdfArray annots = (PdfArray)PdfReader.getPdfObjectRelease(page.get(PdfName.ANNOTS), page);
            if(annots == null)
                continue;
            for(int j = 0; j < annots.size(); j++)
            {
                PdfDictionary annot = annots.getAsDict(j);
                if(annot == null)
                {
                    PdfReader.releaseLastXrefPartial(annots.getAsIndirectObject(j));
                    continue;
                }
                if(!PdfName.WIDGET.equals(annot.getAsName(PdfName.SUBTYPE)))
                {
                    PdfReader.releaseLastXrefPartial(annots.getAsIndirectObject(j));
                    continue;
                }
                PdfDictionary widget = annot;
                PdfDictionary dic = new PdfDictionary();
                dic.putAll(annot);
                String name = "";
                PdfDictionary value = null;
                PdfObject lastV = null;
                for(; annot != null; annot = annot.getAsDict(PdfName.PARENT))
                {
                    dic.mergeDifferent(annot);
                    PdfString t = annot.getAsString(PdfName.T);
                    if(t != null)
                        name = (new StringBuilder()).append(t.toUnicodeString()).append(".").append(name).toString();
                    if(lastV == null && annot.get(PdfName.V) != null)
                        lastV = PdfReader.getPdfObjectRelease(annot.get(PdfName.V));
                    if(value != null || t == null)
                        continue;
                    value = annot;
                    if(annot.get(PdfName.V) == null && lastV != null)
                        value.put(PdfName.V, lastV);
                }

                if(name.length() > 0)
                    name = name.substring(0, name.length() - 1);
                Item item = (Item)fields.get(name);
                if(item == null)
                {
                    item = new Item();
                    fields.put(name, item);
                }
                if(value == null)
                    item.addValue(widget);
                else
                    item.addValue(value);
                item.addWidget(widget);
                item.addWidgetRef(annots.getAsIndirectObject(j));
                if(top != null)
                    dic.mergeDifferent(top);
                item.addMerged(dic);
                item.addPage(k);
                item.addTabOrder(j);
            }

        }

        PdfNumber sigFlags = top.getAsNumber(PdfName.SIGFLAGS);
        if(sigFlags == null || (sigFlags.intValue() & 1) != 1)
            return;
        for(int j = 0; j < arrfds.size(); j++)
        {
            PdfDictionary annot = arrfds.getAsDict(j);
            if(annot == null)
            {
                PdfReader.releaseLastXrefPartial(arrfds.getAsIndirectObject(j));
                continue;
            }
            if(!PdfName.WIDGET.equals(annot.getAsName(PdfName.SUBTYPE)))
            {
                PdfReader.releaseLastXrefPartial(arrfds.getAsIndirectObject(j));
                continue;
            }
            PdfArray kids = (PdfArray)PdfReader.getPdfObjectRelease(annot.get(PdfName.KIDS));
            if(kids != null)
                continue;
            PdfDictionary dic = new PdfDictionary();
            dic.putAll(annot);
            PdfString t = annot.getAsString(PdfName.T);
            if(t == null)
                continue;
            String name = t.toUnicodeString();
            if(!fields.containsKey(name))
            {
                Item item = new Item();
                fields.put(name, item);
                item.addValue(dic);
                item.addWidget(dic);
                item.addWidgetRef(arrfds.getAsIndirectObject(j));
                item.addMerged(dic);
                item.addPage(-1);
                item.addTabOrder(-1);
            }
        }

    }

    public String[] getAppearanceStates(String fieldName)
    {
        Item fd = (Item)fields.get(fieldName);
        if(fd == null)
            return null;
        HashSet names = new LinkedHashSet();
        PdfDictionary vals = fd.getValue(0);
        PdfString stringOpt = vals.getAsString(PdfName.OPT);
        if(stringOpt != null)
        {
            names.add(stringOpt.toUnicodeString());
        } else
        {
            PdfArray arrayOpt = vals.getAsArray(PdfName.OPT);
            if(arrayOpt != null)
            {
                for(int k = 0; k < arrayOpt.size(); k++)
                {
                    PdfObject pdfObject = arrayOpt.getDirectObject(k);
                    PdfString valStr = null;
                    switch(pdfObject.type())
                    {
                    case 5: // '\005'
                        PdfArray pdfArray = (PdfArray)pdfObject;
                        valStr = pdfArray.getAsString(1);
                        break;

                    case 3: // '\003'
                        valStr = (PdfString)pdfObject;
                        break;
                    }
                    if(valStr != null)
                        names.add(valStr.toUnicodeString());
                }

            }
        }
        for(int k = 0; k < fd.size(); k++)
        {
            PdfDictionary dic = fd.getWidget(k);
            dic = dic.getAsDict(PdfName.AP);
            if(dic == null)
                continue;
            dic = dic.getAsDict(PdfName.N);
            if(dic == null)
                continue;
            String name;
            for(Iterator i$ = dic.getKeys().iterator(); i$.hasNext(); names.add(name))
            {
                Object element = i$.next();
                name = PdfName.decodeName(((PdfName)element).toString());
            }

        }

        String out[] = new String[names.size()];
        return (String[])names.toArray(out);
    }

    private String[] getListOption(String fieldName, int idx)
    {
        Item fd = getFieldItem(fieldName);
        if(fd == null)
            return null;
        PdfArray ar = fd.getMerged(0).getAsArray(PdfName.OPT);
        if(ar == null)
            return null;
        String ret[] = new String[ar.size()];
        for(int k = 0; k < ar.size(); k++)
        {
            PdfObject obj = ar.getDirectObject(k);
            try
            {
                if(obj.isArray())
                    obj = ((PdfArray)obj).getDirectObject(idx);
                if(obj.isString())
                    ret[k] = ((PdfString)obj).toUnicodeString();
                else
                    ret[k] = obj.toString();
            }
            catch(Exception e)
            {
                ret[k] = "";
            }
        }

        return ret;
    }

    public String[] getListOptionExport(String fieldName)
    {
        return getListOption(fieldName, 0);
    }

    public String[] getListOptionDisplay(String fieldName)
    {
        return getListOption(fieldName, 1);
    }

    public boolean setListOption(String fieldName, String exportValues[], String displayValues[])
    {
        if(exportValues == null && displayValues == null)
            return false;
        if(exportValues != null && displayValues != null && exportValues.length != displayValues.length)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.export.and.the.display.array.must.have.the.same.size", new Object[0]));
        int ftype = getFieldType(fieldName);
        if(ftype != 6 && ftype != 5)
            return false;
        Item fd = (Item)fields.get(fieldName);
        String sing[] = null;
        if(exportValues == null && displayValues != null)
            sing = displayValues;
        else
        if(exportValues != null && displayValues == null)
            sing = exportValues;
        PdfArray opt = new PdfArray();
        if(sing != null)
        {
            for(int k = 0; k < sing.length; k++)
                opt.add(new PdfString(sing[k], "UnicodeBig"));

        } else
        {
            for(int k = 0; k < exportValues.length; k++)
            {
                PdfArray a = new PdfArray();
                a.add(new PdfString(exportValues[k], "UnicodeBig"));
                a.add(new PdfString(displayValues[k], "UnicodeBig"));
                opt.add(a);
            }

        }
        fd.writeToAll(PdfName.OPT, opt, 5);
        return true;
    }

    public int getFieldType(String fieldName)
    {
        Item fd = getFieldItem(fieldName);
        if(fd == null)
            return 0;
        PdfDictionary merged = fd.getMerged(0);
        PdfName type = merged.getAsName(PdfName.FT);
        if(type == null)
            return 0;
        int ff = 0;
        PdfNumber ffo = merged.getAsNumber(PdfName.FF);
        if(ffo != null)
            ff = ffo.intValue();
        if(PdfName.BTN.equals(type))
        {
            if((ff & 0x10000) != 0)
                return 1;
            return (ff & 0x8000) == 0 ? 2 : 3;
        }
        if(PdfName.TX.equals(type))
            return 4;
        if(PdfName.CH.equals(type))
            return (ff & 0x20000) == 0 ? 5 : 6;
        return !PdfName.SIG.equals(type) ? 0 : 7;
    }

    public void exportAsFdf(FdfWriter writer)
    {
        Iterator i$ = fields.entrySet().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            Item item = (Item)entry.getValue();
            String name = (String)entry.getKey();
            PdfObject v = item.getMerged(0).get(PdfName.V);
            if(v != null)
            {
                String value = getField(name);
                if(lastWasString)
                    writer.setFieldAsString(name, value);
                else
                    writer.setFieldAsName(name, value);
            }
        } while(true);
    }

    public boolean renameField(String oldName, String newName)
    {
        int idx1 = oldName.lastIndexOf('.') + 1;
        int idx2 = newName.lastIndexOf('.') + 1;
        if(idx1 != idx2)
            return false;
        if(!oldName.substring(0, idx1).equals(newName.substring(0, idx2)))
            return false;
        if(fields.containsKey(newName))
            return false;
        Item item = (Item)fields.get(oldName);
        if(item == null)
        {
            return false;
        } else
        {
            newName = newName.substring(idx2);
            PdfString ss = new PdfString(newName, "UnicodeBig");
            item.writeToAll(PdfName.T, ss, 5);
            item.markUsed(this, 4);
            fields.remove(oldName);
            fields.put(newName, item);
            return true;
        }
    }

    public static Object[] splitDAelements(String da)
    {
        try
        {
            PRTokeniser tk = new PRTokeniser(new RandomAccessFileOrArray((new RandomAccessSourceFactory()).createSource(PdfEncodings.convertToBytes(da, null))));
            ArrayList stack = new ArrayList();
            Object ret[] = new Object[3];
            do
            {
                if(!tk.nextToken())
                    break;
                if(tk.getTokenType() != PRTokeniser.TokenType.COMMENT)
                    if(tk.getTokenType() == PRTokeniser.TokenType.OTHER)
                    {
                        String operator = tk.getStringValue();
                        if(operator.equals("Tf"))
                        {
                            if(stack.size() >= 2)
                            {
                                ret[0] = stack.get(stack.size() - 2);
                                ret[1] = new Float((String)stack.get(stack.size() - 1));
                            }
                        } else
                        if(operator.equals("g"))
                        {
                            if(stack.size() >= 1)
                            {
                                float gray = (new Float((String)stack.get(stack.size() - 1))).floatValue();
                                if(gray != 0.0F)
                                    ret[2] = new GrayColor(gray);
                            }
                        } else
                        if(operator.equals("rg"))
                        {
                            if(stack.size() >= 3)
                            {
                                float red = (new Float((String)stack.get(stack.size() - 3))).floatValue();
                                float green = (new Float((String)stack.get(stack.size() - 2))).floatValue();
                                float blue = (new Float((String)stack.get(stack.size() - 1))).floatValue();
                                ret[2] = new BaseColor(red, green, blue);
                            }
                        } else
                        if(operator.equals("k") && stack.size() >= 4)
                        {
                            float cyan = (new Float((String)stack.get(stack.size() - 4))).floatValue();
                            float magenta = (new Float((String)stack.get(stack.size() - 3))).floatValue();
                            float yellow = (new Float((String)stack.get(stack.size() - 2))).floatValue();
                            float black = (new Float((String)stack.get(stack.size() - 1))).floatValue();
                            ret[2] = new CMYKColor(cyan, magenta, yellow, black);
                        }
                        stack.clear();
                    } else
                    {
                        stack.add(tk.getStringValue());
                    }
            } while(true);
            return ret;
        }
        catch(IOException ioe)
        {
            throw new ExceptionConverter(ioe);
        }
    }

    public void decodeGenericDictionary(PdfDictionary merged, BaseField tx)
        throws IOException, DocumentException
    {
        int flags = 0;
        PdfString da = merged.getAsString(PdfName.DA);
        if(da != null)
        {
            Object dab[] = splitDAelements(da.toUnicodeString());
            if(dab[1] != null)
                tx.setFontSize(((Float)dab[1]).floatValue());
            if(dab[2] != null)
                tx.setTextColor((BaseColor)dab[2]);
            if(dab[0] != null)
            {
                PdfDictionary font = merged.getAsDict(PdfName.DR);
                if(font != null)
                {
                    font = font.getAsDict(PdfName.FONT);
                    if(font != null)
                    {
                        PdfObject po = font.get(new PdfName((String)dab[0]));
                        if(po != null && po.type() == 10)
                        {
                            PRIndirectReference por = (PRIndirectReference)po;
                            BaseFont bp = new DocumentFont((PRIndirectReference)po);
                            tx.setFont(bp);
                            Integer porkey = Integer.valueOf(por.getNumber());
                            BaseFont porf = (BaseFont)extensionFonts.get(porkey);
                            if(porf == null && !extensionFonts.containsKey(porkey))
                            {
                                PdfDictionary fo = (PdfDictionary)PdfReader.getPdfObject(po);
                                PdfDictionary fd = fo.getAsDict(PdfName.FONTDESCRIPTOR);
                                if(fd != null)
                                {
                                    PRStream prs = (PRStream)PdfReader.getPdfObject(fd.get(PdfName.FONTFILE2));
                                    if(prs == null)
                                        prs = (PRStream)PdfReader.getPdfObject(fd.get(PdfName.FONTFILE3));
                                    if(prs == null)
                                    {
                                        extensionFonts.put(porkey, null);
                                    } else
                                    {
                                        try
                                        {
                                            porf = BaseFont.createFont("font.ttf", "Identity-H", true, false, PdfReader.getStreamBytes(prs), null);
                                        }
                                        catch(Exception e) { }
                                        extensionFonts.put(porkey, porf);
                                    }
                                }
                            }
                            if(tx instanceof TextField)
                                ((TextField)tx).setExtensionFont(porf);
                        } else
                        {
                            BaseFont bf = (BaseFont)localFonts.get(dab[0]);
                            if(bf == null)
                            {
                                String fn[] = (String[])stdFieldFontNames.get(dab[0]);
                                if(fn != null)
                                    try
                                    {
                                        String enc = "winansi";
                                        if(fn.length > 1)
                                            enc = fn[1];
                                        bf = BaseFont.createFont(fn[0], enc, false);
                                        tx.setFont(bf);
                                    }
                                    catch(Exception e) { }
                            } else
                            {
                                tx.setFont(bf);
                            }
                        }
                    }
                }
            }
        }
        PdfDictionary mk = merged.getAsDict(PdfName.MK);
        if(mk != null)
        {
            PdfArray ar = mk.getAsArray(PdfName.BC);
            BaseColor border = getMKColor(ar);
            tx.setBorderColor(border);
            if(border != null)
                tx.setBorderWidth(1.0F);
            ar = mk.getAsArray(PdfName.BG);
            tx.setBackgroundColor(getMKColor(ar));
            PdfNumber rotation = mk.getAsNumber(PdfName.R);
            if(rotation != null)
                tx.setRotation(rotation.intValue());
        }
        PdfNumber nfl = merged.getAsNumber(PdfName.F);
        flags = 0;
        tx.setVisibility(2);
        if(nfl != null)
        {
            flags = nfl.intValue();
            if((flags & 4) != 0 && (flags & 2) != 0)
                tx.setVisibility(1);
            else
            if((flags & 4) != 0 && (flags & 0x20) != 0)
                tx.setVisibility(3);
            else
            if((flags & 4) != 0)
                tx.setVisibility(0);
        }
        nfl = merged.getAsNumber(PdfName.FF);
        flags = 0;
        if(nfl != null)
            flags = nfl.intValue();
        tx.setOptions(flags);
        if((flags & 0x1000000) != 0)
        {
            PdfNumber maxLen = merged.getAsNumber(PdfName.MAXLEN);
            int len = 0;
            if(maxLen != null)
                len = maxLen.intValue();
            tx.setMaxCharacterLength(len);
        }
        nfl = merged.getAsNumber(PdfName.Q);
        if(nfl != null)
            if(nfl.intValue() == 1)
                tx.setAlignment(1);
            else
            if(nfl.intValue() == 2)
                tx.setAlignment(2);
        PdfDictionary bs = merged.getAsDict(PdfName.BS);
        if(bs != null)
        {
            PdfNumber w = bs.getAsNumber(PdfName.W);
            if(w != null)
                tx.setBorderWidth(w.floatValue());
            PdfName s = bs.getAsName(PdfName.S);
            if(PdfName.D.equals(s))
                tx.setBorderStyle(1);
            else
            if(PdfName.B.equals(s))
                tx.setBorderStyle(2);
            else
            if(PdfName.I.equals(s))
                tx.setBorderStyle(3);
            else
            if(PdfName.U.equals(s))
                tx.setBorderStyle(4);
        } else
        {
            PdfArray bd = merged.getAsArray(PdfName.BORDER);
            if(bd != null)
            {
                if(bd.size() >= 3)
                    tx.setBorderWidth(bd.getAsNumber(2).floatValue());
                if(bd.size() >= 4)
                    tx.setBorderStyle(1);
            }
        }
    }

    PdfAppearance getAppearance(PdfDictionary merged, String values[], String fieldName)
        throws IOException, DocumentException
    {
        topFirst = 0;
        String text = values.length <= 0 ? null : values[0];
        TextField tx = null;
        if(fieldCache == null || !fieldCache.containsKey(fieldName))
        {
            tx = new TextField(writer, null, null);
            tx.setExtraMargin(extraMarginLeft, extraMarginTop);
            tx.setBorderWidth(0.0F);
            tx.setSubstitutionFonts(substitutionFonts);
            decodeGenericDictionary(merged, tx);
            PdfArray rect = merged.getAsArray(PdfName.RECT);
            Rectangle box = PdfReader.getNormalizedRectangle(rect);
            if(tx.getRotation() == 90 || tx.getRotation() == 270)
                box = box.rotate();
            tx.setBox(box);
            if(fieldCache != null)
                fieldCache.put(fieldName, tx);
        } else
        {
            tx = (TextField)fieldCache.get(fieldName);
            tx.setWriter(writer);
        }
        PdfName fieldType = merged.getAsName(PdfName.FT);
        if(PdfName.TX.equals(fieldType))
        {
            if(values.length > 0 && values[0] != null)
                tx.setText(values[0]);
            return tx.getAppearance();
        }
        if(!PdfName.CH.equals(fieldType))
            throw new DocumentException(MessageLocalization.getComposedMessage("an.appearance.was.requested.without.a.variable.text.field", new Object[0]));
        PdfArray opt = merged.getAsArray(PdfName.OPT);
        int flags = 0;
        PdfNumber nfl = merged.getAsNumber(PdfName.FF);
        if(nfl != null)
            flags = nfl.intValue();
        if((flags & 0x20000) != 0 && opt == null)
        {
            tx.setText(text);
            return tx.getAppearance();
        }
        if(opt != null)
        {
            String choices[] = new String[opt.size()];
            String choicesExp[] = new String[opt.size()];
            for(int k = 0; k < opt.size(); k++)
            {
                PdfObject obj = opt.getPdfObject(k);
                if(obj.isString())
                {
                    choices[k] = choicesExp[k] = ((PdfString)obj).toUnicodeString();
                } else
                {
                    PdfArray a = (PdfArray)obj;
                    choicesExp[k] = a.getAsString(0).toUnicodeString();
                    choices[k] = a.getAsString(1).toUnicodeString();
                }
            }

            if((flags & 0x20000) != 0)
            {
                int k = 0;
                do
                {
                    if(k >= choices.length)
                        break;
                    if(text.equals(choicesExp[k]))
                    {
                        text = choices[k];
                        break;
                    }
                    k++;
                } while(true);
                tx.setText(text);
                return tx.getAppearance();
            }
            ArrayList indexes = new ArrayList();
label0:
            for(int k = 0; k < choicesExp.length; k++)
            {
                int j = 0;
                do
                {
                    if(j >= values.length)
                        continue label0;
                    String val = values[j];
                    if(val != null && val.equals(choicesExp[k]))
                    {
                        indexes.add(Integer.valueOf(k));
                        continue label0;
                    }
                    j++;
                } while(true);
            }

            tx.setChoices(choices);
            tx.setChoiceExports(choicesExp);
            tx.setChoiceSelections(indexes);
        }
        PdfAppearance app = tx.getListAppearance();
        topFirst = tx.getTopFirst();
        return app;
    }

    PdfAppearance getAppearance(PdfDictionary merged, String text, String fieldName)
        throws IOException, DocumentException
    {
        String valueArr[] = new String[1];
        valueArr[0] = text;
        return getAppearance(merged, valueArr, fieldName);
    }

    BaseColor getMKColor(PdfArray ar)
    {
        if(ar == null)
            return null;
        switch(ar.size())
        {
        case 1: // '\001'
            return new GrayColor(ar.getAsNumber(0).floatValue());

        case 3: // '\003'
            return new BaseColor(ExtendedColor.normalize(ar.getAsNumber(0).floatValue()), ExtendedColor.normalize(ar.getAsNumber(1).floatValue()), ExtendedColor.normalize(ar.getAsNumber(2).floatValue()));

        case 4: // '\004'
            return new CMYKColor(ar.getAsNumber(0).floatValue(), ar.getAsNumber(1).floatValue(), ar.getAsNumber(2).floatValue(), ar.getAsNumber(3).floatValue());

        case 2: // '\002'
        default:
            return null;
        }
    }

    public String getFieldRichValue(String name)
    {
        if(xfa.isXfaPresent())
            return null;
        Item item = (Item)fields.get(name);
        if(item == null)
            return null;
        PdfDictionary merged = item.getMerged(0);
        PdfString rich = merged.getAsString(PdfName.RV);
        String markup = null;
        if(rich != null)
            markup = rich.toString();
        return markup;
    }

    public String getField(String name)
    {
        if(xfa.isXfaPresent())
        {
            name = xfa.findFieldName(name, this);
            if(name == null)
            {
                return null;
            } else
            {
                name = XfaForm.Xml2Som.getShortName(name);
                return XfaForm.getNodeText(xfa.findDatasetsNode(name));
            }
        }
        Item item = (Item)fields.get(name);
        if(item == null)
            return null;
        lastWasString = false;
        PdfDictionary mergedDict = item.getMerged(0);
        PdfObject v = PdfReader.getPdfObject(mergedDict.get(PdfName.V));
        if(v == null)
            return "";
        if(v instanceof PRStream)
            try
            {
                byte valBytes[] = PdfReader.getStreamBytes((PRStream)v);
                return new String(valBytes);
            }
            catch(IOException e)
            {
                throw new ExceptionConverter(e);
            }
        PdfName type = mergedDict.getAsName(PdfName.FT);
        if(PdfName.BTN.equals(type))
        {
            PdfNumber ff = mergedDict.getAsNumber(PdfName.FF);
            int flags = 0;
            if(ff != null)
                flags = ff.intValue();
            if((flags & 0x10000) != 0)
                return "";
            String value = "";
            if(v instanceof PdfName)
                value = PdfName.decodeName(v.toString());
            else
            if(v instanceof PdfString)
                value = ((PdfString)v).toUnicodeString();
            PdfArray opts = item.getValue(0).getAsArray(PdfName.OPT);
            if(opts != null)
            {
                int idx = 0;
                try
                {
                    idx = Integer.parseInt(value);
                    PdfString ps = opts.getAsString(idx);
                    value = ps.toUnicodeString();
                    lastWasString = true;
                }
                catch(Exception e) { }
            }
            return value;
        }
        if(v instanceof PdfString)
        {
            lastWasString = true;
            return ((PdfString)v).toUnicodeString();
        }
        if(v instanceof PdfName)
            return PdfName.decodeName(v.toString());
        else
            return "";
    }

    public String[] getListSelection(String name)
    {
        String s = getField(name);
        String ret[];
        if(s == null)
            ret = new String[0];
        else
            ret = (new String[] {
                s
            });
        Item item = (Item)fields.get(name);
        if(item == null)
            return ret;
        PdfArray values = item.getMerged(0).getAsArray(PdfName.I);
        if(values == null)
            return ret;
        ret = new String[values.size()];
        String options[] = getListOptionExport(name);
        int idx = 0;
        for(Iterator i = values.listIterator(); i.hasNext();)
        {
            PdfNumber n = (PdfNumber)i.next();
            ret[idx++] = options[n.intValue()];
        }

        return ret;
    }

    public boolean setFieldProperty(String field, String name, Object value, int inst[])
    {
        if(writer == null)
            throw new RuntimeException(MessageLocalization.getComposedMessage("this.acrofields.instance.is.read.only", new Object[0]));
        Item item;
        InstHit hit;
        PdfDictionary merged;
        PdfString da;
        int k;
        PdfName dname;
        PdfDictionary dr;
        Object dao[];
        int k;
        Object dao[];
        PdfAppearance cb;
        PdfDictionary mk;
        PdfAppearance cb;
        ByteBuffer buf;
        BaseFont bf;
        PdfString s;
        PdfName psn;
        PdfDictionary fonts;
        PdfIndirectReference fref;
        PdfDictionary top;
        PdfDictionary fontsTop;
        PdfIndirectReference frefTop;
        FontDetails fd;
        ByteBuffer buf;
        PdfString s;
        try
        {
            item = (Item)fields.get(field);
            if(item == null)
                return false;
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
        hit = new InstHit(inst);
        if(name.equalsIgnoreCase("textfont"))
        {
            for(k = 0; k < item.size(); k++)
                if(hit.isHit(k))
                {
                    merged = item.getMerged(k);
                    da = merged.getAsString(PdfName.DA);
                    dr = merged.getAsDict(PdfName.DR);
                    if(da != null)
                    {
                        if(dr == null)
                        {
                            dr = new PdfDictionary();
                            merged.put(PdfName.DR, dr);
                        }
                        dao = splitDAelements(da.toUnicodeString());
                        cb = new PdfAppearance();
                        if(dao[0] != null)
                        {
                            bf = (BaseFont)value;
                            psn = (PdfName)PdfAppearance.stdFieldFontNames.get(bf.getPostscriptFontName());
                            if(psn == null)
                                psn = new PdfName(bf.getPostscriptFontName());
                            fonts = dr.getAsDict(PdfName.FONT);
                            if(fonts == null)
                            {
                                fonts = new PdfDictionary();
                                dr.put(PdfName.FONT, fonts);
                            }
                            fref = (PdfIndirectReference)fonts.get(psn);
                            top = reader.getCatalog().getAsDict(PdfName.ACROFORM);
                            markUsed(top);
                            dr = top.getAsDict(PdfName.DR);
                            if(dr == null)
                            {
                                dr = new PdfDictionary();
                                top.put(PdfName.DR, dr);
                            }
                            markUsed(dr);
                            fontsTop = dr.getAsDict(PdfName.FONT);
                            if(fontsTop == null)
                            {
                                fontsTop = new PdfDictionary();
                                dr.put(PdfName.FONT, fontsTop);
                            }
                            markUsed(fontsTop);
                            frefTop = (PdfIndirectReference)fontsTop.get(psn);
                            if(frefTop != null)
                            {
                                if(fref == null)
                                    fonts.put(psn, frefTop);
                            } else
                            if(fref == null)
                            {
                                if(bf.getFontType() == 4)
                                {
                                    fd = new FontDetails(null, ((DocumentFont)bf).getIndirectReference(), bf);
                                } else
                                {
                                    bf.setSubset(false);
                                    fd = writer.addSimple(bf);
                                    localFonts.put(psn.toString().substring(1), bf);
                                }
                                fontsTop.put(psn, fd.getIndirectReference());
                                fonts.put(psn, fd.getIndirectReference());
                            }
                            buf = cb.getInternalBuffer();
                            buf.append(psn.getBytes()).append(' ').append(((Float)dao[1]).floatValue()).append(" Tf ");
                            if(dao[2] != null)
                                cb.setColorFill((BaseColor)dao[2]);
                            s = new PdfString(cb.toString());
                            item.getMerged(k).put(PdfName.DA, s);
                            item.getWidget(k).put(PdfName.DA, s);
                            markUsed(item.getWidget(k));
                        }
                    }
                }

            break MISSING_BLOCK_LABEL_1238;
        }
        if(name.equalsIgnoreCase("textcolor"))
        {
            for(k = 0; k < item.size(); k++)
                if(hit.isHit(k))
                {
                    merged = item.getMerged(k);
                    da = merged.getAsString(PdfName.DA);
                    if(da != null)
                    {
                        dao = splitDAelements(da.toUnicodeString());
                        cb = new PdfAppearance();
                        if(dao[0] != null)
                        {
                            buf = cb.getInternalBuffer();
                            buf.append((new PdfName((String)dao[0])).getBytes()).append(' ').append(((Float)dao[1]).floatValue()).append(" Tf ");
                            cb.setColorFill((BaseColor)value);
                            s = new PdfString(cb.toString());
                            item.getMerged(k).put(PdfName.DA, s);
                            item.getWidget(k).put(PdfName.DA, s);
                            markUsed(item.getWidget(k));
                        }
                    }
                }

            break MISSING_BLOCK_LABEL_1238;
        }
        if(name.equalsIgnoreCase("textsize"))
        {
            for(k = 0; k < item.size(); k++)
                if(hit.isHit(k))
                {
                    merged = item.getMerged(k);
                    da = merged.getAsString(PdfName.DA);
                    if(da != null)
                    {
                        dao = splitDAelements(da.toUnicodeString());
                        cb = new PdfAppearance();
                        if(dao[0] != null)
                        {
                            buf = cb.getInternalBuffer();
                            buf.append((new PdfName((String)dao[0])).getBytes()).append(' ').append(((Float)value).floatValue()).append(" Tf ");
                            if(dao[2] != null)
                                cb.setColorFill((BaseColor)dao[2]);
                            s = new PdfString(cb.toString());
                            item.getMerged(k).put(PdfName.DA, s);
                            item.getWidget(k).put(PdfName.DA, s);
                            markUsed(item.getWidget(k));
                        }
                    }
                }

            break MISSING_BLOCK_LABEL_1238;
        }
        if(!name.equalsIgnoreCase("bgcolor") && !name.equalsIgnoreCase("bordercolor"))
            break MISSING_BLOCK_LABEL_1236;
        dname = name.equalsIgnoreCase("bgcolor") ? PdfName.BG : PdfName.BC;
        k = 0;
_L4:
        if(k >= item.size())
            break MISSING_BLOCK_LABEL_1238;
        if(!hit.isHit(k))
            break MISSING_BLOCK_LABEL_1227;
        merged = item.getMerged(k);
        mk = merged.getAsDict(PdfName.MK);
        if(mk != null) goto _L2; else goto _L1
_L1:
        if(value == null)
            return true;
        mk = new PdfDictionary();
        item.getMerged(k).put(PdfName.MK, mk);
        item.getWidget(k).put(PdfName.MK, mk);
        markUsed(item.getWidget(k));
          goto _L3
_L2:
        markUsed(mk);
_L3:
        if(value == null)
            mk.remove(dname);
        else
            mk.put(dname, PdfFormField.getMKColor((BaseColor)value));
        k++;
          goto _L4
        return false;
        return true;
    }

    public boolean setFieldProperty(String field, String name, int value, int inst[])
    {
        if(writer == null)
            throw new RuntimeException(MessageLocalization.getComposedMessage("this.acrofields.instance.is.read.only", new Object[0]));
        Item item = (Item)fields.get(field);
        if(item == null)
            return false;
        InstHit hit = new InstHit(inst);
        if(name.equalsIgnoreCase("flags"))
        {
            PdfNumber num = new PdfNumber(value);
            for(int k = 0; k < item.size(); k++)
                if(hit.isHit(k))
                {
                    item.getMerged(k).put(PdfName.F, num);
                    item.getWidget(k).put(PdfName.F, num);
                    markUsed(item.getWidget(k));
                }

        } else
        if(name.equalsIgnoreCase("setflags"))
        {
            for(int k = 0; k < item.size(); k++)
                if(hit.isHit(k))
                {
                    PdfNumber num = item.getWidget(k).getAsNumber(PdfName.F);
                    int val = 0;
                    if(num != null)
                        val = num.intValue();
                    num = new PdfNumber(val | value);
                    item.getMerged(k).put(PdfName.F, num);
                    item.getWidget(k).put(PdfName.F, num);
                    markUsed(item.getWidget(k));
                }

        } else
        if(name.equalsIgnoreCase("clrflags"))
        {
            for(int k = 0; k < item.size(); k++)
                if(hit.isHit(k))
                {
                    PdfDictionary widget = item.getWidget(k);
                    PdfNumber num = widget.getAsNumber(PdfName.F);
                    int val = 0;
                    if(num != null)
                        val = num.intValue();
                    num = new PdfNumber(val & ~value);
                    item.getMerged(k).put(PdfName.F, num);
                    widget.put(PdfName.F, num);
                    markUsed(widget);
                }

        } else
        if(name.equalsIgnoreCase("fflags"))
        {
            PdfNumber num = new PdfNumber(value);
            for(int k = 0; k < item.size(); k++)
                if(hit.isHit(k))
                {
                    item.getMerged(k).put(PdfName.FF, num);
                    item.getValue(k).put(PdfName.FF, num);
                    markUsed(item.getValue(k));
                }

        } else
        if(name.equalsIgnoreCase("setfflags"))
        {
            for(int k = 0; k < item.size(); k++)
                if(hit.isHit(k))
                {
                    PdfDictionary valDict = item.getValue(k);
                    PdfNumber num = valDict.getAsNumber(PdfName.FF);
                    int val = 0;
                    if(num != null)
                        val = num.intValue();
                    num = new PdfNumber(val | value);
                    item.getMerged(k).put(PdfName.FF, num);
                    valDict.put(PdfName.FF, num);
                    markUsed(valDict);
                }

        } else
        if(name.equalsIgnoreCase("clrfflags"))
        {
            for(int k = 0; k < item.size(); k++)
                if(hit.isHit(k))
                {
                    PdfDictionary valDict = item.getValue(k);
                    PdfNumber num = valDict.getAsNumber(PdfName.FF);
                    int val = 0;
                    if(num != null)
                        val = num.intValue();
                    num = new PdfNumber(val & ~value);
                    item.getMerged(k).put(PdfName.FF, num);
                    valDict.put(PdfName.FF, num);
                    markUsed(valDict);
                }

        } else
        {
            return false;
        }
        return true;
    }

    public void mergeXfaData(Node n)
        throws IOException, DocumentException
    {
        XfaForm.Xml2SomDatasets data = new XfaForm.Xml2SomDatasets(n);
        String name;
        String text;
        for(Iterator i$ = data.getOrder().iterator(); i$.hasNext(); setField(name, text))
        {
            String string = (String)i$.next();
            name = string;
            text = XfaForm.getNodeText((Node)data.getName2Node().get(name));
        }

    }

    public void setFields(FdfReader fdf)
        throws IOException, DocumentException
    {
        HashMap fd = fdf.getFields();
        Iterator i$ = fd.keySet().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            String f = (String)i$.next();
            String v = fdf.getFieldValue(f);
            if(v != null)
                setField(f, v);
        } while(true);
    }

    public void setFields(XfdfReader xfdf)
        throws IOException, DocumentException
    {
        HashMap fd = xfdf.getFields();
        Iterator i$ = fd.keySet().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            String f = (String)i$.next();
            String v = xfdf.getFieldValue(f);
            if(v != null)
                setField(f, v);
            List l = xfdf.getListValues(f);
            if(l != null)
                setListSelection(v, (String[])l.toArray(new String[l.size()]));
        } while(true);
    }

    public boolean regenerateField(String name)
        throws IOException, DocumentException
    {
        String value = getField(name);
        return setField(name, value, value);
    }

    public boolean setField(String name, String value)
        throws IOException, DocumentException
    {
        return setField(name, value, null);
    }

    public boolean setFieldRichValue(String name, String richValue)
        throws DocumentException, IOException
    {
        if(writer == null)
            throw new DocumentException(MessageLocalization.getComposedMessage("this.acrofields.instance.is.read.only", new Object[0]));
        Item item = getFieldItem(name);
        if(item == null)
            return false;
        if(getFieldType(name) != 4)
            return false;
        PdfDictionary merged = item.getMerged(0);
        PdfNumber ffNum = merged.getAsNumber(PdfName.FF);
        int flagVal = 0;
        if(ffNum != null)
            flagVal = ffNum.intValue();
        if((flagVal & 0x2000000) == 0)
        {
            return false;
        } else
        {
            PdfString richString = new PdfString(richValue);
            item.writeToAll(PdfName.RV, richString, 5);
            InputStream is = new ByteArrayInputStream(richValue.getBytes());
            PdfString valueString = new PdfString(XmlToTxt.parse(is));
            item.writeToAll(PdfName.V, valueString, 5);
            return true;
        }
    }

    public boolean setField(String name, String value, String display)
        throws IOException, DocumentException
    {
        if(writer == null)
            throw new DocumentException(MessageLocalization.getComposedMessage("this.acrofields.instance.is.read.only", new Object[0]));
        if(xfa.isXfaPresent())
        {
            name = xfa.findFieldName(name, this);
            if(name == null)
                return false;
            String shortName = XfaForm.Xml2Som.getShortName(name);
            Node xn = xfa.findDatasetsNode(shortName);
            if(xn == null)
                xn = xfa.getDatasetsSom().insertNode(xfa.getDatasetsNode(), shortName);
            xfa.setNodeText(xn, value);
        }
        Item item = (Item)fields.get(name);
        if(item == null)
            return false;
        PdfDictionary merged = item.getMerged(0);
        PdfName type = merged.getAsName(PdfName.FT);
        if(PdfName.TX.equals(type))
        {
            PdfNumber maxLen = merged.getAsNumber(PdfName.MAXLEN);
            int len = 0;
            if(maxLen != null)
                len = maxLen.intValue();
            if(len > 0)
                value = value.substring(0, Math.min(len, value.length()));
        }
        if(display == null)
            display = value;
        if(PdfName.TX.equals(type) || PdfName.CH.equals(type))
        {
            PdfString v = new PdfString(value, "UnicodeBig");
            for(int idx = 0; idx < item.size(); idx++)
            {
                PdfDictionary valueDic = item.getValue(idx);
                valueDic.put(PdfName.V, v);
                valueDic.remove(PdfName.I);
                markUsed(valueDic);
                merged = item.getMerged(idx);
                merged.remove(PdfName.I);
                merged.put(PdfName.V, v);
                PdfDictionary widget = item.getWidget(idx);
                if(generateAppearances)
                {
                    PdfAppearance app = getAppearance(merged, display, name);
                    if(PdfName.CH.equals(type))
                    {
                        PdfNumber n = new PdfNumber(topFirst);
                        widget.put(PdfName.TI, n);
                        merged.put(PdfName.TI, n);
                    }
                    PdfDictionary appDic = widget.getAsDict(PdfName.AP);
                    if(appDic == null)
                    {
                        appDic = new PdfDictionary();
                        widget.put(PdfName.AP, appDic);
                        merged.put(PdfName.AP, appDic);
                    }
                    appDic.put(PdfName.N, app.getIndirectReference());
                    writer.releaseTemplate(app);
                } else
                {
                    widget.remove(PdfName.AP);
                    merged.remove(PdfName.AP);
                }
                markUsed(widget);
            }

            return true;
        }
        if(PdfName.BTN.equals(type))
        {
            PdfNumber ff = item.getMerged(0).getAsNumber(PdfName.FF);
            int flags = 0;
            if(ff != null)
                flags = ff.intValue();
            if((flags & 0x10000) != 0)
            {
                Image img;
                try
                {
                    img = Image.getInstance(Base64.decode(value));
                }
                catch(Exception e)
                {
                    return false;
                }
                PushbuttonField pb = getNewPushbuttonFromField(name);
                pb.setImage(img);
                replacePushbuttonField(name, pb.getField());
                return true;
            }
            PdfName v = new PdfName(value);
            ArrayList lopt = new ArrayList();
            PdfArray opts = item.getValue(0).getAsArray(PdfName.OPT);
            if(opts != null)
            {
                for(int k = 0; k < opts.size(); k++)
                {
                    PdfString valStr = opts.getAsString(k);
                    if(valStr != null)
                        lopt.add(valStr.toUnicodeString());
                    else
                        lopt.add(null);
                }

            }
            int vidx = lopt.indexOf(value);
            PdfName vt;
            if(vidx >= 0)
                vt = new PdfName(String.valueOf(vidx));
            else
                vt = v;
            for(int idx = 0; idx < item.size(); idx++)
            {
                merged = item.getMerged(idx);
                PdfDictionary widget = item.getWidget(idx);
                PdfDictionary valDict = item.getValue(idx);
                markUsed(item.getValue(idx));
                valDict.put(PdfName.V, vt);
                merged.put(PdfName.V, vt);
                markUsed(widget);
                if(isInAP(widget, vt))
                {
                    merged.put(PdfName.AS, vt);
                    widget.put(PdfName.AS, vt);
                } else
                {
                    merged.put(PdfName.AS, PdfName.Off);
                    widget.put(PdfName.AS, PdfName.Off);
                }
            }

            return true;
        } else
        {
            return false;
        }
    }

    public boolean setListSelection(String name, String value[])
        throws IOException, DocumentException
    {
        Item item = getFieldItem(name);
        if(item == null)
            return false;
        PdfDictionary merged = item.getMerged(0);
        PdfName type = merged.getAsName(PdfName.FT);
        if(!PdfName.CH.equals(type))
            return false;
        String options[] = getListOptionExport(name);
        PdfArray array = new PdfArray();
        String arr$[] = value;
        int len$ = arr$.length;
label0:
        for(int i$ = 0; i$ < len$; i$++)
        {
            String element = arr$[i$];
            int j = 0;
            do
            {
                if(j >= options.length)
                    continue label0;
                if(options[j].equals(element))
                {
                    array.add(new PdfNumber(j));
                    continue label0;
                }
                j++;
            } while(true);
        }

        item.writeToAll(PdfName.I, array, 5);
        PdfArray vals = new PdfArray();
        for(int i = 0; i < value.length; i++)
            vals.add(new PdfString(value[i]));

        item.writeToAll(PdfName.V, vals, 5);
        PdfAppearance app = getAppearance(merged, value, name);
        PdfDictionary apDic = new PdfDictionary();
        apDic.put(PdfName.N, app.getIndirectReference());
        item.writeToAll(PdfName.AP, apDic, 3);
        writer.releaseTemplate(app);
        item.markUsed(this, 6);
        return true;
    }

    boolean isInAP(PdfDictionary dic, PdfName check)
    {
        PdfDictionary appDic = dic.getAsDict(PdfName.AP);
        if(appDic == null)
        {
            return false;
        } else
        {
            PdfDictionary NDic = appDic.getAsDict(PdfName.N);
            return NDic != null && NDic.get(check) != null;
        }
    }

    public Map getFields()
    {
        return fields;
    }

    public Item getFieldItem(String name)
    {
        if(xfa.isXfaPresent())
        {
            name = xfa.findFieldName(name, this);
            if(name == null)
                return null;
        }
        return (Item)fields.get(name);
    }

    public String getTranslatedFieldName(String name)
    {
        if(xfa.isXfaPresent())
        {
            String namex = xfa.findFieldName(name, this);
            if(namex != null)
                name = namex;
        }
        return name;
    }

    public List getFieldPositions(String name)
    {
        Item item;
        ArrayList ret;
        int k;
        item = getFieldItem(name);
        if(item == null)
            return null;
        ret = new ArrayList();
        k = 0;
_L3:
        if(k >= item.size()) goto _L2; else goto _L1
_L1:
        PdfDictionary wd = item.getWidget(k);
        PdfArray rect = wd.getAsArray(PdfName.RECT);
        if(rect == null)
            continue; /* Loop/switch isn't completed */
        try
        {
            Rectangle r = PdfReader.getNormalizedRectangle(rect);
            int page = item.getPage(k).intValue();
            int rotation = reader.getPageRotation(page);
            FieldPosition fp = new FieldPosition();
            fp.page = page;
            if(rotation != 0)
            {
                Rectangle pageSize = reader.getPageSize(page);
                switch(rotation)
                {
                case 270: 
                    r = new Rectangle(pageSize.getTop() - r.getBottom(), r.getLeft(), pageSize.getTop() - r.getTop(), r.getRight());
                    break;

                case 180: 
                    r = new Rectangle(pageSize.getRight() - r.getLeft(), pageSize.getTop() - r.getBottom(), pageSize.getRight() - r.getRight(), pageSize.getTop() - r.getTop());
                    break;

                case 90: // 'Z'
                    r = new Rectangle(r.getBottom(), pageSize.getRight() - r.getLeft(), r.getTop(), pageSize.getRight() - r.getRight());
                    break;
                }
                r.normalize();
            }
            fp.position = r;
            ret.add(fp);
        }
        catch(Exception e) { }
        k++;
          goto _L3
_L2:
        return ret;
    }

    private int removeRefFromArray(PdfArray array, PdfObject refo)
    {
        if(refo == null || !refo.isIndirect())
            return array.size();
        PdfIndirectReference ref = (PdfIndirectReference)refo;
        for(int j = 0; j < array.size(); j++)
        {
            PdfObject obj = array.getPdfObject(j);
            if(obj.isIndirect() && ((PdfIndirectReference)obj).getNumber() == ref.getNumber())
                array.remove(j--);
        }

        return array.size();
    }

    public boolean removeFieldsFromPage(int page)
    {
        if(page < 1)
            return false;
        String names[] = new String[fields.size()];
        fields.keySet().toArray(names);
        boolean found = false;
        for(int k = 0; k < names.length; k++)
        {
            boolean fr = removeField(names[k], page);
            found = found || fr;
        }

        return found;
    }

    public boolean removeField(String name, int page)
    {
        Item item = getFieldItem(name);
        if(item == null)
            return false;
        PdfDictionary acroForm = (PdfDictionary)PdfReader.getPdfObject(reader.getCatalog().get(PdfName.ACROFORM), reader.getCatalog());
        if(acroForm == null)
            return false;
        PdfArray arrayf = acroForm.getAsArray(PdfName.FIELDS);
        if(arrayf == null)
            return false;
        for(int k = 0; k < item.size(); k++)
        {
            int pageV = item.getPage(k).intValue();
            if(page != -1 && page != pageV)
                continue;
            PdfIndirectReference ref = item.getWidgetRef(k);
            PdfDictionary wd = item.getWidget(k);
            PdfDictionary pageDic = reader.getPageN(pageV);
            PdfArray annots = pageDic.getAsArray(PdfName.ANNOTS);
            if(annots != null)
                if(removeRefFromArray(annots, ref) == 0)
                {
                    pageDic.remove(PdfName.ANNOTS);
                    markUsed(pageDic);
                } else
                {
                    markUsed(annots);
                }
            PdfReader.killIndirect(ref);
            PdfIndirectReference kid = ref;
            do
            {
                if((ref = wd.getAsIndirectObject(PdfName.PARENT)) == null)
                    break;
                wd = wd.getAsDict(PdfName.PARENT);
                PdfArray kids = wd.getAsArray(PdfName.KIDS);
                if(removeRefFromArray(kids, kid) != 0)
                    break;
                kid = ref;
                PdfReader.killIndirect(ref);
            } while(true);
            if(ref == null)
            {
                removeRefFromArray(arrayf, kid);
                markUsed(arrayf);
            }
            if(page != -1)
            {
                item.remove(k);
                k--;
            }
        }

        if(page == -1 || item.size() == 0)
            fields.remove(name);
        return true;
    }

    public boolean removeField(String name)
    {
        return removeField(name, -1);
    }

    public boolean isGenerateAppearances()
    {
        return generateAppearances;
    }

    public void setGenerateAppearances(boolean generateAppearances)
    {
        this.generateAppearances = generateAppearances;
        PdfDictionary top = reader.getCatalog().getAsDict(PdfName.ACROFORM);
        if(generateAppearances)
            top.remove(PdfName.NEEDAPPEARANCES);
        else
            top.put(PdfName.NEEDAPPEARANCES, PdfBoolean.PDFTRUE);
    }

    public boolean clearSignatureField(String name)
    {
        sigNames = null;
        getSignatureNames();
        if(!sigNames.containsKey(name))
            return false;
        Item sig = (Item)fields.get(name);
        sig.markUsed(this, 6);
        int n = sig.size();
        for(int k = 0; k < n; k++)
        {
            clearSigDic(sig.getMerged(k));
            clearSigDic(sig.getWidget(k));
            clearSigDic(sig.getValue(k));
        }

        return true;
    }

    private static void clearSigDic(PdfDictionary dic)
    {
        dic.remove(PdfName.AP);
        dic.remove(PdfName.AS);
        dic.remove(PdfName.V);
        dic.remove(PdfName.DV);
        dic.remove(PdfName.SV);
        dic.remove(PdfName.FF);
        dic.put(PdfName.F, new PdfNumber(4));
    }

    public ArrayList getSignatureNames()
    {
        if(sigNames != null)
            return new ArrayList(orderedSignatureNames);
        sigNames = new HashMap();
        orderedSignatureNames = new ArrayList();
        ArrayList sorter = new ArrayList();
        Iterator i$ = fields.entrySet().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            Item item = (Item)entry.getValue();
            PdfDictionary merged = item.getMerged(0);
            if(PdfName.SIG.equals(merged.get(PdfName.FT)))
            {
                PdfDictionary v = merged.getAsDict(PdfName.V);
                if(v != null)
                {
                    PdfString contents = v.getAsString(PdfName.CONTENTS);
                    if(contents != null)
                    {
                        PdfArray ro = v.getAsArray(PdfName.BYTERANGE);
                        if(ro != null)
                        {
                            int rangeSize = ro.size();
                            if(rangeSize >= 2)
                            {
                                int length = ro.getAsNumber(rangeSize - 1).intValue() + ro.getAsNumber(rangeSize - 2).intValue();
                                sorter.add(((Object) (new Object[] {
                                    entry.getKey(), new int[] {
                                        length, 0
                                    }
                                })));
                            }
                        }
                    }
                }
            }
        } while(true);
        Collections.sort(sorter, new SorterComparator());
        if(!sorter.isEmpty())
        {
            if((long)((int[])(int[])((Object[])sorter.get(sorter.size() - 1))[1])[0] == reader.getFileLength())
                totalRevisions = sorter.size();
            else
                totalRevisions = sorter.size() + 1;
            for(int k = 0; k < sorter.size(); k++)
            {
                Object objs[] = (Object[])sorter.get(k);
                String name = (String)objs[0];
                int p[] = (int[])(int[])objs[1];
                p[1] = k + 1;
                sigNames.put(name, p);
                orderedSignatureNames.add(name);
            }

        }
        return new ArrayList(orderedSignatureNames);
    }

    public ArrayList getBlankSignatureNames()
    {
        getSignatureNames();
        ArrayList sigs = new ArrayList();
        Iterator i$ = fields.entrySet().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            Item item = (Item)entry.getValue();
            PdfDictionary merged = item.getMerged(0);
            if(PdfName.SIG.equals(merged.getAsName(PdfName.FT)) && !sigNames.containsKey(entry.getKey()))
                sigs.add(entry.getKey());
        } while(true);
        return sigs;
    }

    public PdfDictionary getSignatureDictionary(String name)
    {
        getSignatureNames();
        name = getTranslatedFieldName(name);
        if(!sigNames.containsKey(name))
        {
            return null;
        } else
        {
            Item item = (Item)fields.get(name);
            PdfDictionary merged = item.getMerged(0);
            return merged.getAsDict(PdfName.V);
        }
    }

    public PdfIndirectReference getNormalAppearance(String name)
    {
        getSignatureNames();
        name = getTranslatedFieldName(name);
        Item item = (Item)fields.get(name);
        if(item == null)
            return null;
        PdfDictionary merged = item.getMerged(0);
        PdfDictionary ap = merged.getAsDict(PdfName.AP);
        if(ap == null)
            return null;
        PdfIndirectReference ref = ap.getAsIndirectObject(PdfName.N);
        if(ref == null)
            return null;
        else
            return ref;
    }

    public boolean signatureCoversWholeDocument(String name)
    {
        getSignatureNames();
        name = getTranslatedFieldName(name);
        if(!sigNames.containsKey(name))
            return false;
        else
            return (long)((int[])sigNames.get(name))[0] == reader.getFileLength();
    }

    public PdfPKCS7 verifySignature(String name)
    {
        return verifySignature(name, null);
    }

    public PdfPKCS7 verifySignature(String name, String provider)
    {
        PdfDictionary v = getSignatureDictionary(name);
        if(v == null)
            return null;
        try
        {
            PdfName sub = v.getAsName(PdfName.SUBFILTER);
            PdfString contents = v.getAsString(PdfName.CONTENTS);
            PdfPKCS7 pk = null;
            if(sub.equals(PdfName.ADBE_X509_RSA_SHA1))
            {
                PdfString cert = v.getAsString(PdfName.CERT);
                if(cert == null)
                    cert = v.getAsArray(PdfName.CERT).getAsString(0);
                pk = new PdfPKCS7(contents.getOriginalBytes(), cert.getBytes(), provider);
            } else
            {
                pk = new PdfPKCS7(contents.getOriginalBytes(), sub, provider);
            }
            updateByteRange(pk, v);
            PdfString str = v.getAsString(PdfName.M);
            if(str != null)
                pk.setSignDate(PdfDate.decode(str.toString()));
            PdfObject obj = PdfReader.getPdfObject(v.get(PdfName.NAME));
            if(obj != null)
                if(obj.isString())
                    pk.setSignName(((PdfString)obj).toUnicodeString());
                else
                if(obj.isName())
                    pk.setSignName(PdfName.decodeName(obj.toString()));
            str = v.getAsString(PdfName.REASON);
            if(str != null)
                pk.setReason(str.toUnicodeString());
            str = v.getAsString(PdfName.LOCATION);
            if(str != null)
                pk.setLocation(str.toUnicodeString());
            return pk;
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    private void updateByteRange(PdfPKCS7 pkcs7, PdfDictionary v)
    {
        PdfArray b;
        RandomAccessFileOrArray rf;
        InputStream rg;
        b = v.getAsArray(PdfName.BYTERANGE);
        rf = reader.getSafeFile();
        rg = null;
        try
        {
            rg = new RASInputStream((new RandomAccessSourceFactory()).createRanged(rf.createSourceView(), b.asLongArray()));
            byte buf[] = new byte[8192];
            int rd;
            while((rd = rg.read(buf, 0, buf.length)) > 0) 
                pkcs7.update(buf, 0, rd);
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
        try
        {
            if(rg != null)
                rg.close();
        }
        catch(IOException e)
        {
            throw new ExceptionConverter(e);
        }
        break MISSING_BLOCK_LABEL_151;
        Exception exception;
        exception;
        try
        {
            if(rg != null)
                rg.close();
        }
        catch(IOException e)
        {
            throw new ExceptionConverter(e);
        }
        throw exception;
    }

    private void markUsed(PdfObject obj)
    {
        if(!append)
        {
            return;
        } else
        {
            ((PdfStamperImp)writer).markUsed(obj);
            return;
        }
    }

    public int getTotalRevisions()
    {
        getSignatureNames();
        return totalRevisions;
    }

    public int getRevision(String field)
    {
        getSignatureNames();
        field = getTranslatedFieldName(field);
        if(!sigNames.containsKey(field))
            return 0;
        else
            return ((int[])sigNames.get(field))[1];
    }

    public InputStream extractRevision(String field)
        throws IOException
    {
        getSignatureNames();
        field = getTranslatedFieldName(field);
        if(!sigNames.containsKey(field))
        {
            return null;
        } else
        {
            int length = ((int[])sigNames.get(field))[0];
            RandomAccessFileOrArray raf = reader.getSafeFile();
            return new RASInputStream(new WindowRandomAccessSource(raf.createSourceView(), 0L, length));
        }
    }

    public Map getFieldCache()
    {
        return fieldCache;
    }

    public void setFieldCache(Map fieldCache)
    {
        this.fieldCache = fieldCache;
    }

    public void setExtraMargin(float extraMarginLeft, float extraMarginTop)
    {
        this.extraMarginLeft = extraMarginLeft;
        this.extraMarginTop = extraMarginTop;
    }

    public void addSubstitutionFont(BaseFont font)
    {
        if(substitutionFonts == null)
            substitutionFonts = new ArrayList();
        substitutionFonts.add(font);
    }

    public ArrayList getSubstitutionFonts()
    {
        return substitutionFonts;
    }

    public void setSubstitutionFonts(ArrayList substitutionFonts)
    {
        this.substitutionFonts = substitutionFonts;
    }

    public XfaForm getXfa()
    {
        return xfa;
    }

    public void removeXfa()
    {
        PdfDictionary root = reader.getCatalog();
        PdfDictionary acroform = root.getAsDict(PdfName.ACROFORM);
        acroform.remove(PdfName.XFA);
        try
        {
            xfa = new XfaForm(reader);
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public PushbuttonField getNewPushbuttonFromField(String field)
    {
        return getNewPushbuttonFromField(field, 0);
    }

    public PushbuttonField getNewPushbuttonFromField(String field, int order)
    {
        Item item;
        try
        {
            if(getFieldType(field) != 1)
                return null;
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
        item = getFieldItem(field);
        if(order >= item.size())
            return null;
        List pos = getFieldPositions(field);
        Rectangle box = ((FieldPosition)pos.get(order)).position;
        PushbuttonField newButton = new PushbuttonField(writer, box, null);
        PdfDictionary dic = item.getMerged(order);
        decodeGenericDictionary(dic, newButton);
        PdfDictionary mk = dic.getAsDict(PdfName.MK);
        if(mk != null)
        {
            PdfString text = mk.getAsString(PdfName.CA);
            if(text != null)
                newButton.setText(text.toUnicodeString());
            PdfNumber tp = mk.getAsNumber(PdfName.TP);
            if(tp != null)
                newButton.setLayout(tp.intValue() + 1);
            PdfDictionary ifit = mk.getAsDict(PdfName.IF);
            if(ifit != null)
            {
                PdfName sw = ifit.getAsName(PdfName.SW);
                if(sw != null)
                {
                    int scale = 1;
                    if(sw.equals(PdfName.B))
                        scale = 3;
                    else
                    if(sw.equals(PdfName.S))
                        scale = 4;
                    else
                    if(sw.equals(PdfName.N))
                        scale = 2;
                    newButton.setScaleIcon(scale);
                }
                sw = ifit.getAsName(PdfName.S);
                if(sw != null && sw.equals(PdfName.A))
                    newButton.setProportionalIcon(false);
                PdfArray aj = ifit.getAsArray(PdfName.A);
                if(aj != null && aj.size() == 2)
                {
                    float left = aj.getAsNumber(0).floatValue();
                    float bottom = aj.getAsNumber(1).floatValue();
                    newButton.setIconHorizontalAdjustment(left);
                    newButton.setIconVerticalAdjustment(bottom);
                }
                PdfBoolean fb = ifit.getAsBoolean(PdfName.FB);
                if(fb != null && fb.booleanValue())
                    newButton.setIconFitToBounds(true);
            }
            PdfObject i = mk.get(PdfName.I);
            if(i != null && i.isIndirect())
                newButton.setIconReference((PRIndirectReference)i);
        }
        return newButton;
    }

    public boolean replacePushbuttonField(String field, PdfFormField button)
    {
        return replacePushbuttonField(field, button, 0);
    }

    public boolean replacePushbuttonField(String field, PdfFormField button, int order)
    {
        if(getFieldType(field) != 1)
            return false;
        Item item = getFieldItem(field);
        if(order >= item.size())
            return false;
        PdfDictionary merged = item.getMerged(order);
        PdfDictionary values = item.getValue(order);
        PdfDictionary widgets = item.getWidget(order);
        for(int k = 0; k < buttonRemove.length; k++)
        {
            merged.remove(buttonRemove[k]);
            values.remove(buttonRemove[k]);
            widgets.remove(buttonRemove[k]);
        }

        Iterator i$ = button.getKeys().iterator();
        do
        {
            if(!i$.hasNext())
                break;
            Object element = i$.next();
            PdfName key = (PdfName)element;
            if(!key.equals(PdfName.T) && !key.equals(PdfName.RECT))
            {
                if(key.equals(PdfName.FF))
                    values.put(key, button.get(key));
                else
                    widgets.put(key, button.get(key));
                merged.put(key, button.get(key));
                markUsed(values);
                markUsed(widgets);
            }
        } while(true);
        return true;
    }

    PdfReader reader;
    PdfWriter writer;
    Map fields;
    private int topFirst;
    private HashMap sigNames;
    private boolean append;
    public static final int DA_FONT = 0;
    public static final int DA_SIZE = 1;
    public static final int DA_COLOR = 2;
    private HashMap extensionFonts;
    private XfaForm xfa;
    public static final int FIELD_TYPE_NONE = 0;
    public static final int FIELD_TYPE_PUSHBUTTON = 1;
    public static final int FIELD_TYPE_CHECKBOX = 2;
    public static final int FIELD_TYPE_RADIOBUTTON = 3;
    public static final int FIELD_TYPE_TEXT = 4;
    public static final int FIELD_TYPE_LIST = 5;
    public static final int FIELD_TYPE_COMBO = 6;
    public static final int FIELD_TYPE_SIGNATURE = 7;
    private boolean lastWasString;
    private boolean generateAppearances;
    private HashMap localFonts;
    private float extraMarginLeft;
    private float extraMarginTop;
    private ArrayList substitutionFonts;
    private ArrayList orderedSignatureNames;
    private static final HashMap stdFieldFontNames;
    private int totalRevisions;
    private Map fieldCache;
    private static final PdfName buttonRemove[];

    static 
    {
        stdFieldFontNames = new HashMap();
        stdFieldFontNames.put("CoBO", new String[] {
            "Courier-BoldOblique"
        });
        stdFieldFontNames.put("CoBo", new String[] {
            "Courier-Bold"
        });
        stdFieldFontNames.put("CoOb", new String[] {
            "Courier-Oblique"
        });
        stdFieldFontNames.put("Cour", new String[] {
            "Courier"
        });
        stdFieldFontNames.put("HeBO", new String[] {
            "Helvetica-BoldOblique"
        });
        stdFieldFontNames.put("HeBo", new String[] {
            "Helvetica-Bold"
        });
        stdFieldFontNames.put("HeOb", new String[] {
            "Helvetica-Oblique"
        });
        stdFieldFontNames.put("Helv", new String[] {
            "Helvetica"
        });
        stdFieldFontNames.put("Symb", new String[] {
            "Symbol"
        });
        stdFieldFontNames.put("TiBI", new String[] {
            "Times-BoldItalic"
        });
        stdFieldFontNames.put("TiBo", new String[] {
            "Times-Bold"
        });
        stdFieldFontNames.put("TiIt", new String[] {
            "Times-Italic"
        });
        stdFieldFontNames.put("TiRo", new String[] {
            "Times-Roman"
        });
        stdFieldFontNames.put("ZaDb", new String[] {
            "ZapfDingbats"
        });
        stdFieldFontNames.put("HySm", new String[] {
            "HYSMyeongJo-Medium", "UniKS-UCS2-H"
        });
        stdFieldFontNames.put("HyGo", new String[] {
            "HYGoThic-Medium", "UniKS-UCS2-H"
        });
        stdFieldFontNames.put("KaGo", new String[] {
            "HeiseiKakuGo-W5", "UniKS-UCS2-H"
        });
        stdFieldFontNames.put("KaMi", new String[] {
            "HeiseiMin-W3", "UniJIS-UCS2-H"
        });
        stdFieldFontNames.put("MHei", new String[] {
            "MHei-Medium", "UniCNS-UCS2-H"
        });
        stdFieldFontNames.put("MSun", new String[] {
            "MSung-Light", "UniCNS-UCS2-H"
        });
        stdFieldFontNames.put("STSo", new String[] {
            "STSong-Light", "UniGB-UCS2-H"
        });
        buttonRemove = (new PdfName[] {
            PdfName.MK, PdfName.F, PdfName.FF, PdfName.Q, PdfName.BS, PdfName.BORDER
        });
    }

}
