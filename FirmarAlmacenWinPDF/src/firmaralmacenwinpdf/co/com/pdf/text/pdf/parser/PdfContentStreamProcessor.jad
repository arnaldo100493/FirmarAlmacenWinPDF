// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfContentStreamProcessor.java

package co.com.pdf.text.pdf.parser;

import co.com.pdf.text.BaseColor;
import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.io.RandomAccessSourceFactory;
import co.com.pdf.text.pdf.*;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.parser:
//            XObjectDoHandler, ContentOperator, GraphicsState, MarkedContentInfo, 
//            TextRenderInfo, Matrix, RenderListener, InlineImageUtils, 
//            ImageRenderInfo, InlineImageInfo, ContentByteUtils

public class PdfContentStreamProcessor
{
    private static class IgnoreXObjectDoHandler
        implements XObjectDoHandler
    {

        public void handleXObject(PdfContentStreamProcessor pdfcontentstreamprocessor, PdfStream pdfstream, PdfIndirectReference pdfindirectreference)
        {
        }

        private IgnoreXObjectDoHandler()
        {
        }

    }

    private static class ImageXObjectDoHandler
        implements XObjectDoHandler
    {

        public void handleXObject(PdfContentStreamProcessor processor, PdfStream xobjectStream, PdfIndirectReference ref)
        {
            PdfDictionary colorSpaceDic = processor.resources.getAsDict(PdfName.COLORSPACE);
            ImageRenderInfo renderInfo = ImageRenderInfo.createForXObject(processor.gs().ctm, ref, colorSpaceDic);
            processor.renderListener.renderImage(renderInfo);
        }

        private ImageXObjectDoHandler()
        {
        }

    }

    private static class FormXObjectDoHandler
        implements XObjectDoHandler
    {

        public void handleXObject(PdfContentStreamProcessor processor, PdfStream stream, PdfIndirectReference ref)
        {
            PdfDictionary resources = stream.getAsDict(PdfName.RESOURCES);
            byte contentBytes[];
            try
            {
                contentBytes = ContentByteUtils.getContentBytesFromContentObject(stream);
            }
            catch(IOException e1)
            {
                throw new ExceptionConverter(e1);
            }
            PdfArray matrix = stream.getAsArray(PdfName.MATRIX);
            (new PushGraphicsState()).invoke(processor, null, null);
            if(matrix != null)
            {
                float a = matrix.getAsNumber(0).floatValue();
                float b = matrix.getAsNumber(1).floatValue();
                float c = matrix.getAsNumber(2).floatValue();
                float d = matrix.getAsNumber(3).floatValue();
                float e = matrix.getAsNumber(4).floatValue();
                float f = matrix.getAsNumber(5).floatValue();
                Matrix formMatrix = new Matrix(a, b, c, d, e, f);
                processor.gs().ctm = formMatrix.multiply(processor.gs().ctm);
            }
            processor.processContent(contentBytes, resources);
            (new PopGraphicsState()).invoke(processor, null, null);
        }

        private FormXObjectDoHandler()
        {
        }

    }

    private static class Do
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
            throws IOException
        {
            PdfName xobjectName = (PdfName)operands.get(0);
            processor.displayXObject(xobjectName);
        }

        private Do()
        {
        }

    }

    private static class EndMarkedContent
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
            throws Exception
        {
            processor.endMarkedContent();
        }

        private EndMarkedContent()
        {
        }

    }

    private static class BeginMarkedContentDictionary
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
            throws Exception
        {
            PdfObject properties = (PdfObject)operands.get(1);
            processor.beginMarkedContent((PdfName)operands.get(0), getPropertiesDictionary(properties, processor.resources));
        }

        private PdfDictionary getPropertiesDictionary(PdfObject operand1, ResourceDictionary resources)
        {
            if(operand1.isDictionary())
            {
                return (PdfDictionary)operand1;
            } else
            {
                PdfName dictionaryName = (PdfName)operand1;
                return resources.getAsDict(dictionaryName);
            }
        }

        private BeginMarkedContentDictionary()
        {
        }

    }

    private static class BeginMarkedContent
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
            throws Exception
        {
            processor.beginMarkedContent((PdfName)operands.get(0), new PdfDictionary());
        }

        private BeginMarkedContent()
        {
        }

    }

    private static class EndText
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            processor.textMatrix = null;
            processor.textLineMatrix = null;
            processor.endText();
        }

        private EndText()
        {
        }

    }

    private static class BeginText
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            processor.textMatrix = new Matrix();
            processor.textLineMatrix = processor.textMatrix;
            processor.beginText();
        }

        private BeginText()
        {
        }

    }

    private static class PopGraphicsState
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            processor.gsStack.pop();
        }

        private PopGraphicsState()
        {
        }

    }

    private static class SetColorStroke
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            processor.gs().strokeColor = PdfContentStreamProcessor.getColor(processor.gs().colorSpaceStroke, operands);
        }

        private SetColorStroke()
        {
        }

    }

    private static class SetColorFill
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            processor.gs().fillColor = PdfContentStreamProcessor.getColor(processor.gs().colorSpaceFill, operands);
        }

        private SetColorFill()
        {
        }

    }

    private static class SetColorSpaceStroke
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            processor.gs().colorSpaceStroke = (PdfName)operands.get(0);
        }

        private SetColorSpaceStroke()
        {
        }

    }

    private static class SetColorSpaceFill
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            processor.gs().colorSpaceFill = (PdfName)operands.get(0);
        }

        private SetColorSpaceFill()
        {
        }

    }

    private static class SetCMYKStroke
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            processor.gs().strokeColor = PdfContentStreamProcessor.getColor(4, operands);
        }

        private SetCMYKStroke()
        {
        }

    }

    private static class SetCMYKFill
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            processor.gs().fillColor = PdfContentStreamProcessor.getColor(4, operands);
        }

        private SetCMYKFill()
        {
        }

    }

    private static class SetRGBStroke
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            processor.gs().strokeColor = PdfContentStreamProcessor.getColor(3, operands);
        }

        private SetRGBStroke()
        {
        }

    }

    private static class SetRGBFill
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            processor.gs().fillColor = PdfContentStreamProcessor.getColor(3, operands);
        }

        private SetRGBFill()
        {
        }

    }

    private static class SetGrayStroke
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            processor.gs().strokeColor = PdfContentStreamProcessor.getColor(1, operands);
        }

        private SetGrayStroke()
        {
        }

    }

    private static class SetGrayFill
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            processor.gs().fillColor = PdfContentStreamProcessor.getColor(1, operands);
        }

        private SetGrayFill()
        {
        }

    }

    private static class ModifyCurrentTransformationMatrix
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            float a = ((PdfNumber)operands.get(0)).floatValue();
            float b = ((PdfNumber)operands.get(1)).floatValue();
            float c = ((PdfNumber)operands.get(2)).floatValue();
            float d = ((PdfNumber)operands.get(3)).floatValue();
            float e = ((PdfNumber)operands.get(4)).floatValue();
            float f = ((PdfNumber)operands.get(5)).floatValue();
            Matrix matrix = new Matrix(a, b, c, d, e, f);
            GraphicsState gs = (GraphicsState)processor.gsStack.peek();
            gs.ctm = matrix.multiply(gs.ctm);
        }

        private ModifyCurrentTransformationMatrix()
        {
        }

    }

    private static class PushGraphicsState
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            GraphicsState gs = (GraphicsState)processor.gsStack.peek();
            GraphicsState copy = new GraphicsState(gs);
            processor.gsStack.push(copy);
        }

        private PushGraphicsState()
        {
        }

    }

    private static class ProcessGraphicsStateResource
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            PdfName dictionaryName = (PdfName)operands.get(0);
            PdfDictionary extGState = processor.resources.getAsDict(PdfName.EXTGSTATE);
            if(extGState == null)
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("resources.do.not.contain.extgstate.entry.unable.to.process.operator.1", new Object[] {
                    operator
                }));
            PdfDictionary gsDic = extGState.getAsDict(dictionaryName);
            if(gsDic == null)
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("1.is.an.unknown.graphics.state.dictionary", new Object[] {
                    dictionaryName
                }));
            PdfArray fontParameter = gsDic.getAsArray(PdfName.FONT);
            if(fontParameter != null)
            {
                CMapAwareDocumentFont font = processor.getFont((PRIndirectReference)fontParameter.getPdfObject(0));
                float size = fontParameter.getAsNumber(1).floatValue();
                processor.gs().font = font;
                processor.gs().fontSize = size;
            }
        }

        private ProcessGraphicsStateResource()
        {
        }

    }

    private static class SetTextWordSpacing
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            PdfNumber wordSpace = (PdfNumber)operands.get(0);
            processor.gs().wordSpacing = wordSpace.floatValue();
        }

        private SetTextWordSpacing()
        {
        }

    }

    private static class SetTextCharacterSpacing
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            PdfNumber charSpace = (PdfNumber)operands.get(0);
            processor.gs().characterSpacing = charSpace.floatValue();
        }

        private SetTextCharacterSpacing()
        {
        }

    }

    private static class SetTextHorizontalScaling
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            PdfNumber scale = (PdfNumber)operands.get(0);
            processor.gs().horizontalScaling = scale.floatValue() / 100F;
        }

        private SetTextHorizontalScaling()
        {
        }

    }

    private static class SetTextLeading
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            PdfNumber leading = (PdfNumber)operands.get(0);
            processor.gs().leading = leading.floatValue();
        }

        private SetTextLeading()
        {
        }

    }

    private static class SetTextRise
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            PdfNumber rise = (PdfNumber)operands.get(0);
            processor.gs().rise = rise.floatValue();
        }

        private SetTextRise()
        {
        }

    }

    private static class SetTextRenderMode
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            PdfNumber render = (PdfNumber)operands.get(0);
            processor.gs().renderMode = render.intValue();
        }

        private SetTextRenderMode()
        {
        }

    }

    private static class SetTextFont
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            PdfName fontResourceName = (PdfName)operands.get(0);
            float size = ((PdfNumber)operands.get(1)).floatValue();
            PdfDictionary fontsDictionary = processor.resources.getAsDict(PdfName.FONT);
            PdfObject fontObject = fontsDictionary.get(fontResourceName);
            CMapAwareDocumentFont font;
            if(fontObject instanceof PdfDictionary)
                font = processor.getFont((PdfDictionary)fontObject);
            else
                font = processor.getFont((PRIndirectReference)fontObject);
            processor.gs().font = font;
            processor.gs().fontSize = size;
        }

        private SetTextFont()
        {
        }

    }

    private static class TextMoveStartNextLine
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            float tx = ((PdfNumber)operands.get(0)).floatValue();
            float ty = ((PdfNumber)operands.get(1)).floatValue();
            Matrix translationMatrix = new Matrix(tx, ty);
            processor.textMatrix = translationMatrix.multiply(processor.textLineMatrix);
            processor.textLineMatrix = processor.textMatrix;
        }

        private TextMoveStartNextLine()
        {
        }

    }

    private static class TextMoveStartNextLineWithLeading
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            float ty = ((PdfNumber)operands.get(1)).floatValue();
            ArrayList tlOperands = new ArrayList(1);
            tlOperands.add(0, new PdfNumber(-ty));
            setTextLeading.invoke(processor, null, tlOperands);
            moveStartNextLine.invoke(processor, null, operands);
        }

        private final TextMoveStartNextLine moveStartNextLine;
        private final SetTextLeading setTextLeading;

        public TextMoveStartNextLineWithLeading(TextMoveStartNextLine moveStartNextLine, SetTextLeading setTextLeading)
        {
            this.moveStartNextLine = moveStartNextLine;
            this.setTextLeading = setTextLeading;
        }
    }

    private static class TextSetTextMatrix
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            float a = ((PdfNumber)operands.get(0)).floatValue();
            float b = ((PdfNumber)operands.get(1)).floatValue();
            float c = ((PdfNumber)operands.get(2)).floatValue();
            float d = ((PdfNumber)operands.get(3)).floatValue();
            float e = ((PdfNumber)operands.get(4)).floatValue();
            float f = ((PdfNumber)operands.get(5)).floatValue();
            processor.textLineMatrix = new Matrix(a, b, c, d, e, f);
            processor.textMatrix = processor.textLineMatrix;
        }

        private TextSetTextMatrix()
        {
        }

    }

    private static class TextMoveNextLine
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            ArrayList tdoperands = new ArrayList(2);
            tdoperands.add(0, new PdfNumber(0));
            tdoperands.add(1, new PdfNumber(-processor.gs().leading));
            moveStartNextLine.invoke(processor, null, tdoperands);
        }

        private final TextMoveStartNextLine moveStartNextLine;

        public TextMoveNextLine(TextMoveStartNextLine moveStartNextLine)
        {
            this.moveStartNextLine = moveStartNextLine;
        }
    }

    private static class ShowText
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            PdfString string = (PdfString)operands.get(0);
            processor.displayPdfString(string);
        }

        private ShowText()
        {
        }

    }

    private static class MoveNextLineAndShowText
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            textMoveNextLine.invoke(processor, null, new ArrayList(0));
            showText.invoke(processor, null, operands);
        }

        private final TextMoveNextLine textMoveNextLine;
        private final ShowText showText;

        public MoveNextLineAndShowText(TextMoveNextLine textMoveNextLine, ShowText showText)
        {
            this.textMoveNextLine = textMoveNextLine;
            this.showText = showText;
        }
    }

    private static class MoveNextLineAndShowTextWithSpacing
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            PdfNumber aw = (PdfNumber)operands.get(0);
            PdfNumber ac = (PdfNumber)operands.get(1);
            PdfString string = (PdfString)operands.get(2);
            ArrayList twOperands = new ArrayList(1);
            twOperands.add(0, aw);
            setTextWordSpacing.invoke(processor, null, twOperands);
            ArrayList tcOperands = new ArrayList(1);
            tcOperands.add(0, ac);
            setTextCharacterSpacing.invoke(processor, null, tcOperands);
            ArrayList tickOperands = new ArrayList(1);
            tickOperands.add(0, string);
            moveNextLineAndShowText.invoke(processor, null, tickOperands);
        }

        private final SetTextWordSpacing setTextWordSpacing;
        private final SetTextCharacterSpacing setTextCharacterSpacing;
        private final MoveNextLineAndShowText moveNextLineAndShowText;

        public MoveNextLineAndShowTextWithSpacing(SetTextWordSpacing setTextWordSpacing, SetTextCharacterSpacing setTextCharacterSpacing, MoveNextLineAndShowText moveNextLineAndShowText)
        {
            this.setTextWordSpacing = setTextWordSpacing;
            this.setTextCharacterSpacing = setTextCharacterSpacing;
            this.moveNextLineAndShowText = moveNextLineAndShowText;
        }
    }

    private static class ShowTextArray
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor processor, PdfLiteral operator, ArrayList operands)
        {
            PdfArray array = (PdfArray)operands.get(0);
            float tj = 0.0F;
            for(Iterator i = array.listIterator(); i.hasNext();)
            {
                PdfObject entryObj = (PdfObject)i.next();
                if(entryObj instanceof PdfString)
                {
                    processor.displayPdfString((PdfString)entryObj);
                    tj = 0.0F;
                } else
                {
                    tj = ((PdfNumber)entryObj).floatValue();
                    processor.applyTextAdjust(tj);
                }
            }

        }

        private ShowTextArray()
        {
        }

    }

    private static class IgnoreOperatorContentOperator
        implements ContentOperator
    {

        public void invoke(PdfContentStreamProcessor pdfcontentstreamprocessor, PdfLiteral pdfliteral, ArrayList arraylist)
        {
        }

        private IgnoreOperatorContentOperator()
        {
        }

    }

    private static class ResourceDictionary extends PdfDictionary
    {

        public void push(PdfDictionary resources)
        {
            resourcesStack.add(resources);
        }

        public void pop()
        {
            resourcesStack.remove(resourcesStack.size() - 1);
        }

        public PdfObject getDirectObject(PdfName key)
        {
            for(int i = resourcesStack.size() - 1; i >= 0; i--)
            {
                PdfDictionary subResource = (PdfDictionary)resourcesStack.get(i);
                if(subResource == null)
                    continue;
                PdfObject obj = subResource.getDirectObject(key);
                if(obj != null)
                    return obj;
            }

            return super.getDirectObject(key);
        }

        private final List resourcesStack = new ArrayList();

        public ResourceDictionary()
        {
        }
    }


    public PdfContentStreamProcessor(RenderListener renderListener)
    {
        this.renderListener = renderListener;
        populateOperators();
        populateXObjectDoHandlers();
        reset();
    }

    private void populateXObjectDoHandlers()
    {
        registerXObjectDoHandler(PdfName.DEFAULT, new IgnoreXObjectDoHandler());
        registerXObjectDoHandler(PdfName.FORM, new FormXObjectDoHandler());
        registerXObjectDoHandler(PdfName.IMAGE, new ImageXObjectDoHandler());
    }

    public XObjectDoHandler registerXObjectDoHandler(PdfName xobjectSubType, XObjectDoHandler handler)
    {
        return (XObjectDoHandler)xobjectDoHandlers.put(xobjectSubType, handler);
    }

    private CMapAwareDocumentFont getFont(PRIndirectReference ind)
    {
        Integer n = Integer.valueOf(ind.getNumber());
        CMapAwareDocumentFont font = (CMapAwareDocumentFont)cachedFonts.get(n);
        if(font == null)
        {
            font = new CMapAwareDocumentFont(ind);
            cachedFonts.put(n, font);
        }
        return font;
    }

    private CMapAwareDocumentFont getFont(PdfDictionary fontResource)
    {
        return new CMapAwareDocumentFont(fontResource);
    }

    private void populateOperators()
    {
        registerContentOperator("DefaultOperator", new IgnoreOperatorContentOperator());
        registerContentOperator("q", new PushGraphicsState());
        registerContentOperator("Q", new PopGraphicsState());
        registerContentOperator("g", new SetGrayFill());
        registerContentOperator("G", new SetGrayStroke());
        registerContentOperator("rg", new SetRGBFill());
        registerContentOperator("RG", new SetRGBStroke());
        registerContentOperator("k", new SetCMYKFill());
        registerContentOperator("K", new SetCMYKStroke());
        registerContentOperator("cs", new SetColorSpaceFill());
        registerContentOperator("CS", new SetColorSpaceStroke());
        registerContentOperator("sc", new SetColorFill());
        registerContentOperator("SC", new SetColorStroke());
        registerContentOperator("scn", new SetColorFill());
        registerContentOperator("SCN", new SetColorStroke());
        registerContentOperator("cm", new ModifyCurrentTransformationMatrix());
        registerContentOperator("gs", new ProcessGraphicsStateResource());
        SetTextCharacterSpacing tcOperator = new SetTextCharacterSpacing();
        registerContentOperator("Tc", tcOperator);
        SetTextWordSpacing twOperator = new SetTextWordSpacing();
        registerContentOperator("Tw", twOperator);
        registerContentOperator("Tz", new SetTextHorizontalScaling());
        SetTextLeading tlOperator = new SetTextLeading();
        registerContentOperator("TL", tlOperator);
        registerContentOperator("Tf", new SetTextFont());
        registerContentOperator("Tr", new SetTextRenderMode());
        registerContentOperator("Ts", new SetTextRise());
        registerContentOperator("BT", new BeginText());
        registerContentOperator("ET", new EndText());
        registerContentOperator("BMC", new BeginMarkedContent());
        registerContentOperator("BDC", new BeginMarkedContentDictionary());
        registerContentOperator("EMC", new EndMarkedContent());
        TextMoveStartNextLine tdOperator = new TextMoveStartNextLine();
        registerContentOperator("Td", tdOperator);
        registerContentOperator("TD", new TextMoveStartNextLineWithLeading(tdOperator, tlOperator));
        registerContentOperator("Tm", new TextSetTextMatrix());
        TextMoveNextLine tstarOperator = new TextMoveNextLine(tdOperator);
        registerContentOperator("T*", tstarOperator);
        ShowText tjOperator = new ShowText();
        registerContentOperator("Tj", tjOperator);
        MoveNextLineAndShowText tickOperator = new MoveNextLineAndShowText(tstarOperator, tjOperator);
        registerContentOperator("'", tickOperator);
        registerContentOperator("\"", new MoveNextLineAndShowTextWithSpacing(twOperator, tcOperator, tickOperator));
        registerContentOperator("TJ", new ShowTextArray());
        registerContentOperator("Do", new Do());
    }

    public ContentOperator registerContentOperator(String operatorString, ContentOperator operator)
    {
        return (ContentOperator)operators.put(operatorString, operator);
    }

    public void reset()
    {
        gsStack.removeAllElements();
        gsStack.add(new GraphicsState());
        textMatrix = null;
        textLineMatrix = null;
        resources = new ResourceDictionary();
    }

    private GraphicsState gs()
    {
        return (GraphicsState)gsStack.peek();
    }

    private void invokeOperator(PdfLiteral operator, ArrayList operands)
        throws Exception
    {
        ContentOperator op = (ContentOperator)operators.get(operator.toString());
        if(op == null)
            op = (ContentOperator)operators.get("DefaultOperator");
        op.invoke(this, operator, operands);
    }

    private void beginMarkedContent(PdfName tag, PdfDictionary dict)
    {
        markedContentStack.push(new MarkedContentInfo(tag, dict));
    }

    private void endMarkedContent()
    {
        markedContentStack.pop();
    }

    private String decode(PdfString in)
    {
        byte bytes[] = in.getBytes();
        return gs().font.decode(bytes, 0, bytes.length);
    }

    private void beginText()
    {
        renderListener.beginTextBlock();
    }

    private void endText()
    {
        renderListener.endTextBlock();
    }

    private void displayPdfString(PdfString string)
    {
        String unicode = decode(string);
        TextRenderInfo renderInfo = new TextRenderInfo(unicode, gs(), textMatrix, markedContentStack);
        renderListener.renderText(renderInfo);
        textMatrix = (new Matrix(renderInfo.getUnscaledWidth(), 0.0F)).multiply(textMatrix);
    }

    private void displayXObject(PdfName xobjectName)
        throws IOException
    {
        PdfDictionary xobjects = resources.getAsDict(PdfName.XOBJECT);
        PdfObject xobject = xobjects.getDirectObject(xobjectName);
        PdfStream xobjectStream = (PdfStream)xobject;
        PdfName subType = xobjectStream.getAsName(PdfName.SUBTYPE);
        if(xobject.isStream())
        {
            XObjectDoHandler handler = (XObjectDoHandler)xobjectDoHandlers.get(subType);
            if(handler == null)
                handler = (XObjectDoHandler)xobjectDoHandlers.get(PdfName.DEFAULT);
            handler.handleXObject(this, xobjectStream, xobjects.getAsIndirectObject(xobjectName));
        } else
        {
            throw new IllegalStateException(MessageLocalization.getComposedMessage("XObject.1.is.not.a.stream", new Object[] {
                xobjectName
            }));
        }
    }

    private void applyTextAdjust(float tj)
    {
        float adjustBy = (-tj / 1000F) * gs().fontSize * gs().horizontalScaling;
        textMatrix = (new Matrix(adjustBy, 0.0F)).multiply(textMatrix);
    }

    public void processContent(byte contentBytes[], PdfDictionary resources)
    {
        this.resources.push(resources);
        try
        {
            PRTokeniser tokeniser = new PRTokeniser(new RandomAccessFileOrArray((new RandomAccessSourceFactory()).createSource(contentBytes)));
            PdfContentParser ps = new PdfContentParser(tokeniser);
            for(ArrayList operands = new ArrayList(); ps.parse(operands).size() > 0;)
            {
                PdfLiteral operator = (PdfLiteral)operands.get(operands.size() - 1);
                if("BI".equals(operator.toString()))
                {
                    PdfDictionary colorSpaceDic = resources == null ? null : resources.getAsDict(PdfName.COLORSPACE);
                    handleInlineImage(InlineImageUtils.parseInlineImage(ps, colorSpaceDic), colorSpaceDic);
                } else
                {
                    invokeOperator(operator, operands);
                }
            }

        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
        this.resources.pop();
    }

    protected void handleInlineImage(InlineImageInfo info, PdfDictionary colorSpaceDic)
    {
        ImageRenderInfo renderInfo = ImageRenderInfo.createForEmbeddedImage(gs().ctm, info, colorSpaceDic);
        renderListener.renderImage(renderInfo);
    }

    private static BaseColor getColor(PdfName colorSpace, List operands)
    {
        if(PdfName.DEVICEGRAY.equals(colorSpace))
            return getColor(1, operands);
        if(PdfName.DEVICERGB.equals(colorSpace))
            return getColor(3, operands);
        if(PdfName.DEVICECMYK.equals(colorSpace))
            return getColor(4, operands);
        else
            return null;
    }

    private static BaseColor getColor(int nOperands, List operands)
    {
        float c[] = new float[nOperands];
        for(int i = 0; i < nOperands; i++)
            c[i] = ((PdfNumber)operands.get(i)).floatValue();

        switch(nOperands)
        {
        case 1: // '\001'
            return new GrayColor(c[0]);

        case 3: // '\003'
            return new BaseColor(c[0], c[1], c[2]);

        case 4: // '\004'
            return new CMYKColor(c[0], c[1], c[2], c[3]);

        case 2: // '\002'
        default:
            return null;
        }
    }

    public static final String DEFAULTOPERATOR = "DefaultOperator";
    private final Map operators = new HashMap();
    private ResourceDictionary resources;
    private final Stack gsStack = new Stack();
    private Matrix textMatrix;
    private Matrix textLineMatrix;
    private final RenderListener renderListener;
    private final Map xobjectDoHandlers = new HashMap();
    private final Map cachedFonts = new HashMap();
    private final Stack markedContentStack = new Stack();



















}
