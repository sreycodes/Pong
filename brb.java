class brb
{
    public static void main(int[] a) 
    {
        a[0] = 4;
    }
    public static void main2()
    {
       int a[] = {1,3,3};
        System.out.println(a[0]);
        main(a);
        System.out.println(a[0]);
    }
}

        
        