10. Laboratorijas darbs - Darbs ar teksta failiem: Faila modificēšana 
    Uzdevums: 
        Izstrādāt programmu, kas modificē teksta failu, formatējot tekstu dotā failā sekojošā veidā:   
        Izlīdzināt visas teksta rindas pēc centra (līdzīgi tam, kā to dara Word pēc komandas Format -> Paragraph -> 
        Center Text). Teksta formatēšanai palieliniet atstarpes simbolu skaitu rindas sākumā un beigās. Rindas beigās 
        var būt par vienu atstarpes simbolu mazāk (obligāti pievienojiet atstarpes arī rindās beigās). 
        Tukšās rindas izdzēst, katrai rindai ir jāsākas ar lielo burtu. 
        Starp katrām divām rindām iestarpināt tukšu rindu. 
        Teksta rindu garumu rezultējošā failā ir jāizvēlas ievērojot garākās teksta rindas garumu. 
        Piemēram, fails satur tekstu: 
            qw ert yui, 
            op asd.  
            fg hjk l 
            z xcv. 
            bbb nnn mmm. 
        Tad tekstu failā ir jāpārveido sekojošā veidā: 
            Qw ert yui, 
            Op asd.  
            Fg hjk l 
            Z xcv. 
            Bbb nnn mmm. 
        Izstrādāto programmu pārbaudīt ar testiem github portālā. Programmas pārbaudei izmantojiet failus 
        Text1.txt, Text2.txt un Text3.txt, kas atrodas github repozitorija un ORTUSā. Failus, kas atrodas mapē "files" 
        (github repozitorijā) modificēt aizliegts. 
        Prasības lietotāja saskarnei: 
            Programmai ir jāapstrādā vaicājumi print, format un exit. Kamēr lietotājs neievadīs komandu exit 
            programmai bezgalīgā ciklā ir jāievada komandas un jāizpilda atbilstošas darbības. Visu darbību apraksts un 
        komandu formāts ir parādīts sekojošā tabulā: 
            print:
                Komandas formāts: 
                    print filename
                kur:
                    filename ir faila nosaukums (ar ceļu).  
                    Svarīgi: programmas kodā ievadītu faila nosaukumu nerediģēt, pie ievadīta no 
                    tastatūras faila nosaukuma nepievienot faila tipu (.txt) vai mapes nosaukumu.
                Darbības: 
                    Izvadīt norādītā faila saturu ekrānā.
            format:
                Komandas formāts: 
                    format filename
                kur: 
                    filename ir faila nosaukums (ar ceļu).  
                    Svarīgi: programmas kodā ievadītu faila nosaukumu nerediģēt, pie ievadīta no 
                    tastatūras faila nosaukuma nepievienot faila tipu (.txt) vai mapes nosaukumu.
                Darbības: 
                    Izmainīt faila saturu atbilstoši uzdevumam.
            exit:
                Pārtraukt programmas izpildi.
        Speciālās prasības programmai: 
            • Piešķirt klasei nosaukumu Main. 
            • Neizmantot operatoru package. 
            • Ievades kļūdas atļauts neapstrādāt. 
            • Programmas sākumā izvietot komentāru, kas satur programmas autora apliecības numuru, vārdu, 
              uzvārdu un grupas numuru.  
        Programmas, kas modificē failu,  piemērs atrodas ORTUSā, failā Main.java. 
        Teksta faila paraugi atrodas ORTUSā, sk. failus Text1.txt, Text2.txt, Text3.txt.