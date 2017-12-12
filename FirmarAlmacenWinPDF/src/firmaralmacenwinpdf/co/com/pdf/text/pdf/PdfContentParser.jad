// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentParser.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.IOException;
import java.util.ArrayList;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfName, PdfArray, PdfString, 
//            PdfNumber, PdfLiteral, PdfObject, PRTokeniser

public class PdfContentParser
{

    public PdfContentParser(PRTokeniser tokeniser)
    {
        this.tokeniser = tokeniser;
    }

    public ArrayList parse(ArrayList ls)
        throws IOException
    {
        if(ls == null)
            ls = new ArrayList();
        else
            ls.clear();
        PdfObject ob = null;
        do
        {
            if((ob = readPRObject()) == null)
                break;
            ls.add(ob);
        } while(ob.type() != 200);
        return ls;
    }

    public PRTokeniser getTokeniser()
    {
        return tokeniser;
    }

    public void setTokeniser(PRTokeniser tokeniser)
    {
        this.tokeniser = tokeniser;
    }

    public PdfDictionary readDictionary()
        throws IOException
    {
        PdfDictionary dic = new PdfDictionary();
        do
        {
            if(!nextValidToken())
                throw new IOException(MessageLocalization.getComposedMessage("unexpected.end.of.file", new Object[0]));
            if(tokeniser.getTokenType() == PRTokeniser.TokenType.END_DIC)
                break;
            if(tokeniser.getTokenType() != PRTokeniser.TokenType.OTHER || !"def".equals(tokeniser.getStringValue()))
            {
                if(tokeniser.getTokenType() != PRTokeniser.TokenType.NAME)
                    throw new IOException(MessageLocalization.getComposedMessage("dictionary.key.1.is.not.a.name", new Object[] {
                        tokeniser.getStringValue()
                    }));
                PdfName name = new PdfName(tokeniser.getStringValue(), false);
                PdfObject obj = readPRObject();
                int type = obj.type();
                if(-type == PRTokeniser.TokenType.END_DIC.ordinal())
                    throw new IOException(MessageLocalization.getComposedMessage("unexpected.gt.gt", new Object[0]));
                if(-type == PRTokeniser.TokenType.END_ARRAY.ordinal())
                    throw new IOException(MessageLocalization.getComposedMessage("unexpected.close.bracket", new Object[0]));
                dic.put(name, obj);
            }
        } while(true);
        return dic;
    }

    public PdfArray readArray()
        throws IOException
    {
        PdfArray array = new PdfArray();
        do
        {
            PdfObject obj = readPRObject();
            int type = obj.type();
            if(-type != PRTokeniser.TokenType.END_ARRAY.ordinal())
            {
                if(-type == PRTokeniser.TokenType.END_DIC.ordinal())
                    throw new IOException(MessageLocalization.getComposedMessage("unexpected.gt.gt", new Object[0]));
                array.add(obj);
            } else
            {
                return array;
            }
        } while(true);
    }

    public PdfObject readPRObject()
        throws IOException
    {
        if(!nextValidToken())
            return null;
        PRTokeniser.TokenType type = tokeniser.getTokenType();
        static class _cls1
        {

            static final int $SwitchMap$co$com$pdf$text$pdf$PRTokeniser$TokenType[];

            static 
            {
                $SwitchMap$co$com$pdf$text$pdf$PRTokeniser$TokenType = new int[PRTokeniser.TokenType.values().length];
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$PRTokeniser$TokenType[PRTokeniser.TokenType.START_DIC.ordinal()] = 1;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$PRTokeniser$TokenType[PRTokeniser.TokenType.START_ARRAY.ordinal()] = 2;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$PRTokeniser$TokenType[PRTokeniser.TokenType.STRING.ordinal()] = 3;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$PRTokeniser$TokenType[PRTokeniser.TokenType.NAME.ordinal()] = 4;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$PRTokeniser$TokenType[PRTokeniser.TokenType.NUMBER.ordinal()] = 5;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$PRTokeniser$TokenType[PRTokeniser.TokenType.OTHER.ordinal()] = 6;
                }
                catch(NoSuchFieldError ex) { }
            }
        }

        switch(_cls1..SwitchMap.co.com.pdf.text.pdf.PRTokeniser.TokenType[type.ordinal()])
        {
        case 1: // '\001'
            PdfDictionary dic = readDictionary();
            return dic;

        case 2: // '\002'
            return readArray();

        case 3: // '\003'
            PdfString str = (new PdfString(tokeniser.getStringValue(), null)).setHexWriting(tokeniser.isHexString());
            return str;

        case 4: // '\004'
            return new PdfName(tokeniser.getStringValue(), false);

        case 5: // '\005'
            return new PdfNumber(tokeniser.getStringValue());

        case 6: // '\006'
            return new PdfLiteral(200, tokeniser.getStringValue());
        }
        return new PdfLiteral(-type.ordinal(), tokeniser.getStringValue());
    }

    public boolean nextValidToken()
        throws IOException
    {
        while(tokeniser.nextToken()) 
            if(tokeniser.getTokenType() != PRTokeniser.TokenType.COMMENT)
                return true;
        return false;
    }

    public static final int COMMAND_TYPE = 200;
    private PRTokeniser tokeniser;
}
