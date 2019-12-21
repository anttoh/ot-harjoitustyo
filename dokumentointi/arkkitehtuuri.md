## Rakenne

Ohjelman pakkauskaavio on seuraava:

<img src="./kuvat/luokkakaavio.png">

(HUOM. Sovellukseen on lisätty luokka Difficulty ja DifficultyDao, joiden välinen yhteys on samanlainen kuin User ja UserDaon sekä kuvan Game ja GameDaon. Difficultyn ja Gamen välinen suhde one seuraava: Game *-1 Difficulty. Refaktoroinnin myötä kuvan MazeGenerator on nykyään LayaoutGenerator ja Game on nykyään Result ja GameDao on ResultDao. Kuvan Mazen ja LayoutGeneratorin välisen viivan molemmissa päissä kuuluisi olla nro. 1. Kuvan nuoli MazeGameUI:sta MazeGameService:n on hieman liian lyhyt, mutta se siis osoittaa MazeGameServiceen, ei koko mazegame.domain pakkausta)

Pakkaus _mazegame.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän _mazegame.domain_ sovelluslogiikan ja _mazegame.dao_ tietojen pysyväistallennuksesta vastaavan koodin, sekä tietokannan alustavan koodin.

## Käyttöliittmymä

Ohlejmalla on kolme eri näkymää:
- kirjautuminen/uuden käyttäjän luonti
- lobby(pelin avausnäkymä)
- peli

Näkymät on toteuttettu omina luokkinaan. Nämä luokat luovat ja palauttavat Scene-olion, joina näkymät ollaan toteutettu. Vain yksi näistä Scene-olioista on asetettu sovelluksen Stage-olioon, eli vain yksi näkymä näytetään käyttäjälle kerralla. Näkymän vaihtuessa näiden luokkien medodia, joka luo ja palauttaa Stage-olion, kutsutaan uudelleen. Näkymä päivittyy kun siitä siirrytään pois ja sitten takaisin. Käyttöliittymän luokka _MazeGameUi_ sisältää stagen ja metodit, joita kutsumalla näkymä vaihtuu esim. LobbyScene kutsuu MazeGameUi:n metodia startGame, joka vaihtaa näkymän pelin näkymään.

Käyttöliittymän koodi ja sovelluslogiikan koodi ollaan pyritty eristämään mahdollisimman paljon. Luokka mazegame.domain.MazeGameService tarjoaa käyttöliittymälle kaikki tarvittavat metodit sovelluslogiikan hoitamiseen esim. hahmon liikuttaminen, uuden käyttäjän luomiseen jne. Luokan MazeGameUi metodit kutsuvat näitä metodeja sen lisäksi, että ne vaihtavat näkymää.

## Sovelluslogiikka



## Kirjautumislogiikkaa
<img src="./kuvat/loginSequence.png">
