public class Producer extends Thread {
    buffer buf;
    int n;

    //Function Check the prime Numbers
    static boolean isPrime(int n){
        //since 0 and 1 is not prime return false.
        if(n==1||n==0)return false;

        //Run a loop from 2 to n-1
        for(int i=2; i<n; i++){
            // if the number is divisible by i, then n is not a prime number.
            if(n%i==0)return false;
        }
        //otherwise, n is prime number.
        return true;
    }

    //send buffer and max n to producer
    public Producer(buffer buf,int n) {
        this.buf = buf;
        this.n=n;
    }
    public void run() {
        for (int i = 1; i <= n; i++) {
            if(isPrime(i)){
                buf.produce(i);
            }
        }
        //Boolean show if the producer finished working in buffer
        buf.is_finished=true;
    }}
