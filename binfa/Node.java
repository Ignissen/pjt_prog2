

public class Node
{
    public Node()
    {
        c = '/';
    }
    public Node(char c)
    {
        this.c = c;
    }
    public void addLeft(Node n)
    {
        this.left = n;
    }
    public void addRight(Node n)
    {
        this.right = n;
    }
    public boolean hasLeft()
    {
        return this.left != null;
    }
    public boolean hasRight()
    {
        return this.right != null;
    }
    public Node getLeft()
    {
        return this.left;
    }
    public Node getRight()
    {
        return this.right;
    }
    public char getChar()
    {
        return this.c;
    }
    private Node left;
    private Node right;
    private char c;
}