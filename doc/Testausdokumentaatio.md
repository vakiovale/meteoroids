# Testausdokumentaatio

## Yleistä
Ohjelmassa on testattu yksikkötestauksella lähinnä gameobjects-paketin luokkia. Pelissä on kuitenkin paljon komponentteja, jotka perustuvat fysiikkaan ja grafiikkaan ja näiden testaaminen yksikkötestauksen avulla osoittautui hankalaksi.

## Käsin testaus

### Fysiikkaluokat/kontrollerit
Pelissä on paljon laskentaa, joka perustuu fysiikan lakeihin. Monimutkaisten painovoimakenttien ja törmäysten testaaminen oli erittäin hankalaa yksikkötestauksen avulla ja päädyinkin näitä testaamaan käsin. Projektin alussa lähdin liikkeelle luomalla vain pieniä fysiikan osa-alueita kerrallaan ja testasin, että näiden toiminta vaikutti järkevältä useissa eri tilanteissa.

Alussa loin ruudulle yksittäisiä pisteitä, joihin lisäsin eri suuntaisia voimavektoreita ja katsoin, että liike lähti liikkeelle voiman osoittamaan suuntaan. Kun totesin, että kappaleen liikkeellelähtiessä vauhti pysyi vakiona, pystyin totemaaan, että fysiikkamoottorin ensimmäinen vaihe toimii – eli se, että liike pysyy avaruudessa olevilla kappaleilla vakiona.

Sueraavissa vaiheissa lisäsin ruudulle planeettoja, jotka lisäsivät lentäviin pisteitä painovoimavektoreita ja useilla testeillä totesin, että kappaleiden liike näytti riittävän realistiselta peliä varten.

Samoja menetelmiä käyttäen sain kappaleet törmäämään toisiinsa riittävän uskottavasti ja kun koin testanneeni tarpeeksi käsin fysiikan toimintaa, löin lukkoon, etten enää koske fysiikkakontrollien toimintaan. Tällä tavalla pidin huolen, että fysiikan toiminta pysyi ehjänä myös ohjelman tekemisen viimeisille päiville saakka. Eristämällä fysiikka muista luokista helpotti tässä asiassa.

### Grafiikka & käyttöliittymä
Ohjelmassa piirretään paljon asioita ruudulle, kuten taustalla näkyvä tähtitaivas, aluksen ja asteroidien tulilieskat, aluksen tutka ym. Näiden testaaminen oli myös hieman hankalaa. Käsin testausta helpotti suuresti se, että luokat sai riittävän hyvin eristettyä toisistaan ja, että niiden vastuualueet pysyivät riittävän pieninä. Yleensä loin yhden luokan kerrallaan ja testasin sitä käsin niin kauan, että koin sen toimivan riittävän hyvin. Esimerkiksi tutkan testaaminen osoittautui erittäin hankalaksi. Ainoaksi tavaksi onnistuin keksimään kirjoittamalla aina pienen määrän koodia kerrallaan ja katsoin miten se vaikutti tutkan toimintaan.

Käyttöliittymä ja erityisesti näppäimistöllä valikoissa liikkuminen tuli testattua myös useilla tunneilla käsin testausta. Pyrin painamaan monia eri näppäimiä samanaikaisesti ja rämppäsin nuolia ylös ja alas, jotta löysin mahdollisia bugeja, joita sitten virhetilanteissa poistin. Järkevää tapaa tähän en keksinyt, mutta luotin kärsivällisen ja pitkäjänteisen testaamisen saavan aikaan riittävän vakauden.

###Kontrolliluokat yleisesti
Kontrolliluokkien toiminnat olivat melko monimutkaisia ja monilla kontrollereilla oli riippuvuuksia muista pelin luokista. Monet kontrolliluokat vain ohjasivat komentoja toisille kontrolliluokille ja olioille, mutta silti niistä löytyneiden bugien määrä tuntui yllättävän vähäiseltä. Uskon tämän johtuvan siitä, että monet luokat pysyivät riittävän pieninä. Testaus oli näissäkin melko hankalaa, mutta lopullinen toimivuus on tuntunut olevan melko hyvällä tasolla.

###Apuohjelmat
Ohjelmassa on käytetty myös joitakin itsetehtyjä apuohjelmia, joiden testaus oli tehtävä käsin. Esimerkiksi ajanottoon liittyvät luokat kuten Timer ja GameTimer oli helpointa testata käsin ja katsoa kuinka aika käyttäytyy erilaisissa tilanteissa (kuten Pause-ruudun aikana, tai kun aika tippuu nollaan).

## Lopuksi
Ohjelma kasvoi lopulta niin isoksi, että huomasin ettei pelkästään yksikkötestaus riitä tällaiseen projektiin. Tulevaisuudessa täytyy ottaa selvää erilaisista testaustavoista ja kuinka niitä voi hyödyntää eri tilanteisiin. Positiivista oli huomata myös se, että melkein jokaisen tehdyn yksikkötestauksen myötä tuli tehtyä muutoksia koodiin, jotta ohjelmasta tuli entistäkin vakaampi.
