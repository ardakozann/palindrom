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
		
		//Gelen sayiyi first ne last olarak ayırdım.
		String first = firstHalfOfString(sayi);
		
		int sayiInt = Integer.parseInt(sayi);
		int firstInt = Integer.parseInt(first);

		//Sayının ilk yarımını 1 arttır ve sonra polindrom bul, asıl sayıdan çıkart
		firstInt++;
		//Sayının ilk kısmını Stringe çevir
		first = String.valueOf(firstInt);
		//İşlemler dolayısıyla first içerisinde fazla olan sayılar silindi.
		String reverseFirst = checkBeforeCombining(sayi.length(), first);
			
		//Sayının ilk yarısını ve ilk yarısının terse çevrilmiş halini stringe ekliyorum
		String palindrom = first + reverseFirst;
		int palindromInt = Integer.parseInt(palindrom);
		
		//Palindrom sayısından asıl sayı çıkartılıp değer bulmak için
		String result = String.valueOf(palindromInt - sayiInt);
		
		return result;
	}
	
	//Eğer firstInt 1 arttırıldığında sayı uzunluğu değişirse onu silmek için kontrol ediyor.
	//Aynı zamanda sayi uzunluğunu tek sayi ve çift olmasına görede kontrol ediyor.
	//Sayıyı tersine çevirirken üstteki iki koşuldan dolayı sayının uzunluğu değişiyor
	//Uzunluğu değişince problem meydana gelmesinden dolayı burada düzeltildi.
	private String checkBeforeCombining(int sayiLength, String first){
		String temp = first;
		String reverseFirst = reverse(Integer.parseInt(first));
		if(sayiLength % 2 == 1) {
			if(temp.length() != first.length()) {
				reverseFirst = reverseFirst.substring(2);
			}
			else {
				reverseFirst = reverseFirst.substring(1);
			}
		}
		else {
			if(temp.length() != first.length()) {
				reverseFirst = reverseFirst.substring(1);
			}
		}
		return reverseFirst;
		
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
	
	//Sayının ilk yarımını string tipinde döndürüyor
	//Eğer sayının uzunluğu tek ise ortadaki sayıyı da first içerisine alıyor.
	private String firstHalfOfString(String str) {
		
		String half = ""; 
		if(str.length() % 2 == 0) {
			for(int i = 0; i < str.length()/2; i++) {
				half += str.charAt(i);
			}
		}
		else {
			for(int i = 0; i <= str.length()/2; i++) {
				half += str.charAt(i);
			}
		}	
		return half;
	}
	
	//Gönderilen sayıyı ters çeviriyor
	private String reverse(int strInt) {
		String str = String.valueOf(strInt);
		String reverse = ""; 
		for(int i = str.length()-1; i >= 0; i--) {
			reverse += str.charAt(i);
		}
		return reverse;
	}
	
}
