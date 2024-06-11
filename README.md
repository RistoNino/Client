# Client

## Idea
Implementazione di un'applicazione che gestisce il menù in digitale di un ristorante (qualsiasi).
Il cliente dal menù, ha la possibilità di vedere tutte le informazioni inerenti a un determinato piatto.
Può ordinare un piatto oppure modificarne uno già esistente e chiedere qualcosa di specifico attraverso delle note.

## Librerie implementate
1. Vert.x Core (Implementazione API)
2. JSON (Oggetti per l'invio e il ricevimento di dati tramite API)
3. Dotenv (Lettura da file Config.env)

## Struttura Schermata
### Login
Schermata di entrata con la selezione dei numeri di persone al tavolo (coperti)

### Menu
#### Sidebar
Sidebar adibita alla selezione di una categoria del Menu,
usata per raggiungere velocemente una determinata categoria.
#### Lista Piatti
A ogni categoria nel menù è assegnata una lista di piatti.
Ogni piatto ha una immagine (se aggiunta), un titolo, una descrizione
e una lista di ingredienti.

Dalla lista ci sono le opzioni rapide di aggiunta e rimozione di un piatto da un'ordinazione.
Un piatto può avere una personalizzazione sulla rimozione di ingredienti e aggiunta di note, 
e questo si può fare cliccando il piatto. 

Gli ingredienti hanno delle Flag che vanno a indicare gli allergeni presenti nel piatto e sono raffigurati da delle icone.

#### Ordine
Alla destra della schermata principale ci sarà un riassunto di tutti i piatti aggiunti all'ordinazione.
Prima di ordinare è possibile rimuovere un elemento personalizzato o rimuovere altri piatti.
Una volta cliccato il tasto "Ordina" in basso, la lista verrà bloccata e inoltrata al Server che riceverà gli ordini.
