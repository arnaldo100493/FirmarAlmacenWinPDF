// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XMPNode.java

package co.com.pdf.xmp.impl;

import co.com.pdf.xmp.XMPException;
import co.com.pdf.xmp.options.PropertyOptions;
import java.util.*;

class XMPNode
    implements Comparable
{

    public XMPNode(String name, String value, PropertyOptions options)
    {
        children = null;
        qualifier = null;
        this.options = null;
        this.name = name;
        this.value = value;
        this.options = options;
    }

    public XMPNode(String name, PropertyOptions options)
    {
        this(name, null, options);
    }

    public void clear()
    {
        options = null;
        name = null;
        value = null;
        children = null;
        qualifier = null;
    }

    public XMPNode getParent()
    {
        return parent;
    }

    public XMPNode getChild(int index)
    {
        return (XMPNode)getChildren().get(index - 1);
    }

    public void addChild(XMPNode node)
        throws XMPException
    {
        assertChildNotExisting(node.getName());
        node.setParent(this);
        getChildren().add(node);
    }

    public void addChild(int index, XMPNode node)
        throws XMPException
    {
        assertChildNotExisting(node.getName());
        node.setParent(this);
        getChildren().add(index - 1, node);
    }

    public void replaceChild(int index, XMPNode node)
    {
        node.setParent(this);
        getChildren().set(index - 1, node);
    }

    public void removeChild(int itemIndex)
    {
        getChildren().remove(itemIndex - 1);
        cleanupChildren();
    }

    public void removeChild(XMPNode node)
    {
        getChildren().remove(node);
        cleanupChildren();
    }

    protected void cleanupChildren()
    {
        if(children.isEmpty())
            children = null;
    }

    public void removeChildren()
    {
        children = null;
    }

    public int getChildrenLength()
    {
        return children == null ? 0 : children.size();
    }

    public XMPNode findChildByName(String expr)
    {
        return find(getChildren(), expr);
    }

    public XMPNode getQualifier(int index)
    {
        return (XMPNode)getQualifier().get(index - 1);
    }

    public int getQualifierLength()
    {
        return qualifier == null ? 0 : qualifier.size();
    }

    public void addQualifier(XMPNode qualNode)
        throws XMPException
    {
        assertQualifierNotExisting(qualNode.getName());
        qualNode.setParent(this);
        qualNode.getOptions().setQualifier(true);
        getOptions().setHasQualifiers(true);
        if(qualNode.isLanguageNode())
        {
            options.setHasLanguage(true);
            getQualifier().add(0, qualNode);
        } else
        if(qualNode.isTypeNode())
        {
            options.setHasType(true);
            getQualifier().add(options.getHasLanguage() ? 1 : 0, qualNode);
        } else
        {
            getQualifier().add(qualNode);
        }
    }

    public void removeQualifier(XMPNode qualNode)
    {
        PropertyOptions opts = getOptions();
        if(qualNode.isLanguageNode())
            opts.setHasLanguage(false);
        else
        if(qualNode.isTypeNode())
            opts.setHasType(false);
        getQualifier().remove(qualNode);
        if(qualifier.isEmpty())
        {
            opts.setHasQualifiers(false);
            qualifier = null;
        }
    }

    public void removeQualifiers()
    {
        PropertyOptions opts = getOptions();
        opts.setHasQualifiers(false);
        opts.setHasLanguage(false);
        opts.setHasType(false);
        qualifier = null;
    }

    public XMPNode findQualifierByName(String expr)
    {
        return find(qualifier, expr);
    }

    public boolean hasChildren()
    {
        return children != null && children.size() > 0;
    }

    public Iterator iterateChildren()
    {
        if(children != null)
            return getChildren().iterator();
        else
            return Collections.EMPTY_LIST.listIterator();
    }

    public boolean hasQualifier()
    {
        return qualifier != null && qualifier.size() > 0;
    }

    public Iterator iterateQualifier()
    {
        if(qualifier != null)
        {
            final Iterator it = getQualifier().iterator();
            return new Iterator() {

                public boolean hasNext()
                {
                    return it.hasNext();
                }

                public Object next()
                {
                    return it.next();
                }

                public void remove()
                {
                    throw new UnsupportedOperationException("remove() is not allowed due to the internal contraints");
                }

                final Iterator val$it;
                final XMPNode this$0;

            
            {
                this$0 = XMPNode.this;
                it = iterator;
                super();
            }
            }
;
        } else
        {
            return Collections.EMPTY_LIST.iterator();
        }
    }

    public Object clone()
    {
        PropertyOptions newOptions;
        try
        {
            newOptions = new PropertyOptions(getOptions().getOptions());
        }
        catch(XMPException e)
        {
            newOptions = new PropertyOptions();
        }
        XMPNode newNode = new XMPNode(name, value, newOptions);
        cloneSubtree(newNode);
        return newNode;
    }

    public void cloneSubtree(XMPNode destination)
    {
        try
        {
            XMPNode child;
            for(Iterator it = iterateChildren(); it.hasNext(); destination.addChild((XMPNode)child.clone()))
                child = (XMPNode)it.next();

            XMPNode qualifier;
            for(Iterator it = iterateQualifier(); it.hasNext(); destination.addQualifier((XMPNode)qualifier.clone()))
                qualifier = (XMPNode)it.next();

        }
        catch(XMPException e)
        {
            if(!$assertionsDisabled)
                throw new AssertionError();
        }
    }

    public String dumpNode(boolean recursive)
    {
        StringBuffer result = new StringBuffer(512);
        dumpNode(result, recursive, 0, 0);
        return result.toString();
    }

    public int compareTo(Object xmpNode)
    {
        if(getOptions().isSchemaNode())
            return value.compareTo(((XMPNode)xmpNode).getValue());
        else
            return name.compareTo(((XMPNode)xmpNode).getName());
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public PropertyOptions getOptions()
    {
        if(options == null)
            options = new PropertyOptions();
        return options;
    }

    public void setOptions(PropertyOptions options)
    {
        this.options = options;
    }

    public boolean isImplicit()
    {
        return implicit;
    }

    public void setImplicit(boolean implicit)
    {
        this.implicit = implicit;
    }

    public boolean getHasAliases()
    {
        return hasAliases;
    }

    public void setHasAliases(boolean hasAliases)
    {
        this.hasAliases = hasAliases;
    }

    public boolean isAlias()
    {
        return alias;
    }

    public void setAlias(boolean alias)
    {
        this.alias = alias;
    }

    public boolean getHasValueChild()
    {
        return hasValueChild;
    }

    public void setHasValueChild(boolean hasValueChild)
    {
        this.hasValueChild = hasValueChild;
    }

    public void sort()
    {
        if(hasQualifier())
        {
            XMPNode quals[] = (XMPNode[])(XMPNode[])getQualifier().toArray(new XMPNode[getQualifierLength()]);
            int sortFrom;
            for(sortFrom = 0; quals.length > sortFrom && ("xml:lang".equals(quals[sortFrom].getName()) || "rdf:type".equals(quals[sortFrom].getName())); sortFrom++)
                quals[sortFrom].sort();

            Arrays.sort(quals, sortFrom, quals.length);
            ListIterator it = qualifier.listIterator();
            for(int j = 0; j < quals.length; j++)
            {
                it.next();
                it.set(quals[j]);
                quals[j].sort();
            }

        }
        if(hasChildren())
        {
            if(!getOptions().isArray())
                Collections.sort(children);
            for(Iterator it = iterateChildren(); it.hasNext(); ((XMPNode)it.next()).sort());
        }
    }

    private void dumpNode(StringBuffer result, boolean recursive, int indent, int index)
    {
        for(int i = 0; i < indent; i++)
            result.append('\t');

        if(parent != null)
        {
            if(getOptions().isQualifier())
            {
                result.append('?');
                result.append(name);
            } else
            if(getParent().getOptions().isArray())
            {
                result.append('[');
                result.append(index);
                result.append(']');
            } else
            {
                result.append(name);
            }
        } else
        {
            result.append("ROOT NODE");
            if(name != null && name.length() > 0)
            {
                result.append(" (");
                result.append(name);
                result.append(')');
            }
        }
        if(value != null && value.length() > 0)
        {
            result.append(" = \"");
            result.append(value);
            result.append('"');
        }
        if(getOptions().containsOneOf(-1))
        {
            result.append("\t(");
            result.append(getOptions().toString());
            result.append(" : ");
            result.append(getOptions().getOptionsString());
            result.append(')');
        }
        result.append('\n');
        if(recursive && hasQualifier())
        {
            XMPNode quals[] = (XMPNode[])(XMPNode[])getQualifier().toArray(new XMPNode[getQualifierLength()]);
            int i;
            for(i = 0; quals.length > i && ("xml:lang".equals(quals[i].getName()) || "rdf:type".equals(quals[i].getName())); i++);
            Arrays.sort(quals, i, quals.length);
            for(i = 0; i < quals.length; i++)
            {
                XMPNode qualifier = quals[i];
                qualifier.dumpNode(result, recursive, indent + 2, i + 1);
            }

        }
        if(recursive && hasChildren())
        {
            XMPNode children[] = (XMPNode[])(XMPNode[])getChildren().toArray(new XMPNode[getChildrenLength()]);
            if(!getOptions().isArray())
                Arrays.sort(children);
            for(int i = 0; i < children.length; i++)
            {
                XMPNode child = children[i];
                child.dumpNode(result, recursive, indent + 1, i + 1);
            }

        }
    }

    private boolean isLanguageNode()
    {
        return "xml:lang".equals(name);
    }

    private boolean isTypeNode()
    {
        return "rdf:type".equals(name);
    }

    private List getChildren()
    {
        if(children == null)
            children = new ArrayList(0);
        return children;
    }

    public List getUnmodifiableChildren()
    {
        return Collections.unmodifiableList(new ArrayList(getChildren()));
    }

    private List getQualifier()
    {
        if(qualifier == null)
            qualifier = new ArrayList(0);
        return qualifier;
    }

    protected void setParent(XMPNode parent)
    {
        this.parent = parent;
    }

    private XMPNode find(List list, String expr)
    {
label0:
        {
            if(list == null)
                break label0;
            Iterator it = list.iterator();
            XMPNode child;
            do
            {
                if(!it.hasNext())
                    break label0;
                child = (XMPNode)it.next();
            } while(!child.getName().equals(expr));
            return child;
        }
        return null;
    }

    private void assertChildNotExisting(String childName)
        throws XMPException
    {
        if(!"[]".equals(childName) && findChildByName(childName) != null)
            throw new XMPException((new StringBuilder()).append("Duplicate property or field node '").append(childName).append("'").toString(), 203);
        else
            return;
    }

    private void assertQualifierNotExisting(String qualifierName)
        throws XMPException
    {
        if(!"[]".equals(qualifierName) && findQualifierByName(qualifierName) != null)
            throw new XMPException((new StringBuilder()).append("Duplicate '").append(qualifierName).append("' qualifier").toString(), 203);
        else
            return;
    }

    private String name;
    private String value;
    private XMPNode parent;
    private List children;
    private List qualifier;
    private PropertyOptions options;
    private boolean implicit;
    private boolean hasAliases;
    private boolean alias;
    private boolean hasValueChild;
    static final boolean $assertionsDisabled = !co/com/pdf/xmp/impl/XMPNode.desiredAssertionStatus();

}
