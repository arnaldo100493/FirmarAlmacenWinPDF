// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FieldPositioningEvents.java

package co.com.pdf.text.pdf.events;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.*;
import java.io.IOException;
import java.util.HashMap;

public class FieldPositioningEvents extends PdfPageEventHelper
    implements PdfPCellEvent
{

    public FieldPositioningEvents()
    {
        genericChunkFields = new HashMap();
        cellField = null;
        fieldWriter = null;
        parent = null;
    }

    public void addField(String text, PdfFormField field)
    {
        genericChunkFields.put(text, field);
    }

    public FieldPositioningEvents(PdfWriter writer, PdfFormField field)
    {
        genericChunkFields = new HashMap();
        cellField = null;
        fieldWriter = null;
        parent = null;
        cellField = field;
        fieldWriter = writer;
    }

    public FieldPositioningEvents(PdfFormField parent, PdfFormField field)
    {
        genericChunkFields = new HashMap();
        cellField = null;
        fieldWriter = null;
        this.parent = null;
        cellField = field;
        this.parent = parent;
    }

    public FieldPositioningEvents(PdfWriter writer, String text)
        throws IOException, DocumentException
    {
        genericChunkFields = new HashMap();
        cellField = null;
        fieldWriter = null;
        parent = null;
        fieldWriter = writer;
        TextField tf = new TextField(writer, new Rectangle(0.0F, 0.0F), text);
        tf.setFontSize(14F);
        cellField = tf.getTextField();
    }

    public FieldPositioningEvents(PdfWriter writer, PdfFormField parent, String text)
        throws IOException, DocumentException
    {
        genericChunkFields = new HashMap();
        cellField = null;
        fieldWriter = null;
        this.parent = null;
        this.parent = parent;
        TextField tf = new TextField(writer, new Rectangle(0.0F, 0.0F), text);
        tf.setFontSize(14F);
        cellField = tf.getTextField();
    }

    public void setPadding(float padding)
    {
        this.padding = padding;
    }

    public void setParent(PdfFormField parent)
    {
        this.parent = parent;
    }

    public void onGenericTag(PdfWriter writer, Document document, Rectangle rect, String text)
    {
        rect.setBottom(rect.getBottom() - 3F);
        PdfFormField field = (PdfFormField)genericChunkFields.get(text);
        if(field == null)
        {
            TextField tf = new TextField(writer, new Rectangle(rect.getLeft(padding), rect.getBottom(padding), rect.getRight(padding), rect.getTop(padding)), text);
            tf.setFontSize(14F);
            try
            {
                field = tf.getTextField();
            }
            catch(Exception e)
            {
                throw new ExceptionConverter(e);
            }
        } else
        {
            field.put(PdfName.RECT, new PdfRectangle(rect.getLeft(padding), rect.getBottom(padding), rect.getRight(padding), rect.getTop(padding)));
        }
        if(parent == null)
            writer.addAnnotation(field);
        else
            parent.addKid(field);
    }

    public void cellLayout(PdfPCell cell, Rectangle rect, PdfContentByte canvases[])
    {
        if(cellField == null || fieldWriter == null && parent == null)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("you.have.used.the.wrong.constructor.for.this.fieldpositioningevents.class", new Object[0]));
        cellField.put(PdfName.RECT, new PdfRectangle(rect.getLeft(padding), rect.getBottom(padding), rect.getRight(padding), rect.getTop(padding)));
        if(parent == null)
            fieldWriter.addAnnotation(cellField);
        else
            parent.addKid(cellField);
    }

    protected HashMap genericChunkFields;
    protected PdfFormField cellField;
    protected PdfWriter fieldWriter;
    protected PdfFormField parent;
    public float padding;
}
