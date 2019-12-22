## Rakenne

Ohjelman pakkauskaavio on seuraava:

<img src="./kuvat/luokkakaavio.png">

(HUOM. Sovellukseen on lisätty luokka Difficulty ja DifficultyDao, joiden välinen yhteys on samanlainen kuin User ja UserDaon sekä kuvan Game ja GameDaon. Difficultyn ja Gamen välinen suhde on samanlainen kuin Gamen ja Userin välinen suhde. Pelillä on yksi vaikeustaso, mutta sama vaikeustaso voi olla usealla pelillä. Refaktoroinnin myötä kuvan MazeGenerator on nykyään LayaoutGenerator ja Game on nykyään Result ja GameDao on ResultDao. Kuvan Mazen ja LayoutGeneratorin välisen viivan molemmissa päissä kuuluisi olla nro. 1. Kuvan nuoli MazeGameUI:sta MazeGameService:n on hieman liian lyhyt, mutta se siis osoittaa MazeGameServiceen, ei koko mazegame.domain pakkausta)

Pakkaus _mazegame.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän _mazegame.domain_ sovelluslogiikan ja _mazegame.dao_ tietojen pysyväistallennuksesta vastaavan koodin, sekä tietokannan alustavan koodin.

## Käyttöliittmymä

Ohlejmalla on kolme eri näkymää:
- kirjautuminen/uuden käyttäjän luonti
- lobby(pelin avausnäkymä)
- peli

Näkymät on toteuttettu omina luokkinaan. Nämä luokat luovat ja palauttavat Scene-olion, joina näkymät ollaan toteutettu. Vain yksi näistä Scene-olioista on asetettu sovelluksen Stage-olioon, eli vain yksi näkymä näytetään käyttäjälle kerralla. Näkymän vaihtuessa näiden luokkien medodia, joka luo ja palauttaa Stage-olion, kutsutaan uudelleen. Näkymä päivittyy kun siitä siirrytään pois ja sitten takaisin. Käyttöliittymän luokka _MazeGameUi_ sisältää stagen ja metodit, joita kutsumalla näkymä vaihtuu esim. LobbyScene kutsuu MazeGameUi:n metodia startGame, joka vaihtaa näkymän pelin näkymään.

Käyttöliittymän koodi ja sovelluslogiikan koodi ollaan pyritty eristämään mahdollisimman paljon. Luokka mazegame.domain.MazeGameService tarjoaa käyttöliittymälle kaikki tarvittavat metodit sovelluslogiikan hoitamiseen esim. hahmon liikuttaminen, uuden käyttäjän luomiseen jne. Luokan MazeGameUi metodit kutsuvat näitä metodeja sen lisäksi, että ne vaihtavat näkymää.

## Sovelluslogiikka

MazeGameServicen ainoa olio tarjoaa kaikille käyttöliittymän toiminnoille omat metodit. Näitä metodeja ovat esim:

- boolean login(String username, String password)
- boolean register(String username, String password)
- void startGame(int width, int height)
- void exitGame(int time)
- void goUp(), goDown() jne..

Tämä olio tarjoaa käyttöliittymälle myös metodeja sokkelon piirtämistä varten kuten:

- CellTypeForDrawing[][] getLayoutForDrawing()
- Cell getCellAtPos(int x, int y)
- boolean hasCellBeenVisited(Cell cell)

MazeGameService pääsee käsiksi tietokantaan mazegame.dao paukkauksen Dao luokkien avulla. MazeGameService luo jokaisesta Dao luokasta olion sen konstruktorissa.

## Tietokanta

Pakkauksen mazegame.dao luokat huolehtivat tietojen tallentamisesta tietokantaan. Poikkeuksena luokka DatabaseInitializer, joka huolehtii tietokannan luonnista. Tietokanta luodaan paikallisesti.

Luokat ottavat mallia DAO suunnittelumallista (paitsi DatabaseInitializer). Luokkia ei ole eristetty erillisten rajapintojen taakse, vaan sovelluslogiikka käyttää niitä suoraan. Rajapintojen lisääminen on kuitenkin mahdollista, jos on tarvetta. DatabaseInitializer tarjoaa yhden staattisen medotin initDatabaseIfNotExisting, joka luo tietokannan, jos sitä ei ole olemassa paikallisesti.

### Tietokannan rakenne

Tietokanta sisältää kolme taulua: User, Result ja Difficulty. Taulujen rakenteet ovat seuraavat:

User
- id IDENTITY PRIMARY KEY
- username VARCHAR UNIQUE
- password VARCHAR

Difficulty
- id IDENTITY PRIMARY KEY
- name VARCHAR

Result
- id IDENTITY PRIMARY KEY
- user_id BIGINT FOREIGN KEY
- difficulty_id BIGINT FOREIGN KEY
- time INT

Tauluun Difficulty luodaan valmiiksi määritellyt vaikeusasteet tietokannan luonti hetkellä.

## Päätoiminnallisuudet

Seuraava sekvenssikaavio antaa esimerkin sovelluksen toiminnasta ja miten eri luokat kommunikoivat keskenään.

### Kirjautumislogiikkaa

(HUOM. kuvan User ei kuvasta luokkaa User vaan sovelluksen käyttäjää)

<img src="./kuvat/loginSequence.png">

(Selvennys. stage.setScene(lobbyScene) on itseasiassa stage.setScene(LobbyScene.createAndGet()), eli luokan LobbyScenen metodia createAndGet() kutsutaan, jolloin stage asettaa uuden LobbyScene olion stageen. Tämän jälkeen käyttäjä näkee lobby näkymän kirjautumisnäkymän sijaan. Jos kirjautuminen olisi epäonnistunut, näkymä ei olisi vaihtunut, ja käyttäjälle oltaisiin ilmoitettua, että käyttäjänimi tai salasana oli väärin)
