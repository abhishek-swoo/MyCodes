
package spoj;

import java.math.BigInteger;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class BISHOPS
{
	public static void main (String[] args) throws IOException
	{
		BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<1024;i++){
			String st=obj.readLine();
//			if("".equals(st))
//			    break;
			BigInteger bb=new BigInteger(st);
			if(bb.equals(BigInteger.ONE))
			System.out.println(1);
			else{
				bb=bb.subtract(BigInteger.ONE);
				bb=bb.multiply(BigInteger.valueOf(2));
				System.out.println(bb);
			}
		}
	}
}