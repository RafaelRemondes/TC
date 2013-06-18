package OTP;

public class Operations {

	public static String xor(String a, String b){
		StringBuilder result = new StringBuilder();
		int aValue = 'A', valInt;
		char[] cA = a.toCharArray(), cB = b.toCharArray();
		
		for(int i = 0; i < cA.length; i++){
			valInt = ((cA[i] - aValue) + (cB[i] - aValue)) % 26;
            if (valInt < 0) valInt = -valInt;
            valInt = valInt + aValue;
            result.append((char)valInt);
		}
		
		return result.toString();
	}
	
	
}
