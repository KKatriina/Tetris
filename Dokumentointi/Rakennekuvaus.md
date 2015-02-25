#Rakennekuvaus
Pelissä on kerrallaan yksi Palikka-olio, joka edustaa pelikentän poikki liikkuvaa palikkaa. Palikka koostuu 2-5 Pala-oliosta, jotka edustavat neliön muotoisia palikan osia. Palat kuitenkin ovat olemassa myös palikasta erillisinä: kun palikka pysähtyy, palat liitetään pelikentän pohjan paloihin. LisaPala-luokka laajentaa Pala-luokkaa: Pala tuntee vain koordinaattinsa, mutta LisaPala tietää lisäksi, mihin palaan ja mistä suunnasta se on kiinnitetty. Yhtä lukuun ottamatta kaikki Palikka-olion Pala-oliot ovat LisaPaloja.

Kayttoliittyma-luokka luo käyttöliittymän ja siihen kuuluvan Nappaimistonkuuntelijan ja Pelikentan. 
Pelikentta laajentaa JPanel-luokkaa ja piirtää alustalle peliin kuuluvan palikan ja palan.
Nappaimistonkuuntelija kuuntelee nuolinäppäimiä ja kutsuu jokaisen nuolinäppäimen painalluksen jälkeen
Logiikan metodeja sekä Pelikentan paivita-metodia. 
Ajastin kuuntelee itseään, toisin sanoen suorittaa actionPerformed-metodin määrätyn aina määrätyn ajan kuluttua.
Ajastin kutsuu Logiikka-luokan metodeja jokaisella actionPerformed-metodin suorituskerralla.
Logiikka-luokka hoitaa peliin kuuluvan palikan ja palojen liikuttelun pelikentällä.

