public class S0002_AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(4) ;
        ListNode l1_1 = new ListNode(4) ;
        ListNode l1_2 = new ListNode(3) ;
        l1.next = l1_1 ;
        l1_1.next = l1_2 ;

        ListNode l2 = new ListNode(5) ;
        ListNode l2_1 = new ListNode(6) ;
        ListNode l2_2 = new ListNode(8) ;
        l2.next = l2_1 ;
        l2_1.next = l2_2 ;

        System.err.println(showList(addTwoNumbers( l1,  l2))) ;


    }

    public static  ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultNode = new ListNode(0)  ;

        ListNode l1_current = l1 ;
        ListNode l2_current = l2 ;
        ListNode preNode  = resultNode ;


        while (true){

            int scale = 0 ;
            int sum = l1_current.val + l2_current.val ;
            if(sum>9){
                sum = Integer.parseInt(String.valueOf(sum).substring(1,2)) ;
                scale += Integer.parseInt(String.valueOf(sum).substring(0,1)) ;

            }
            ListNode currentNode = new ListNode(sum+scale) ;
            preNode.next = currentNode ;
            preNode = currentNode ;

            if(l1_current.next == null ){
                preNode.next = new ListNode(scale) ;
                return resultNode ;
            }

            l1_current = l1_current.next ;
            l2_current = l2_current.next ;

        }


    }




    public  static String   showList(ListNode current){
        StringBuffer sb  = new StringBuffer();
        sb.append("(") ;
        while (true){
            sb.append(current.val+"->") ;
            if(current.next==null){
                return sb.substring(0,sb.length()-2)+")" ;
            }
            current = current.next ;
        }

    }

}



class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
 }

