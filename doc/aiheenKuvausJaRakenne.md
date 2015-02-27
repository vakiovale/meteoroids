# Aiheen kuvaus ja rakenne

### Aihemäärittely
Klassisen Asteroids-pelin klooni **Meteoroids**. Pelissä lennetään avaruusaluksella ja pyritään tuhoamaan lähestyviä meteoriitteja ampumalla niitä. Pelin tavoitteena on selviytyä mahdollisimman pitkään hengissä ja kerätä mahdollisimman paljon pisteitä tuhotuista meteoriiteista. Pelaajan on samalla myös suojeltava planeettaa meteoriittien törmäyksiltä, jotta planeetan asukkaat selviäisivät hengissä. Mikäli planeetta tuhoutuu, peli päättyy. Pelaajien parhaat tulokset tallentuvat highscore-listalle. Pelaaja etenee tasolta toiselle, mikäli hän pystyy pitämään kunkin tason planeetan/planeetat elossa.

**Käyttäjät:** Pelaaja

**Pelaajan toiminnot:**
* pelin aloittaminen
  * aloittaa uuden pelin alusta pisteiden ollessa nolla
* pelin pelaaminen
  * pelaaja ohjaa näppäimistöllä avaruusalusta (liikkuminen/ampuminen)
  * pelaaja ampuu meteoriitteja ennen kuin ne tuhoavat planeetan ja peli päättyy
* highscore-listan katsominen
* pelin lopettaminen

### Ohjelman rakenne
Ohjelmassa on lukuisia eri kontrolliluokkia, joilla jokaisella on jokin tietty tehtävä. Ohjelman pääpelisilmukka käynnistää muut pääkontrollerit, joka taas pitää huolta muista kontrollereista. Kontrollerit jakautuvat eri paketteihin, joiden päätehtävät jakaantuvat seuraavasti:

* pelioliot (asteroidit, planeetat, avaruusalukset) sekä niiden toiminta (esim. aluksen ampuminen)
* fysiikkakontrolleri, olioiden liikuttaminen ja törmäykset
* game states ja tasojen kontrollointi (main menu, highscores, yms.)
  * levelit eli tasot – jokaisella tasolla on tiedot tason toiminnasta
* grafiikan piirtäminen ja tekstuurit
* input/output (näppäimistön tarkkailu)
* apuohjelmat (kello, top ten -lista, yms.)

Kontrolleriluokat pitävät huolen muun muassa olioiden luomisesta ja tuhoamisesta sekä pelaajan toimintojen kuuntelemisesta. Kontrolliluokat taas sisältävät lukuisia muita peliolioita, joita ne ohjaavat.

Itse pelissä olevat pelioliot jakaantuvat useampaan eri pakettiin riippuen niiden toiminnasta. Fysiikkapelioliot reagoivat toisten olioiden törmäyksiin sekä mahdollisesti painovoimaolioiden luomiin painovoimakenttiin.

Tavoitteena on ollut luoda mahdollisimman helposti muokattava peli, johon voi vaivatta lisätä uusia toiminnalisuuksia ja muokata olemassaolevia. Uusien tasojen lisääminen on helppoa ja pelin muokkaaminen esimerkiksi kaksinpeliksi onnistuu.
