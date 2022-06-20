# Projekt z przedmiotu "Archtektury zorientowane na serwisy"

Wykonawca : Klaudia Klocek, Mateusz Chojnacki

Projekt : Biblioteka cyfrowa

Nasz projekt umożliwa tworzenie użytkowników, autora, oraz książki.
Najpierw tworzymy użytkownika, potem autora (lub autorów) ponieważ jest on potrzebny do stworzenia książki i
tworzymy książkę (lub książki).
Następnie użytkownik może dodać książkę do swojej półki.
Dodatkowo została stworzona flaga czy książka jest zarezerwowana czy nie i jeżeli jest to inny użytkownik nie może jej wypożyczyć.

Na wszystkich encjach (user, author, book) można robić wszystkie podstawowe operację CRUD (create, read, update, delete).

W projekcie jest użyta baza danych H2 która jest tak zwaną "in memory database" dlatego że jest lotna, czyli po każdym wyłączeniu programu baza
zostaje wyczyszczona, a po każdym włączeniu tworzy się na nowo.
