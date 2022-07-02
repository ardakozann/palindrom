# palindrom
Bir java methodu geliştireceksiniz, girdisi bir tane String olan, çıktısı da bir tane String olan.
Methodunuzun imzası şu şekilde olacak => String palindromeTamamlayiciSayi(String sayi)
Bu method girdi olarak iletilen String'in bir tam sayı olup olmadığını kontrol edecek, değilse istisna (exception) fırlatacak. Tam sayı ise de, bu sayı ile toplandığında oluşan sonucun palindrom olduğu bir sayı bulacak ve bu sayıyı String tipinde dönecek.

Örnek #1: Girdi olarak iletilen String => "araba". İstisna fırlatmalı
Örnek #2: Girdi olarak iletilen String => "123" Beklenen Çıktı= "8" (123+8 sonucunda oluşan 131 palindromdur.)

Dikkat edilmesi gereken noktalar/tüyolar:
•	Tek doğru cevap yok, herhangi bir doğru cevabı vermeniz yeterli. Örnek #2'de "8" doğru cevap ama "18" de doğru bir cevap çünkü 123+18 = 141 ve 141 sayısı da bir palindrom.
