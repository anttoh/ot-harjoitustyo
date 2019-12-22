# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on yksinkertainen sokkelo peli. Jokaisen pelin alussa sovellus luo satunnainen sokkelon, jonka läpi käyttäjän tulee ohjata hahmonsa mahdollisimman nopeasti. Käyttäjä näkee koko sokkelon kerralla ruudullaan.

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda käyttäjätunnuksen
  - tunnuksen tulee olla uniikki ja vähintään 3 merkkiä pitkä ja salasanan vähintään 5 merkkiä pitkä

- käyttäjä voi kirjautua sisään
  - kirjautuminen onnistuu, jos käyttätunnus ja salasana ovat oikein

### Kirjautumisen jälkeen

- käyttäjä siirtyy avausnäkymään (lobby)
- käyttäjä voi valita vaikeustason tai kirjoittaa haluamansa sokkelon leveyden ja korkeuden syötekenttiin
- käyttäjä voi valita korostetaanko tämän kulkema polku sokkelossa vai ei
- käyttäjälle näytetään tämän edellisten pelien tilastoja (keskiarvo, nopein ja hitain ratkais aika)
- käyttäjä voi aloittaa pelin, jolloin tämä siirtyy pelin näkymään
- käyttäjä voi kirjautua ulos

### Käyttäjän pelatessa

- pelin alkaessa, sovellus luo satunnaisen sokkelon ja ajastin käynnistyy, kun sokkelo on valmis
- käyttäjä voi ohjata hahmonsa sokkelon läpi
- käyttäjä voi poistua pelistä ja palata avausnäkymään painamalla exit nappia, jolloin pelin tulos ei talnnenu
- käyttäjän saavuttua sokkelon loppuun ajastin pysähtyy ja vaihtuu näkymä takaisin edelliseen (lobby), sekä pelin tulos tallentuu

## Jatkokehitysideoita

- pelimoodi, jossa aika laskee alaspäin ja käyttäjän tulee selvittää sokkelo ennen ajajn loppumista
- käyttäjä voi itse valita pelin pituuden (kuinka monta sokkeloa ratkoa peräkkäin ilman että ajastin pysähtyy)
- lisää tilastoja esim. lista päivän tai kuukauden nopeimmista sokkeloiden ratkojista kategoria kohtaisesti
- tilastoja käyttäjän kehityksestä esim. vertailemalla eri ajanjaksojen ratkaisunopeuksien keskiarvoja
- optimaalisen ratkaisun ajan laskeminen ja sen vertaaminen käyttäjän aikaan
- sokkelon ratkaisijaalgoritmin luominen jonka avulla sokkelon lyhin polku voidaan näyttää käyttäjälle pelin päätyttyä tai kun käyttäjä painaa exit nappia pelissä
