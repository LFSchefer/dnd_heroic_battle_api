# Welcome to DnD Heroic Battle API repository.
You  can find the project wiki [here](https://github.com/LFSchefer/dnd_heroic_battle/wiki)

## This is the Api repository to the DnD Heroic Battle application. The API is in Java.

Please follow those instruction to setup your Api in local.
---
### Clone the repository in local:

HTTPS:
```txt
https://github.com/LFSchefer/dnd_heroic_battle_api.git
```

Or SSH:
```txt
git@github.com:LFSchefer/dnd_heroic_battle_api.git
```
---
### Open your favorite Java IDE :

Don't forget to create a ```application.properties``` file in:
```txt
src/main/resources
```
to connect to your local database.
You will find instruction to create your local Database [here](https://github.com/LFSchefer/dnd_heroic_battle_data).

---
Launch your api and then call this URL: 
```txt
http://localhost:8080/import-data
```
to automatically import data to initialize your local database.

Your API is setup in local, enjoy =)