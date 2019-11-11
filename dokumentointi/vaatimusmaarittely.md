# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on yksinkertainen sokkelo peli. Jokaisen pelin alussa luodaan
käyttäjän määrämän kokoinen satunnainen sokkelo. Käyttäjältä sokkelon 
ratkaisemiseen kulunut aika tallennetaan tietokantaan.

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda käyttäjätunnuksen
  - tunnuksen tulee olla uniikki ja salasanan vähintään 3 merkkiä pitkä

- käyttäjä voi kirjautua sisään
  - kirjautuminen onnistuu, jos käyttätunnus ja salasana ovat oikein

### Kirjautumisen jälkeen

- käyttäjä siirtyy avausnäkymään (lobby)
- käyttäjä näkee tilastot nopeimmista sokkeloiden ratkaisuista (leaderboard)
- käyttäjä voi aloittaa pelin, jolloin tämä siirtyy pelin näkymään

### Käyttäjän pelatessa

- pelin alkaessa, sovellus luo satunnaisen sokkelon ja ajastin käynnistyy
- käyttäjä voi ohjata hahmoaan sokkelossa
- käyttäjän saavuttua sokkelon loppuun ajastin pysähtyy ja vaihtuu näkymä takaisin edelliseen (lobby)
