

public class Binfa
{
    public Binfa()
    {
        root = fa = new Node();
    }
    
    public void add(char c)
    {
        if(c == '0')
        {
            if(fa.hasLeft())
            {
                fa = fa.getLeft();
            }
            else
            {
                fa.addLeft(new Node(c));
                fa = root;
            }
        }
        else
        {
            if(fa.hasRight())
            {
                fa = fa.getRight();
            }
            else
            {
                fa.addRight(new Node(c));
                fa = root;
            }
        }
    }

    public Node getRoot()
    {
        return this.root;
    }

    public Node getFa()
    {
        return this.fa;
    }

    public void preorder(Node n)
    {
        if(n == null)
        {
            return;
        }
        preorder(n.getLeft());
        preorder(n.getRight());
        System.out.println(n.getChar());
    }

    public void postorder(Node n)
    {
        if(n == null)
        {
            return;
        }
        System.out.println(n.getChar());
        preorder(n.getLeft());
        preorder(n.getRight());
    }
    public void inorder(Node n)
    {
        if(n == null)
        {
            return;
        }
        preorder(n.getLeft());
        System.out.println(n.getChar());
        preorder(n.getRight());
    }

    Node root;
    Node fa;
}




