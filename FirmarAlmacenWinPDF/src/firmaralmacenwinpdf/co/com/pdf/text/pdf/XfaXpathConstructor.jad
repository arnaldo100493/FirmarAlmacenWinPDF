// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XfaXpathConstructor.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.pdf.security.XpathConstructor;

public class XfaXpathConstructor
    implements XpathConstructor
{
    public static final class XdpPackage extends Enum
    {

        public static XdpPackage[] values()
        {
            return (XdpPackage[])$VALUES.clone();
        }

        public static XdpPackage valueOf(String name)
        {
            return (XdpPackage)Enum.valueOf(co/com/pdf/text/pdf/XfaXpathConstructor$XdpPackage, name);
        }

        public static final XdpPackage Config;
        public static final XdpPackage ConnectionSet;
        public static final XdpPackage Datasets;
        public static final XdpPackage LocaleSet;
        public static final XdpPackage Pdf;
        public static final XdpPackage SourceSet;
        public static final XdpPackage Stylesheet;
        public static final XdpPackage Template;
        public static final XdpPackage Xdc;
        public static final XdpPackage Xfdf;
        public static final XdpPackage Xmpmeta;
        private static final XdpPackage $VALUES[];

        static 
        {
            Config = new XdpPackage("Config", 0);
            ConnectionSet = new XdpPackage("ConnectionSet", 1);
            Datasets = new XdpPackage("Datasets", 2);
            LocaleSet = new XdpPackage("LocaleSet", 3);
            Pdf = new XdpPackage("Pdf", 4);
            SourceSet = new XdpPackage("SourceSet", 5);
            Stylesheet = new XdpPackage("Stylesheet", 6);
            Template = new XdpPackage("Template", 7);
            Xdc = new XdpPackage("Xdc", 8);
            Xfdf = new XdpPackage("Xfdf", 9);
            Xmpmeta = new XdpPackage("Xmpmeta", 10);
            $VALUES = (new XdpPackage[] {
                Config, ConnectionSet, Datasets, LocaleSet, Pdf, SourceSet, Stylesheet, Template, Xdc, Xfdf, 
                Xmpmeta
            });
        }

        private XdpPackage(String s, int i)
        {
            super(s, i);
        }
    }


    public XfaXpathConstructor()
    {
        xpathExpression = "";
    }

    public XfaXpathConstructor(XdpPackage xdpPackage)
    {
        static class _cls1
        {

            static final int $SwitchMap$co$com$pdf$text$pdf$XfaXpathConstructor$XdpPackage[];

            static 
            {
                $SwitchMap$co$com$pdf$text$pdf$XfaXpathConstructor$XdpPackage = new int[XdpPackage.values().length];
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$XfaXpathConstructor$XdpPackage[XdpPackage.Config.ordinal()] = 1;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$XfaXpathConstructor$XdpPackage[XdpPackage.ConnectionSet.ordinal()] = 2;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$XfaXpathConstructor$XdpPackage[XdpPackage.Datasets.ordinal()] = 3;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$XfaXpathConstructor$XdpPackage[XdpPackage.LocaleSet.ordinal()] = 4;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$XfaXpathConstructor$XdpPackage[XdpPackage.Pdf.ordinal()] = 5;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$XfaXpathConstructor$XdpPackage[XdpPackage.SourceSet.ordinal()] = 6;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$XfaXpathConstructor$XdpPackage[XdpPackage.Stylesheet.ordinal()] = 7;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$XfaXpathConstructor$XdpPackage[XdpPackage.Template.ordinal()] = 8;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$XfaXpathConstructor$XdpPackage[XdpPackage.Xdc.ordinal()] = 9;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$XfaXpathConstructor$XdpPackage[XdpPackage.Xfdf.ordinal()] = 10;
                }
                catch(NoSuchFieldError ex) { }
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$XfaXpathConstructor$XdpPackage[XdpPackage.Xmpmeta.ordinal()] = 11;
                }
                catch(NoSuchFieldError ex) { }
            }
        }

        String strPackage;
        switch(_cls1..SwitchMap.co.com.pdf.text.pdf.XfaXpathConstructor.XdpPackage[xdpPackage.ordinal()])
        {
        case 1: // '\001'
            strPackage = "config";
            break;

        case 2: // '\002'
            strPackage = "connectionSet";
            break;

        case 3: // '\003'
            strPackage = "datasets";
            break;

        case 4: // '\004'
            strPackage = "localeSet";
            break;

        case 5: // '\005'
            strPackage = "pdf";
            break;

        case 6: // '\006'
            strPackage = "sourceSet";
            break;

        case 7: // '\007'
            strPackage = "stylesheet";
            break;

        case 8: // '\b'
            strPackage = "template";
            break;

        case 9: // '\t'
            strPackage = "xdc";
            break;

        case 10: // '\n'
            strPackage = "xfdf";
            break;

        case 11: // '\013'
            strPackage = "xmpmeta";
            break;

        default:
            xpathExpression = "";
            return;
        }
        StringBuilder builder = new StringBuilder("/xdp:xdp/*[local-name()='");
        builder.append(strPackage);
        builder.append("']");
        xpathExpression = builder.toString();
    }

    public String getXpathExpression()
    {
        return xpathExpression;
    }

    private final String CONFIG = "config";
    private final String CONNECTIONSET = "connectionSet";
    private final String DATASETS = "datasets";
    private final String LOCALESET = "localeSet";
    private final String PDF = "pdf";
    private final String SOURCESET = "sourceSet";
    private final String STYLESHEET = "stylesheet";
    private final String TEMPLATE = "template";
    private final String XDC = "xdc";
    private final String XFDF = "xfdf";
    private final String XMPMETA = "xmpmeta";
    private String xpathExpression;
}
