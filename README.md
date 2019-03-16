# Give-me-more-subscriptions-Java-Language

Implementation of 3 types of cache, who use 3 different methods of storing objects:
</br>-LRU (least recently used) ;
</br>-FIFO (first in first out);
</br>-LFU (Least Frequently Used);

Pislari Vadim -> Facultatea Automatica si Calculatoare -> Universitatea Politehnica Bucuresti

                                                 (Romanian language)
                                               -------DESCRIERE-------
  Memoria cache este o colecție de date ce sunt o "copie la indigo" a valorilor originale stocate altundeva sau calculate mai devreme, 
unde operația de aducere din memorie a datelor originale este costisitoare (datorită timpilor mari de acces la memorie) sau costul 
recalculării acestora este mare, în comparație cu cel al citirii acestora din cache (o memorie mult mai rapidă și mai ușor de accesat). 
Cu alte cuvinte, un cache este o arie temporară de stocare unde datele utilizate în mod frecvent pot fi depozitate pentru un acces 
rapid la acestea. Odată ce datele sunt stocate în cache, în viitor vor fi luate de aici și utilizate decât să se încerce readucerea 
datelor originale sau recalcularea acestora, astfel încât timpul mediu de acces este mai mic

  Asa cum a fost mentionat mai sus, dimensiunea unui cache este mult mai mică în comparație cu cea a memoriei principale, iar în cazul
în care un nou element se aduce in cache, iar dimensiunea acestuia deja atinge capacitatea maxima, unul din obiectele deja existente
in cache va fi inlocuit cu noul element. 
  
  1.)Tipuri de cache
Exista diverse metode de a decide ce element va fi eliminat din cache (numai atunci cand acesta este plin).Se realizeaza implementarea a
2 tipuri de cache, ce folosesc 2 metode diferite de stocare a obiectelor: 

  ● LRU (least recently used) - elementul cel mai putin utilizat va fi inlocuit cu noul obiect (O modalitate, care nu e impusa, de 
implementare ar fi sa retineti un timestamp dat de un contor care se incrementeaza cand aveti operatii de ADD sau GET, iar la
eveacuarea din cache se va alege elementul cu timestamp-ul cel mai mic)
  ● FIFO (first in first out) - elementul de la inceputul cozii va fi eliminat, iar noul element va fi inserat la finalul cozii. 
  ● LFU (Least Frequently Used) -  de fiecare data cand un obiect trebuie eliminat din cache, se va elimina cel care a fost accesat
de cele mai putine ori de cand se afla in cache. Daca toate obiectele care se afla in cache la un momentdat au fost accesate de
un numar de ori egal, atunci cel care va fi eliminat va fi primul care a fost adaugat (fiind cel mai vechi). 
  
  Pentru toate tipurile de cache se considera o dimensiune maxima (un numar de obiecte ce pot fi retinute la un moment dat), 
iar in momentul in care aceasta limita este atinsa, in functie de cache-ul utilizat, la introducerea unui nou obiect se folosesc 
regulile mentionate mai sus. Se defineste o interfata “Cache” ce declara doua metode: add si remove (adaugare si eliminare din cache).
Ulterior, se implementeaza trei clase: LRUCache, FIFOCache si LFUCache, in care fiecare dintre cele doua metode contin implementarea 
particulara pentru tipurile de cache. 
 
  2.)Memoria principala 
Memoria principala este reprezentata sub forma unui array ce contine toate obiectele ce vor fi adaugate la un moment dat.
Chiar daca un obiect se gaseste in cache, acesta este prezent si in memoria principala. 
 
  3.)Tipuri de obiecte 
Obiectele folosite de cele 2 memorii sunt de 3 tipuri. Se porneste de la clasa Subscriptie (clasa abstracta) si se obtin trei clase: 
Free (mosteneste clasa abstracta Subscriptie), Basic (mosteneste clasa Free) si Premium (mosteneste clasa Basic). Toate tipurile de 
subscriptie contin un nume (sir de caractere) ce reprezinta numele titularului ce are subscriptia respectiva. 
 
  4.)Subscriptii pentru obiecte 
Fiecare subscriptie contine numarul de cereri pana la epuizarea celei de tipul Premium si Basic, iar cea Free este nelimitata. 
In cazul operatiilor de tip GET numele scris este fix tipul subscriptiei (Premium, Basic sau Free). Asta inseamna ca pentru fiecare 
subscriptie de tip Premium, se mentioneaza un numar ce reprezinta numarul maxim de accesari (GET). In momentul in care acest numar
este epuizat, subscriptia devine automat Basic, analog devenind ulterior Free care poate fi accesata de un numar nelimitat de ori. 

  5.)Operatii  
Sunt implementate doua operatii de lucru cu memoria.
● ADD nume_obiect cereri_basic [cereri_premium] - la fiecare adaugare de nou element, acesta este inserat numai in memoria principala,
nu si in cache, iar in cazul in care un element exista deja in memoria principala sau in ambele, se suprascrie in memoria principala si
este eliminat din cache. Parametrul cereri_basic este obligatoriu, iar cereri_premium este optional. 
● GET nume_obiect - aceasta operatie intoarce un intreg in functie de apartenenta obiectului la cache: 0 - obiectul se gaseste in cache 
1 - obiectul se gaseste doar in memoria principala. Ulterior acestei operatii, obiectul este mutat si in cache 2 - obiectul nu a fost
gasit in memoria principala.
 

                                            ------Implementare------
Metoda de implementare a proiectului a fost organizarea structurata  si stocarea informatiilor in mai multe 
clase care contin arraylist-uri si hashmap. Hashmap-ul a fost utilizat pentru gasirea rapida cu ajutorul 
unei chei a un nod cu o anumita entitate. Dificultatea acestei teme consta in repartizarea informatiei
din baza de date in clase si gasirea legaturilor dintre tipul atributului si atribut, nod si instanta etc.
Pentru a se realiza tema au fost create clasele cu urmatoarele functionalitati si legaturi cu alte clase:
    1.Tema2 - clasa de baza in care se face citirea din fisier si apelarea metodelor din alte clase.
    2.Database - clasa care reprezinta baza de date. Ea contine un arraylist cu toate instantele si hashmap
cu toate nodurile. Metodele sunt comenzile din enuntul temei.Pe langa aceasta mai este creata metoda care
adauga cate o instanta in toate nodurile necesare pe baza rf-ului. 
    3.Instances - clasa care reprezinta toate inserarile dintr-un anumit nod. Ea contine un array_list
de inserari.
    4.Insertions - clasa care reprezinta o inserare cu mai multe valori celule.
    5.Cell - clasa care pastreaza cea mai mica unitate a bazei de date, un atribut si valoarea sa. Pentru a 
se realiza atribuirea valorii int string si float au fost create 3 variabile care se initializeaza in 
functie de tipul atributului.
    6.Nods - clasa care este o reprezentare a unui nod si are functionalitatea de a pastra toate tipurile
si denumirile de atribute din baza de date si este folosita pentru a gasi acest atribut in momentul in care
se adauga o inserare. Contine un arraylist de argumente
    7.Nods_Arguments - clasa care contine tipul unui atribut si numele sau.
