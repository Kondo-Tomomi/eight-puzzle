public class IDS {

    public static void Setting(){  //step1
        NPD root = new NPD();
        root.FirstNPD();
        DLS.start_time = System.currentTimeMillis();
        DLS.start = root;
        DLS.limit = 0;
    }
    public static void main(String[] args){
        Setting();
        System.out.println("DLS.limit = "+DLS.limit);
        while(true){
            DLS.main(args);
            DLS.setLimit();
            System.out.println("DLS.limit = "+DLS.limit);
        }
    }
}