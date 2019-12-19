# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on yksinkertainen sokkelo peli. Jokaisen pelin alussa sovellus luo satunnainen sokkelon, jonka läpi käyttäjän tulee ohjata hahmonsa mahdollisimman nopeasti. Käyttäjä näkee koko sokkelon kerralla ruudullaan.

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda käyttäjätunnuksen
  - tunnuksen tulee olla uniikki ja salasanan vähintään 5 merkkiä pitkä

- käyttäjä voi kirjautua sisään
  - kirjautuminen onnistuu, jos käyttätunnus ja salasana ovat oikein

### Kirjautumisen jälkeen

- käyttäjä siirtyy avausnäkymään (lobby)
- käyttäjä voi aloittaa pelin, jolloin tämä siirtyy pelin näkymään
- käyttäjä voi kirjautua ulos

### Käyttäjän pelatessa

- pelin alkaessa, sovellus luo satunnaisen sokkelon ja ajastin käynnistyy, kun sokkelo on valmis
- käyttäjä voi ohjata hahmonsa sokkelon läpi
- käyttäjän saavuttua sokkelon loppuun ajastin pysähtyy ja vaihtuu näkymä takaisin edelliseen (lobby) 

## Jatkokehitysideoita

- pelimoodi, jossa aika laskee alaspäin ja käyttäjän tulee selvittää sokkelo ennen ajajn loppumista
- käyttäjä voi itse valita pelin pituuden (kuinka monta sokkeloa ratkoa peräkkäin ilman että ajastin pysähtyy)
- erillaisten tilastojen luonti esim. lista päivän tai kuukauden nopeimmista sokkeloiden ratkojista kategoria kohtaisesti
- tilastoja käyttäjän kehityksestä esim. vertailemalla eri ajanjaksojen ratkaisunopeuksien keskiarvoja
- optimaalisen ratkaisun ajan laskeminen ja sen vertaaminen käyttäjän aikaan.
