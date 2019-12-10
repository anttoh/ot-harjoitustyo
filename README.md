# MazeGame

Sovellus on yksinkertainen sokkelo peli. Jokaisen pelin alussa sovellus luo satunnainen sokkelon, jonka läpi käyttäjän tulee ohjata hahmonsa mahdollisimman nopeasti. Käyttäjä näkee koko sokkelon kerralla ruudullaan.
(Sovellus on syksyn 2019 ohjelmistotekniikan harjoitustyöni)

## Dokumentointi
[Työaikakirjanpito](./dokumentointi/tuntikirjanpito.md)

[Vaatimusmäärittely](./dokumentointi/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](./dokumentointi/arkkitehtuuri.md)

## Releaset

[Viikko 5](https://github.com/anttoh/ot-harjoitustyo/releases/tag/viikko5)
[Viikko 6](https://github.com/anttoh/ot-harjoitustyo/releases/tag/viikko6)


## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _MazeGame-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](./MazeGame/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
