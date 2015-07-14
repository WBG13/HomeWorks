public class Boxes {
 
    public static void main(String a[]){
        int[] arr1 =  new int [10];
		for(int x=0; x < arr1.length; x++){
			arr1[x]=(int) Math.round(Math.random()*100);}
                       int[] arr2 = doInsertionSort(arr1);
        for(int i:arr2){
            System.out.print(i);
            System.out.print(", ");}}
		
    public static int[] doInsertionSort(int[] input){
         
        int temp;
        for (int x = 1; x < input.length; x++) {
            for(int j = x ; j > 0 ; j--){
                if(input[j] < input[j-1]){
                    temp = input[j];
                    input[j] = input[j-1];
                    input[j-1] = temp;
                }
            }
        }
        return input;
    }
}
