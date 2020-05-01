# Guess the word - ehk ingliskeelsete sõnade õppimise mäng

## Autorid: Tauno Tamm ja Eero Ääremaa

Meie ideeks oli luua inglise keelne sõnade õppimise mäng, mis võimaldaks
algajal ja miks mitte ka väga heal inglise keele kõnelejaid õppida uusi
sõnu. Selle jaoks lõime programmi, mis töötab javaFx baasil. 
Programm küsib kasutajalt alustuseks tema nime, kui kasutaja on selle sisestanud
ning vajutanud nupule enter, algab mäng. Samuti pakub programm kasutajale võimalust
sisestada uusi sõnu, mis peavad olema pikemad kui 8 tähte. Samuti annab kasutajale
tagasisidet kui sõna lisamine õnnestus. Programm loeb eelnevalt
tekstifaili kirjutatud sõnad ArrayListi ning seejärel genereerib enda
andmebaasist (ArrayListist) suvalise sõna ning kasutaja püüab seda ära arvata,
sisestades iga kord üksiku tähe. Mänguväli koosneb tekstilüngast, kuhu saab sisestada
tähti, tagasiside laused, kas pakutud täht läks täppi või mitte ning
pisikesest ülevaateribast, mis annab kasutajale tagasisidet (näitab tema nime
ning selle järel tema kogutud punktisummat). Graafiliselt näeb see välja selline,
et eksisteerib lünk, kuhu saab kasutaja sisestada enda poolt pakutud tähe, 
kuid see lünk on tehtud selliselt, et sinna saab korraga sisestada ainult
ühe tähe. Kui pakutud täht sisaldub arvatavas sõnas, siis annab see kasutajale teada,
et tema vastus oli õige. Samuti saab kasutaja iga õige vastuse eest 5 punkti.
Kui aga kasutaja poolt pakutud täht on vale, siis annab programm teada, et 
pakutud tähte ei ole selles sõnas ning võtab kasutajalt 1 punkti ära.
Kui kasutaja arvab sõna ära, siis tuleb ette lõpumenüü, kust saab kasutaja teha 
valiku, kas ta mängib veel korra mingi teise suvalise sõnaga või sulgeb programmi.
Kui kasutaja valib uuesti mängimise, siis iga kord liidetakse tema olemasolevale 
punktiskoorile punkte juurde. Ehk teisisõnu on tal iga mäng 0 punkti ning
iga uuesti mängitud korraga lisanud need punktid tema selle mängu kogupunktisummale.
Kuid kui kasutaja lahkub mängus, siis tema skoor nullitakse ning tema eelnev tulemus
koos tema nime, kuupäeva ja kellaajaga salvestatakse faili Viimane_tulemus.txt,
mis iga kord annab siis kasutajale tekstifailipõhiselt ülevaate tema eelmise mängu 
punktisummast, mis on kindlasti väga kasulik õppimisel, et näha, kas on toimunud
ka arengut. Kogu mängusisene kasutajaga suhtlus toimub inglise keeles, et veelgi
luua õppijale/kasutajale sobilikku keeleõppe õhkkonda.
</br>

### Programmi ülesehitusest:

Koosneb 4 klassist:
    * anbmebaas
    * Kasutaja
    * Mang
    * Peaklass

### Klass andmebaas

Loetakse voo abil failist ArrayListi sõnad, mida kasutatakse hiljem mängus.
Klassis üks meetod, mis tagastab ArrayListi sõnadega.


### Klass Kasutaja

Isendiväli, konstruktor, getterid ja setterid
Seda kõike selle jaoks, et meil oleks eraldi isend Kasutaja,
millega oleks meil hiljem mängus andmeid lihtne salvestada.
Parameetrid nimi(String), punktid(int).


### Klass Peaklass

Selles klassis on kaks meetodit - juhuslikSõna ning lisaSõna.
juhuslikSõna genereerib mängule suvalise arvatava sõna.
lisaSõna lisab sonad.txt andmebaasi kasutaja poolt lisatud üksikuid sõnu (Kirjutatakse FileWriteriga).

### Klass Mang

Siin toimub pigem graafiline pool. Luuakse aken ning lisatakse vajalikud tekstiväljad ja sildid, et kasutajaga
mugavalt suhelda. Punktide ja nime kuvamiseks kasutame klassis Kasutaja loodud get meetodeid. 
Ning kui punktiskoor muutub (valesti või õigesti vastamisel), seal kasutame set meetodeid.
Klassis on kolm stseeni - algusestseen, mängustseen ning lõpustseen.</br>

Algusestseen on selle jaoks et küsida kasutajal uusi lisatavaid sõnu ning lisaks ta nime. 
Kui kasutaja sisestab nime, vahetub stseen mängusteeniks, kus toimub kogu mänguline poolt.
If kontrolllausega kontrollime, et kui kasutaja on sõna ära arvanud, siis vahetub mängustseen
lõpustseeniks, kus on kasutajal võimalik uuesti mängida või lahkuda mängust.</br>


Nagu ikka oli kõige raskemaks osaks teema väljemõtlemine, kuid seekord tuli see meil ladusamalt.
Olime suhteliselt kohe ühel meelel ning polnud erimeelsusi. Kõige rohkem aega võttis klass mang, sest graafiline
pool oli kõige raskem. See ei olnud kontimurdev, kuid kui võrrelda graafilise klassi teiste klassidega, siis on aru
saada, et sellega on kõige rohkem vaeva nähtud. Teised klassid olid lühikesed, peamiselt selleks, et manguklass
oleks paremini loetav ning arusaadav. Projekti idee mõtlemine võttis meil kuskil tunnike. Seejärel hakkasime looma
väiksemaid klasse, mille tegemine oli ka lihtne ning ei nõudnud palju aega. Kuid graafilise klassi tegemise alla 
kulus umbkaudu 18h. Tuleb ka mainida, et alguses võttis ka aega, et saada tööle meie ühine githubi repositoorium -
sinna alla kulus meil kuskil 2h. Tehnilistest oskustest puudu ei olnud, ei vajanud väga ka interneti abi, sest kursusel
õpituga oli väga hästi võimalik hakkama saada. Ainsaks mureks oli halb interneti ühendus, kuna me vahepeal harrastasime
paarisprogrammeerimist, kus siis üks meist jagas ekraani ning hakkasime koos asju läbivaatama/mõtlema. Peab tõdema,
et kaks pead on tõesti kaks pead. Kui ühel on midagi ununenud, siis teisel on see alati meeles ja vastupidi.
Jäime oma tehtud tööga rahule. Kõik töötab täpselt nii nagu olime plaaninud, selleks testisime pidevalt oma 
programmi erinevate parameetritega. Kasutajale võib jääda mulje, et tegu ei ole võib-olla just kõige
atraktiivsema graafilise väljundiga, kuid see oli tehtud taotuslikult, et õppimisel ei kaoks ära kasutaja
tähelepanu, seetõttu on ka programmi graafiline väljund läbivalt akadeemiliselt valge taust.
