# Parancsok a külömböző osztályokhoz

## Felhasználókezelés (`users`)

Parancs | Leírás
--- | ---
`add-user <username>` | Új felhasználó hozzáadása
`update-user <currentUsername> <newUsername>` | Felhasználó nevének frissítése
`remove-user <username>` | Felhasználó törlése
`list-all-user` | Összes felhasználó listázása
`list-all-info <username>` | Megadott felhasználó teljes adatainak lekérdezése

---

## Háttérkép menü (`background`)

Parancs | Leírás
--- | ---
`add-bg <bgName>` | Új háttérkép hozzáadása
`update-bg <oldName> <newName>` | Háttérkép nevének frissítése
`remove-bg <bgName>` | Háttérkép törlése
`select-bg <bgName> <username>` | Háttérkép hozzárendelése felhasználóhoz
`list-all-bg` | Minden háttérkép listázása

---

## Alkalmazáskezelés (`application`)

Parancs | Leírás
--- | ---
`list-all-app` | Összes alkalmazás listázása
`install-app <appName> <username>` | Alkalmazás telepítése felhasználóhoz
`delete-app <appName> <username>` | Alkalmazás eltávolítása a felhasználótól
`start-app <appName> <username>` | Alkalmazás elindítása
`close-app <appName>` | Alkalmazás leállítása
`running-app` | Futó alkalmazások listázása
`app-operation` | Alkalmazás műveletek almenü
`add-app <appName>` | Új ikon (alkalmazás) hozzáadása az adatbázishoz
`update-app <oldName> <newName>` | Alkalmazás nevének frissítése
`remove-app <appName>` | Alkalmazás eltávolítása az adatbázisból

---

## Csoportkezelés (`group`)

Parancs | Leírás
--- | ---
`list-all-group` | Csoportok listázása
`add-user-group <groupName> <username>` | Felhasználó hozzáadása csoporthoz
`rm-user-group <groupName> <username>` | Felhasználó eltávolítása csoportból
`group-operation` | Csoport műveletek almenü
`create-group <groupName>` | Új csoport létrehozása
`update-group <oldName> <newName>` | Csoport nevének frissítése
`delete-group <groupName>` | Csoport törlése
`change-appear <groupName> <appearance>` | Csoport megjelenésének beállítása
`delete-appear <groupName>` | Csoport megjelenésének törlése (Sample Text lesz az új érték)

---

## AI használata

Parancs | Leírás
--- | ---
prompt "természetes nyelven leírt parancs" | Természetes nyelven leírt parancsot értelmezi az AI és egy parancssal válaszol FONTOS!!! AI-nak a parancsot kapcsos zárójelek közt kell megadni! -> prompt "parancs"
simulation | adatokkal tölti fel az adatbázist
