public class FindPalindrom {
	
	String palindromeTamamlayiciSayi(String sayi) {
		
		//İlk Kontroller
		isNumeric(sayi);
		if(sayi.length() == 1) {return "0";}
		if(firstTimeCheckIfPalindrome(sayi)) {return "0";}
		if(sayi.length() == 2) {
			int firstNumber = Integer.valueOf(sayi.charAt(0));
			int lastNumber = Integer.valueOf(sayi.charAt(1));
			if(firstNumber > lastNumber) {
				return String.valueOf(firstNumber-lastNumber);
			}
			else {
				return String.valueOf(11+firstNumber-lastNumber);
			}
		}
		
		//Kullanılacak değişkenler
		StringBuilder temp = new StringBuilder (sayi);
		StringBuilder result = new StringBuilder();
		StringBuilder fromBeforeTransaction = new StringBuilder();
		
		//result değişkenini 0lardan oluşan sayi uzunluğunda bir string yapıyorum.
		//Recursive yapıdan dolayı önceki metottan kalmaları toplamak için fromBeforeTransaction değişkeni yaptım.
		for(int i=0; i < sayi.length(); i++) {
			result.append('0');
			fromBeforeTransaction.append('0');
		}
		
		for(int i=0; i < temp.length()/2; i++) {
			//Uzunluğu tek olan sayılarda ortadaki sayıya gelirse kontrol etmeyecek.
			if(i == temp.length()-1-i) {
				break;
			}

			//Eğer sol yarımdaki rakam sağ yarımdaki rakamdan büyükse
			//direkt soldaki rakamı sağa atama ypaıyor.
			if(temp.charAt(i) > temp.charAt(temp.length()-1-i)) {
				
				//Aradaki farkı result değişkeninde length()-1-i indeksine setliyorum
				int temp1 = substraction(temp.charAt(i), temp.charAt(temp.length()-1-i));	
				
				result.setCharAt(temp.length()-1-i, intToChar(temp1));
				temp.setCharAt(temp.length()-1-i, temp.charAt(i));
			}
			
			//Eğer sol yarımdaki rakam sağ yarımdaki rakamdan küçükse
			else if(temp.charAt(i) < temp.charAt(temp.length()-1-i)) {	
				
				//10 ile soldaki rakamı toplayıp, toplamdan sağdakini çıkartıyorum.
				int temp1 = 10 + substraction(temp.charAt(i), temp.charAt(temp.length()-1-i));
				
				//Soldaki rakama sağa atama yapıyorum.
				temp.setCharAt(temp.length()-1-i, temp.charAt(i));
				
				//sağ yarımdaki atama yaptığımın 1 solundaki rakamı 1 arttırıyorum
				//eğer onunda solunda 1 varsa diye recursive metota gönderiyorum.
				temp = changeNextTo(temp, temp.length()-2-i);

				//Eğer sayi değişkeninin sol yarımında değişiklik varsa 
				//palindromeTamamlayiciSayi metotu recursive yapıda kullanarak
				//Sol yarımda değişmiş halini yeniden metota gönderiyorum ve
				
				if(!sayi.substring(0, sayi.length()/2).equals(temp.substring(0, sayi.length()/2))){
					fromBeforeTransaction = new StringBuilder(palindromeTamamlayiciSayi(temp.toString()));

					//Gelen sonucu farklarını yazdığım string ile topluyorum.
					result = sum(result, fromBeforeTransaction, result.length()-1);

				}
				result.setCharAt(temp.length()-1-i, intToChar(temp1));
			}
		}
		
		//uzunluğu çift olan (!) ve sol yarımın en sağındaki sayı 1 artırılırsa onu kontrol etmiyor. 
		//Onun için elle yapıyorum. Eğer sol yarımın en sağındaki rakam orjinaline göre değişmişse
		//cevap olarak döndüreceğim palindrom olması için gereken sayının aynı indeksteki değerine 1 ekliyorum.
		if(sayi.length() % 2 == 0 && sayi.charAt((sayi.length()/2)-1) != temp.charAt((temp.length()/2)-1)) {
			result = changeNextTo(result, result.length()/2);
		}
		
		return removeZeroCharacter(result);
	}
	
	private String removeZeroCharacter(StringBuilder temp) {
		int index = 0;
		for(int i = 0; i < temp.length(); i++) {
			if(temp.charAt(i) != '0') {
				index = i;
				break;
			}
		}
		return temp.substring(index);
	}
	
	//Eğer 9 olan bir sayı varsa 1 solundaki kontrol ederek ekleme işlemi yapıyor.
	//index olarak gönderilen sayı, çalışılan indexin bir soldaki indexi
	private StringBuilder changeNextTo(StringBuilder str, int index) {
		
		int temp = Character.getNumericValue(str.charAt(index));
		temp++; 
		
		if(temp == 10) {
			str = changeNextTo(str, index-1);
			temp = 0;
		}
		 
		str.setCharAt(index, intToChar(temp));
		return str;
	}
	
	//char tipindeki rakamlar arasındaki farkı int tipinden döndürüyor.
	private int substraction(char left, char right) {
		return Character.getNumericValue(left) - Character.getNumericValue(right);
	}
	
	private char intToChar(int number) {
		String temp = String.valueOf(number);
		return temp.charAt(0);
	}
	
	//result ve fromBeforeTransaction, palindrome için gereken farkların toplamları
	private StringBuilder sum(StringBuilder result, StringBuilder fromBeforeTransaction, int index) {
		int temp1, temp2;
		temp1 = Character.getNumericValue(result.charAt(index));
		temp2 = Character.getNumericValue(fromBeforeTransaction.charAt(index));
		temp1 = temp1 + temp2;
		
		
		if(temp1 >= 10) {
			result = changeNextTo(result, index);
			result.setCharAt(index, String.valueOf(temp1).charAt(1));
		}
		else {
			result.setCharAt(index, String.valueOf(temp1).charAt(0));
		}
		if(index > 0) {
			result = sum(result, fromBeforeTransaction, index-1);
		}
		
		return result;
	}
	
	//Gönderilen sayı eğer palindrom ise direkt 0 döndürmek için
	private boolean firstTimeCheckIfPalindrome(String str) {
		for(int i=0; i <= str.length()/2; i++) {
			if(str.charAt(i) != str.charAt(str.length()-1-i)) {
				return false;
			}
		}
		return true;
	}
	
	//Sayı kontrolü
	private boolean isNumeric(String sayi) {
		if(!sayi.chars().allMatch( Character::isDigit)) {
			throw new NumberFormatException("Girilen deger 0'dan kucuk veya integer turunde degil.");
		}
		return true;
	}
}
